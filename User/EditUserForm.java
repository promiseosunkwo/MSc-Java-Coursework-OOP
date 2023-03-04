/**
 * This class incldes the html input forms with populated fields of a user to update
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

public class EditUserForm implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {
    // String response = "Welcome to OOP";
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // get ID number
    int id = Integer.parseInt(parms.get("id"));
    User oneUser = null;
    
    // get the product details before populating with the id
    oneUser = UserDAO.getUser(id);

    out.write(

        "<html>" +
            "<head> <title>Update User</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-4 mb-3 mt-5 \" style=\"width:40% auto; background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75);\">"
            +
            "<h1 style=\"text-align:center; \">Update User</h1>" +
            "<form action=\"/processEditUser\" method=\"GET\">" +
            "<div class=\"form-group\">" +
            "<label for=\"username\">Username</label>" +
            "<input type=\"text\" name=\"username\" class=\"form-control\" id=\"username\" value=\""
            + oneUser.getUsername() + "\" required>"
            +
            "</div>" +
            "<div class=\"form-group\">" +
            "<label for=\"email\">Email</label>" +
            "<input type=\"email\" name=\"email\" class=\"form-control\" id=\"email\" value=\"" + oneUser.getEmail()
            + "\" required>" +
            "</div>" +

            "<div class=\"form-group \">" +
            "<label for=\"password1\">Password</label>" +
            "<input type=\"password\" class=\"form-control\" name=\"password1\" id=\"password1\" placeholder=\"New Password\" required>"
            +
            "</div>" +
            "<div class=\"form-group \">" +
            "<label for=\"password2\">Confirm Password</label>" +
            "<input type=\"password\" class=\"form-control\" name=\"password2\"  id=\"password2\"   placeholder=\"Confirm new Password\" required>"
            +
            "</div>");

    if (oneUser.getIsAdmin() == 1) {
      out.write(
          "<div class=\"form-check\">" +
              "<input class=\"form-check-input\" value=\"1\" type=\"radio\" name=\"isadmin\" id=\"flexRadioDefault2\" checked>"
              +
              "<label class=\"form-check-label\" for=\"flexRadioDefault2\"> Admin</label>" +
              "</div>" +
              "<div class=\"form-check\">" +
              "<input class=\"form-check-input\" value=\"0\" type=\"radio\" name=\"isadmin\" id=\"flexRadioDefault2\">"
              +
              "<label class=\"form-check-label\" for=\"flexRadioDefault2\"> User</label>" +
              "</div>"

      );
    } else {
      out.write(
          "<div class=\"form-check\">" +
              "<input class=\"form-check-input\" value=\"0\" type=\"radio\" name=\"isadmin\" id=\"flexRadioDefault2\" checked>"
              +
              "<label class=\"form-check-label\" for=\"flexRadioDefault2\"> User</label>" +
              "</div>" +
              "<div class=\"form-check\">" +
              "<input class=\"form-check-input\" value=\"1\" type=\"radio\" name=\"isadmin\" id=\"flexRadioDefault2\">"
              +
              "<label class=\"form-check-label\" for=\"flexRadioDefault2\"> Admin</label>" +
              "</div>"

      );
    }

    out.write(
        "<input type=\"hidden\" name=\"id\" class=\"form-control\" id=\"id\" value=\"" + id + "\" required>" +
            "<br />" +

            "<button type=\"submit\" class=\"btn btn-danger btn-lg btn-block\">Update User</button>" +
            "<small class=\"form-text\"><a href=\"/users\">Back to users<a href=\"/\"> | Back to list</a></small>" +
            // "</div>" +
            "</form>" +

            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}