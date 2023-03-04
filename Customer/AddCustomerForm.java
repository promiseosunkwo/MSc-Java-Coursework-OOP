
/**
 * The AddCustomer class contains html form to add a new customer
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

public class AddCustomerForm implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    System.out.println("Add Handler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

    out.write(

        "<html>" +
            "<head> <title>Add New Customer</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-5 mb-3 mt-5 \" style=\"background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); \">"
            +
            "<h1 style=\"text-align:center; \" > Add Customer</h1>" +
            "<form action=\"/processAddCustomer\" method=\"GET\">" +
            "<div class=\"form-group\">" +
            "<label for=\"sku-input\">First Name</label>" +
            "<input type=\"text\" name=\"firstname\" class=\"form-control\" id=\"firstname\" placeholder=\"Enter First Name\" required>"
            +
            "</div>" +
            "<div class=\"form-group \">" +
            "<label for=\"lastname\">Last Name</label>" +
            "<input type=\"text\" name=\"lastname\" class=\"form-control\" id=\"lastname\" placeholder=\"Enter Last Name\" required>"
            +
            "</div>" +
            "<div class=\"form-group\">" +
            "<label for=\"email\">Email Address</label>" +
            "<input type=\"email\" name=\"email\" class=\"form-control\" id=\"email\" placeholder=\"Enter Email\" required>"
            +
            "</div>" +
            "<div class=\"form-group\">" +
            "<label for=\"phone\">Phone</label>" +
            "<input type=\"text\" name=\"phone\" class=\"form-control\" id=\"phone\" placeholder=\"Enter Phone No\" required>"
            +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"house\">House No</label>" +
            "<input type=\"text\" name=\"house\" class=\"form-control\" id=\"house\" placeholder=\"Enter House No\" required>"
            +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"address1\">Address Line 1</label>" +
            "<input type=\"text\" name=\"address1\" class=\"form-control\" id=\"address1\" placeholder=\"Enter Address Line 1\" required>"
            +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"address2\">Address Line 2</label>" +
            "<input type=\"text\" name=\"address2\" class=\"form-control\" id=\"address2\" placeholder=\"Enter Address Line 2\" required>"
            +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"country\">Country</label>" +
            "<input type=\"text\" name=\"country\" class=\"form-control\" id=\"country\" placeholder=\"Enter Country\" required>"
            +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"postcode\">Post Code</label>" +
            "<input type=\"text\" name=\"postcode\" class=\"form-control\" id=\"postcode\" placeholder=\"Enter Post Code\" required>"
            +
            "</div>" +

            "<button type=\"submit\" class=\"btn btn-Success btn-lg btn-block\">Submit</button>" +
            "</form>" +
            "<a href=\"/\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}