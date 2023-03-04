
/**
 * This class the delete method called within the web page to delete a particualr product
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

public class DeleteHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    // System.out.println("DeleteHandler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // print the params for debugging
    System.out.println(parms);

    // get ID number
    int id = Integer.parseInt(parms.get("id"));

    Product oneProduct = null;

    // get the product details before we delete from the Database
    oneProduct = ProductDAO.getProduct(id);

    // actually delete from database;
    ProductDAO.deleteProduct(id);

    // show deleted product
    out.write(
        "<html>" +
            "<head> <title>DVD Library</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-danger\" role=\"alert\">Product Deleted Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +
            "    <th>ID</th>" +
            "    <th>SKU</th>" +
            "    <th>DESCRIPTION</th>" +
            "    <th>CATEGORY</th>" +
            "    <th>PRICE</th>" +

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +
            "    <td>" + oneProduct.getId() + "</td>" +
            "    <td>" + oneProduct.getSku() + "</td>" +
            "    <td>" + oneProduct.getDescription() + "</td>" +
            "    <td>" + oneProduct.getCategory() + "</td>" +
            "    <td>" + oneProduct.getPrice() + "</td>" +
            "  </tr>");

    out.write(
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/products\" class=\"btn btn-outline-dark\">All Products </a>" +
            "</div>" +
            "</body>" +
            "</html>");
    out.close();

  }
}