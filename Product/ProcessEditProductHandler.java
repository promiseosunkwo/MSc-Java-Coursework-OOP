
/**
 * This Class handles updating of product 
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessEditProductHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // print the params for debugging
    System.out.println(parms);

    System.out.println("about to get data");// Debugging message

    String uSku = parms.get("sku");
    String uDescription = parms.get("description");
    String uCategory = parms.get("category");
    int uPrice = Integer.parseInt(parms.get("price"));
    int uId = Integer.parseInt(parms.get("id"));

    System.out.println("about to update product"); // Debugging message
    Product newPoduct = new Product(uId, uSku, uDescription, uCategory, uPrice);
    ProductDAO.updateProduct(newPoduct);

    // displays updated values
    out.write(
        "<html>" +
            "<head> <title>Success Page</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-success\" role=\"alert\">Product Updated Successfully</div>" +
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
            "    <td>" + newPoduct.getSku() + "</td>" +
            "    <td>" + newPoduct.getDescription() + "</td>" +
            "    <td>" + newPoduct.getCategory() + "</td>" +
            "    <td>" + newPoduct.getPrice() + "</td>" +
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
