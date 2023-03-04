/**
 * This customerDisplay class displays all customers from database to web page
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

public class CustomerDisplay implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // calls method to fetch all customer data from database
    ArrayList<Customer> allCustomers = CustomerDAO.getAllCustomers();

    // displays on webpage
    out.write(

        "<html>" +
            "<head> <title>Customers Page</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container-fluid mb-2 mt-2 \">" +
            "<h1 style=\"text-align:center; \" >All Customers</h1>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead class=\"thead-dark\">" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>FIRSTNAME</th>" +
            "    <th>LASTNAME</th>" +
            "    <th>EMAIL</th>" +
            "    <th>PHONE</th>" +
            "    <th>HOUSE NO</th>" +
            "    <th>ADDRESS 1</th>" +
            "    <th>ADDRESS 2</th>" +
            "    <th>COUNTRY</th>" +
            "    <th>POSTCODE</th>" +
            "    <th>EDIT</th>" +
            "    <th>DELETE</th>" +
            "  </tr>" +
            "</thead>" +
            "<tbody>");

    for (Customer cust : allCustomers) {
      out.write(
          "<tr>" +
              "<td>" + cust.getId() + "</td>" +
              "<td>" + cust.getFirstName() + "</td>" +
              "<td>" + cust.getLastName() + "</td>" +
              "<td>" + cust.getEmail() + "</td>" +
              "<td>" + cust.getPhone() + "</td>" +
              "<td>" + cust.getAddress().getHouse() + "</td>" +
              "<td>" + cust.getAddress().getAddress1() + "</td>" +
              "<td>" + cust.getAddress().getAddress2() + "</td>" +
              "<td>" + cust.getAddress().getCountry() + "</td>" +
              "<td>" + cust.getAddress().getPostcode() + "</td>" +
              "<td>" + "<a href=\"/edit-customer?id=" + cust.getId() + "\" class=\"btn btn-primary\" > Edit </a>"
              + "</td>" +
              "<td>" + "<a href=\"/delete-customer?id=" + cust.getId()
              + "\" onClick=\"return alert('Are you sure you want to delete this customer?')\" class=\"btn btn-danger\" > Delete </a>"
              + "</td>" +
              "</tr>");
    }

    out.write(

        "</tbody>" +
        "</table>"+
            "<a href=\"/\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();
    
  }

}