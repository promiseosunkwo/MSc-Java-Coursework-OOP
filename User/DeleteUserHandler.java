/**
 * This class deletes users from the database, can only be done by admin
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteUserHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());


    // get ID number
    int id = Integer.parseInt(parms.get("id"));

    User oneUser = null;

    // gets the user by id
    oneUser = UserDAO.getUser(id);
    
    // actually delete from database;
    UserDAO.deleteUser(id);

    out.write(
        "<html>" +
            "<head> <title>Delete User</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-danger\" role=\"alert\">User Deleted Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>USERNAME</th>" +
            "    <th>EMAIL</th>" +
            "    <th>PASSWORD</th>" +
            "    <th>ROLE</th>" +
           

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +
             " <td>" + oneUser.getId() + "</td>" +
            "    <td>" + oneUser.getUsername() + "</td>" +
            "    <td>" + oneUser.getEmail() + "</td>" +
            "    <td>" + UserDAO.hashPassword(oneUser.getPassword()) + "</td>");
      
                 if(oneUser.getIsAdmin() == 1){
                    
                  out.write(
                    "    <td>Admin</td>");
                  }else{
                   out.write(
                    "    <td>User</td>");
                  }
          

    out.write(
      "  </tr>"+
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/users\" class=\"btn btn-outline-dark\">All Users </a>" +
            "</div>" +
            "</body>" +
            "</html>");
    out.close();

  }
}