
/**
 * This class removes an item to the cart
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

public class ProcessDeleteFromCart implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // print the params for debugging
    System.out.println(parms);

    // get ID number
    int orderId = Integer.parseInt(parms.get("id"));

    Boolean isDeleted = OrderItemDAO.deleteCartItem(orderId);

    if (isDeleted) {
      out.write(
          "<html>" +
              "<head> <title>Cart</title> " +
              "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
              +
              "<div class=\"container mt-3 p-4 \">" +
              "<h3 class=\"mb-3\">Item Deleted from Cart</h3>" +
              "<div class=\"row\">" +
              "<div class=\"container mt-3 p-4 \">" +
              "</div>" +
              "<div class=\"col-lg-12 col-md-12 col-sm-12 \">" +
              "<div class=\"card mt-2\" style=\"box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); max-width: 300px; margin: auto; text-align: center; \">"
              +
              "<h3></h3>" +
              "<p><a href=\"/cart\" class=\"btn btn-dark btn-lg btn-block\" >Go to Cart</a></p>" +
              "<p><a href=\"/\" class=\"btn btn-dark btn-lg btn-block\" >Continue Shopping</a></p>" +
              "</div>" +
              "</div>" +
              "</body>" +
              "</html>");

    } else {
      out.write(
          "<html>" +
              "<head> <title>Cart</title> " +
              "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
              +
              "<div class=\"container mt-3 p-4 \">" +
              "<h3 class=\"mb-3\">Could not Delete item contact Admin</h3>" +
              "<div class=\"row\">" +
              "<div class=\"container mt-3 p-4 \">" +
              "</div>" +
              "<div class=\"col-lg-12 col-md-12 col-sm-12 \">" +
              "<div class=\"card mt-2\" style=\"box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); max-width: 300px; margin: auto; text-align: center; \">"
              +
              "<h3></h3>" +
              "<p><a href=\"/\" class=\"btn btn-dark btn-lg btn-block\" >Back</a></p>" +
              "</div>" +
              "</div>" +
              "</body>" +
              "</html>");

    }
    out.close();

  }
}
