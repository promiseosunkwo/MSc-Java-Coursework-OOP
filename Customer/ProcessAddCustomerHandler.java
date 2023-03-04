/**
 * This class uadds new customer to the database
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

public class ProcessAddCustomerHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {


    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());


    String firstname = parms.get("firstname");
    String lastname = parms.get("lastname");
    String email = parms.get("email");
    String phone = parms.get("phone");
    String house = parms.get("house");
    String address1 = parms.get("address1");
    String address2 = parms.get("address2");
    String country = parms.get("country");
    String postcode = parms.get("postcode");


    System.out.println("about to create customer"); // Debugging message
    Address newAddress = new Address(house, address1, address2, country, postcode);
    Customer newCustomer = new Customer(0, firstname, lastname, email, phone, newAddress);




   
    CustomerDAO.addCustomer(newCustomer); // add to database

    // display on webpage
    out.write(
        "<html>" +
            "<head> <title>Success Page</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-success\" role=\"alert\">Customer Added Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +

            "    <th>Firstname</th>" +
            "    <th>Lastname</th>" +
            "    <th>Email</th>" +
            "    <th>Phone</th>" +
            "    <th>House</th>" +
            "    <th>Address Line 1</th>" +
            "    <th>Address Line 2</th>" +
            "    <th>Country</th>" +
            "    <th>Postcode</th>" +

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +

            "    <td>" + newCustomer.getFirstName() + "</td>" +
            "    <td>" + newCustomer.getLastName() + "</td>" +
            "    <td>" + newCustomer.getEmail() + "</td>" +
            "    <td>" + newCustomer.getPhone() + "</td>" +
            "    <td>" + newCustomer.getAddress().getHouse() + "</td>" +
            "    <td>" + newCustomer.getAddress().getAddress1() + "</td>" +
            "    <td>" + newCustomer.getAddress().getAddress2() + "</td>" +
            "    <td>" + newCustomer.getAddress().getCountry() + "</td>" +
            "    <td>" + newCustomer.getAddress().getPostcode() + "</td>" +
            "  </tr>");

    out.write(
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/add-customer\" class=\"btn btn-outline-secondary\">Add Customer </a>" +
            "</div>" +
            "</body>" +
            "</html>");

    out.close();

  }
}
