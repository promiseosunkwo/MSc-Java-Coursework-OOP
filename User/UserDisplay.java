
/**
 * This class userDisplay displays all users from Database
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

public class UserDisplay implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // gets all users
    ArrayList<User> allUsers = UserDAO.getAllUsers();

    out.write(

        "<html>" +
            "<head> <title>Users Page</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container-fluid mb-2 mt-2 \">" +
            "<h1 style=\"text-align:center; \" >All Users</h1>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead class=\"thead-dark\">" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>USERNAME</th>" +
            "    <th>EMAIL</th>" +
            "    <th>PASSWORD</th>" +
            "    <th>ROLE</th>" +
            "    <th>EDIT</th>" +
            "    <th>DELETE</th>" +
            "  </tr>" +
            "</thead>" +
            "<tbody>");

    for (User users : allUsers) {
      out.write(
          "<tr>" +
              "<td>" + users.getId() + "</td>" +
              "<td>" + users.getUsername() + "</td>" +
              "<td>" + users.getEmail() + "</td>" +
              "<td>" + users.getPassword() + "</td>");

      // checks if user is admin
      if (users.getIsAdmin() == 1) {

        out.write(
            "    <td>Admin</td>");
      } else {
        out.write(
            "    <td>User</td>");
      }

      out.write(
          "<td>" + "<a href=\"/edit-user?id=" + users.getId() + "\" class=\"btn btn-primary\" > Edit </a>" + "</td>" +
              "<td>" + "<a href=\"/delete-user?id=" + users.getId()
              + "\" onClick=\"return alert('Are you sure you want to delete this user?')\" class=\"btn btn-danger\" > Delete </a>"
              + "</td>" +
              "</tr>");
    }

    out.write(

        "</tbody>" +
            "<a href=\"/\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</table>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}