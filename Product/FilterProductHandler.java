
/**
 * This Class implemnts thr filter method on the products to filter products by thier price
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.*;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

public class FilterProductHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    // System.out.println("DeleteHandler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());

    // print the params for debugging
    System.out.println(parms);

    // get string parsed from url
    int filterInput = Integer.parseInt(parms.get("filterInput"));

    // creates a new list
    List<Product> filteredProduct = new ArrayList();

    // use cookie to check if there is a logged in user
    int user_id = Integer.parseInt(Cookie.readUserCookie());

    // call the method to get the filter requested
    filteredProduct = ProductDAO.filterProducts(filterInput);

    out.write(
        "<html>" +
            "<head> <title>Filter Result</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");

    // this is a list of all products
    out.write(
        "<div class=\"container mt-3 p-4 \">" +
            "<h3 class=\"mb-3\">Filter Results</h3>" +
            "<div class=\"row\">");

    if (filteredProduct.isEmpty()) {
      out.write(
          "<div class=\"container mt-3 p-4 \">" +
              "<h3 class=\"mb-3\">No match for filter " + filterInput + "</h3>" +
              "</div>");
    } else {
      for (Product prod : filteredProduct) {
        out.write(

            "<div class=\"col-lg-3 col-md-6 col-sm-12 \">" +
                "<div class=\"card mt-2\" style=\"box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); max-width: 300px; margin: auto; text-align: center; \">"
                +
                "<h3>" + prod.getDescription() + "</h3>" +
                "<h6>" + prod.getSku() + "</h6>" +
                "<h6>" + prod.getCategory() + "</h6>" +
                "<p style=\"color: grey; font-size: 25px;\" >$" + prod.getPrice() + "</p>" +
                "<p>" + prod.getDescription() + "</p>");

              if (user_id != 0) {
               out.write("<p><a href=\"/add-to-cart?pid=" + prod.getId()
               + "\" class=\"btn btn-dark btn-lg btn-block\" >Add to Cart</a></p>");
               } else {
              out.write("<p><a class=\"btn btn-dark btn-lg btn-block\">Login to add to cart</a>                 </p>");
              }


                out.write(
                "</div>" +
                "</div>");
      }
    }
    out.write(
        "</div>" +
            "<button class=\"btn btn-outline-secondary mt-3\" onclick=\"return history.go(-1);\" >Back</button>" +
            "</div>" +

            "</body>" +
            "</html>");
    out.close();

  }
}