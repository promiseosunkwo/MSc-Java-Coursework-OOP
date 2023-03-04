/**
 * This class logs user out
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

public class ProcessLogoutHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // calls logout cookie method from cookie class
    Cookie.logoutCookie();

    out.write(
        "<html>" +
            "<head> <title>Search Result</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");

    out.write(
        "<div class=\"container mt-3 p-4 \">" +
            "<h3 class=\"mb-3\">LOGOUT SUCCESSFUL</h3>" +
            "<div class=\"row\">" +
            "<div class=\"container mt-3 p-4 \">" +
            "</div>");

    out.write(

        "<div class=\"col-lg-12 col-md-12 col-sm-12 \">" +
            "<div class=\"card mt-2\" style=\"box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); max-width: 300px; margin: auto; text-align: center; \">"
            +
            "<h3></h3>" +

            "<p><a href=\"/login\" class=\"btn btn-dark btn-lg btn-block\" >Log back in</a></p>" +
            "<p><a href=\"/\" class=\"btn btn-dark btn-lg btn-block\" >Home</a></p>" +
            "</div>" +
            "</div>");

    out.write(

        "</body>" +
            "</html>");
    out.close();

  }
}
