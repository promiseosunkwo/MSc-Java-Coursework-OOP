/**
 * This class updates customer information
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

public class EditCustomerForm implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    System.out.println("Edit Handler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // get ID number
    int id = Integer.parseInt(parms.get("id"));

    // create new customer ibject and set it to null
    Customer oneCustomer = null;
      
    // get the customer details before with the id
    oneCustomer = CustomerDAO.getCustomer(id);
    

    // display on webpage
    out.write(

        "<html>" +
            "<head> <title>Update Customer</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-5 mb-3 mt-5 \" style=\"background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); \">"
            +
            "<h1 style=\"text-align:center; \" > Update Customer</h1>" +
            "<form action=\"/processEditCustomer\" method=\"GET\">" +

            "<div class=\"form-group\">" +
            "<label for=\"firstname\">First Name</label>" +
            "<input type=\"text\" name=\"firstname\" class=\"form-control\" id=\"firstname\" value=\""
            + oneCustomer.getFirstName() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"lastname\">Last Name</label>" +
            "<input type=\"text\" name=\"lastname\" class=\"form-control\" id=\"lastname\" value=\""
            + oneCustomer.getLastName() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"lastname\">Email</label>" +
            "<input type=\"email\" name=\"email\" class=\"form-control\" id=\"email\" value=\"" + oneCustomer.getEmail()
            + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"phone\">Phone</label>" +
            "<input type=\"text\" name=\"phone\" class=\"form-control\" id=\"phone\" value=\"" + oneCustomer.getPhone()
            + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"house\">House No</label>" +
            "<input type=\"text\" name=\"house\" class=\"form-control\" id=\"house\" value=\""
            + oneCustomer.getAddress().getHouse() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"address1\">Address Line 1</label>" +
            "<input type=\"text\" name=\"address1\" class=\"form-control\" id=\"address1\" value=\""
            + oneCustomer.getAddress().getAddress1() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"address2\">Address Line 2</label>" +
            "<input type=\"text\" name=\"address2\" class=\"form-control\" id=\"address2\" value=\""
            + oneCustomer.getAddress().getAddress2() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"country\">Country</label>" +
            "<input type=\"text\" name=\"country\" class=\"form-control\" id=\"country\" value=\""
            + oneCustomer.getAddress().getCountry() + "\"  required>" +
            "</div>" +

            "<div class=\"form-group\">" +
            "<label for=\"postcode\">Post Code</label>" +
            "<input type=\"text\" name=\"postcode\" class=\"form-control\" id=\"postcode\" value=\""
            + oneCustomer.getAddress().getPostcode() + "\"  required>" +
            "</div>" +

            "<input type=\"hidden\" name=\"id\" class=\"form-control\" id=\"id\" value=\"" + id + "\" required>"
            +

            "<button type=\"submit\" class=\"btn btn-success btn-lg btn-block\">Update</button>" +
            "</form>" +
            "<a href=\"/customers\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();
  }

}