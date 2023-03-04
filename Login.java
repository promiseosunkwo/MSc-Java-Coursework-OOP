/**
 * The Login class contains the login HTML form and inputs
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


public class Login implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));


    out.write(

    "<html>" +
            "<head> <title>Login</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-5 mb-3 mt-5 \" style=\"background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75);\">"
            +
            "<h1 style=\"text-align:center; \" > Login</h1>" +
            "<form action=\"/processLoginUser\" method=\"GET\">" +
            "<div class=\"form-group\">" +
            "<label for=\"username\">Username</label>" +
            "<input type=\"username\" name=\"username\" class=\"form-control\" id=\"username\" placeholder=\"Enter Username\" required>" +
            "</div>" +
            "<div class=\"form-group \">" +
            "<label for=\"password\">Password</label>" +
            "<input type=\"password\" name=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Enter Password\" required>"
            
            +
            "</div>" +
            "<button type=\"submit\" class=\"btn btn-danger btn-lg btn-block\">Login</button>" +
            "<small class=\"form-text\">Don't have an account?<a href=\"/register\">SignUp</a> <a href=\"/\"> | Back to list</a></small>" +
  

            "</form>" +
            "</html>");   
    out.close();
  }
}

 