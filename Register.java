/**
 * The Register class contains html inputs to register user and can only be done by admin
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

public class Register implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    out.write(

        "<html>" +
            "<head> <title>Register</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-4 mb-3 mt-5 \" style=\"width:40% auto; background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75);\">"
            +
            "<h1 style=\"text-align:center; \">Register</h1>" +
            "<form action=\"/processAddUser\" method=\"GET\">" +
            "<div class=\"form-group\">" +
            "<label for=\"username\">Username</label>" +
            "<input type=\"text\" name=\"username\" class=\"form-control\" id=\"username\" placeholder=\"Enter Username \" required>"
            +
            "</div>" +
            "<div class=\"form-group\">" +
            "<label for=\"email\">Email</label>" +
            "<input type=\"email\" name=\"email\" class=\"form-control\" id=\"email\" placeholder=\"Enter Email\" required>"
            +
            "</div>" +

            "<div class=\"form-group \">" +
            "<label for=\"password1\">Password</label>" +
            "<input type=\"password\" class=\"form-control\" name=\"password1\" id=\"password1\" placeholder=\"Enter Password\" required>"
            +
            "</div>" +
            "<div class=\"form-group \">" +
            "<label for=\"password2\">Confirm Password</label>" +
            "<input type=\"password\" class=\"form-control\" name=\"password2\"  id=\"password2\"   placeholder=\"Confirm Password\" required>"
            +
            "</div>" +

            "<div class=\"form-check\">" +
            "<input class=\"form-check-input\" type=\"radio\" value=\"0\"  name=\"isadmin\" id=\"flexRadioDefault1\" checked>"
            +
            "<label class=\"form-check-label\" for=\"flexRadioDefault1\">This is for User without Admin permissions</label>"
            +
            "</div>" +
            "<div class=\"form-check\">" +
            "<input class=\"form-check-input\" value=\"1\" type=\"radio\" name=\"isadmin\" id=\"flexRadioDefault2\" >" +
            "<label class=\"form-check-label\" for=\"flexRadioDefault2\">Check if you want this user to be an Admin</label>"
            +
            "</div>" +
            "<br />");

    int user_id = Integer.parseInt(Cookie.readUserCookie());
    if (user_id != 0) {
      out.write(
          "<button type=\"submit\" class=\"btn btn-danger btn-lg btn-block\">Register</button>");
    } else {
      out.write(
          "<button type=\"submit\" class=\"btn btn-danger btn-lg btn-block\" disabled>Only Admin can Register</button>");
    }
    out.write(
        "<small class=\"form-text\">have an account?<a href=\"/login\">Login</a> <a href=\"/\"> | Back             to list</a></small>"
            +
            "</form>" +

            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}