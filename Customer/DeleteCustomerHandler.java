/**
 * This class deletes a cusomter from the database
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

public class DeleteCustomerHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    // System.out.println("DeleteHandler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // print the params for debugging
    // System.out.println(parms);

    // get ID number
    int id = Integer.parseInt(parms.get("id"));

    Customer oneCustomer = null;


    // get the customer details before we delete from the Database
    oneCustomer = CustomerDAO.getCustomer(id);
    
    // actually delete from database;
    CustomerDAO.deleteCustomer(id);

    out.write(
        "<html>" +
            "<head> <title>Delete Customer</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-danger\" role=\"alert\">Customer Deleted Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>FIRSTNAME</th>" +
            "    <th>LASTNAME</th>" +
            "    <th>EMAIL</th>" +
            "    <th>PHONE</th>" +
            "    <th>HOUSE</th>" +
            "    <th>ADDRESS 1</th>" +
            "    <th>ADDRESS 2</th>" +
            "    <th>COUNTRY</th>" +
            "    <th>POSTCODE</th>" +

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +
            "    <td>" + oneCustomer.getId() + "</td>" +
            "    <td>" + oneCustomer.getFirstName() + "</td>" +
            "    <td>" + oneCustomer.getLastName() + "</td>" +
            "    <td>" + oneCustomer.getEmail() + "</td>" +
            "    <td>" + oneCustomer.getPhone() + "</td>" +
            "    <td>" + oneCustomer.getAddress().getHouse() + "</td>" +
            "    <td>" + oneCustomer.getAddress().getAddress1() + "</td>" +
            "    <td>" + oneCustomer.getAddress().getAddress2() + "</td>" +
            "    <td>" + oneCustomer.getAddress().getCountry() + "</td>" +
            "    <td>" + oneCustomer.getAddress().getPostcode() + "</td>" +
            "  </tr>");

    out.write(
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/customers\" class=\"btn btn-outline-dark\">All Customers </a>" +
            "</div>" +
            "</body>" +
            "</html>");
    out.close();

  }
}