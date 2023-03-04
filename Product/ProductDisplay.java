/**
 * This class Displays all products from Database
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

public class ProductDisplay implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // calls method to get all products
    ArrayList<Product> allProducts = ProductDAO.getAllProducts();

    out.write(
        "<html>" +
            "<head> <title>Products Page</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container-fluid mb-2 mt-2 \">" +
            "<h1 style=\"text-align:center; \" >All Products</h1>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead class=\"thead-dark\">" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>SKU</th>" +
            "    <th>DESCRIPTION</th>" +
            "    <th>CATEGORY</th>" +
            "    <th>PRICE</th>" +
            "    <th>EDIT</th>" +
            "    <th>DELETE</th>" +
            "  </tr>" +
            "</thead>" +
            "<tbody>");

    for (Product prod : allProducts) {
      out.write(
          "<tr>" +
              "<td>" + prod.getId() + "</td>" +
              "<td>" + prod.getSku() + "</td>" +
              "<td>" + prod.getDescription() + "</td>" +
              "<td>" + prod.getCategory() + "</td>" +
              "<td>" + prod.getPrice() + "</td>" +
              "<td>" + "<a href=\"/edit?id=" + prod.getId() + "\" class=\"btn btn-primary\" > Edit </a>" + "</td>" +
              "<td>" + "<a href=\"/delete?id=" + prod.getId()
              + "\" onClick=\"return alert('Are you sure you want to delete this product?')\" class=\"btn btn-danger\" > Delete </a>"
              + "</td>" +
              "</tr>");

    }

    out.write(
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }
}
