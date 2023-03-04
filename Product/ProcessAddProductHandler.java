
/**
 * This Class handles the insertion of a new product into database
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

public class ProcessAddProductHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());
    
    // print the params for debugging
    System.out.println(parms);


    String sku = parms.get("sku");
    String description = parms.get("description");
    String category = parms.get("category");
    int price = Integer.parseInt(parms.get("price"));


    System.out.println("about to create product"); // Debugging message

    
    Product newproduct = new Product(0, sku, description, category, price); // creates new product 

    ProductDAO.addProduct(newproduct); // add to database


    // displays on page
    out.write(
        "<html>" +
            "<head> <title>Success Page</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-success\" role=\"alert\">Product Added Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +
            "    <th>Sku</th>" +
            "    <th>Description</th>" +
            "    <th>Category</th>" +
            "    <th>Price</th>" +

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +
            "    <td>" + newproduct.getSku() + "</td>" +
            "    <td>" + newproduct.getDescription() + "</td>" +
            "    <td>" + newproduct.getCategory() + "</td>" +
            "    <td>" + newproduct.getPrice() + "</td>" +
            "  </tr>");

    out.write(
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/add-product\" class=\"btn btn-outline-secondary\">Add Product </a>" +
            "</div>" +
            "</body>" +
            "</html>");
    
    out.close();

  }
}
