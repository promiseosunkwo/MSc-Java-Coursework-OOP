
/**
 * The Cart page displays all the cart items added by user
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

public class CartPage implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // use cookie to check if there is a logged in user
    int user_id = Integer.parseInt(Cookie.readUserCookie());

    // used to get a particualr users cart items
    ArrayList<OrderItem> orders = OrderItemDAO.getAllCartItems(user_id);

    out.write(

        "<html>" +
            "<head> <title>Cart</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container align-items-center h-100 py-5 \" >" +
            "<h1 style=\"text-align:center; \" > Cart</h1>" +
            "<form action=\"/cart\" method=\"GET\">" +
            "<div class=\"row\">");

    int sumTotal = 0;
    if (orders.isEmpty()) {
      out.write("<h1 style=\"text-align:center; \" > No Cart Items </h1>");
    } else {
      for (int i = 0; i < orders.size(); i++) {
        // this method uses the id of the product and id of the user to get csrt items
        Product cartProduct = ProductDAO.getProduct(orders.get(i).getProductId());

        // gets sum total of all the cart
        sumTotal += cartProduct.getPrice();
        out.write(
            "<div class=\"col-12\">" +
                "<div class=\"card rounded-3 mb-4\">" +
                "<div class=\"card-body p-4\">" +
                "<div class=\"row d-flex justify-content-between align-items-center\">" +
                "<div class=\"col-md-2 col-lg-2 col-xl-2\">" +

                "</div>" +
                "<div class=\"col-md-3 col-lg-3 col-xl-\">" +
                "<p class=\"lead fw-normal mb-2\">" + cartProduct.getDescription() + "</p>" +

                "<p><span class=\"text-muted\">Category: " + cartProduct.getCategory() + "</span></p>" +
                "<p><span class=\"text-muted\">Sku: </span>" + cartProduct.getSku() + "</div></p>" +
                "<div class=\"col-md-3 col-lg-3 col-xl-2 d-flex\">" +
                // "<a href=\"#\" class=\"m-2\">-</a>" +
                "<input id=\"form1\" min=\"1\" name=\"quantity\" value=\"" + orders.get(i).getQuantity()
                + "\" type=\"number\" class=\"form-control form-control-sm\" disabled/>"+
                // "<a href=\"#\" class=\"m-2\">+</a>" +
                "</div>" +
                "<div class=\"col-md-2 col-lg-2 col-xl-2 offset-lg-1\">" +
                " <h5 class=\"mb-0\">$" + cartProduct.getPrice() + "</h5>" +
                "</div>" +
                "<div class=\"col-md-2 col-lg-1 col-xl-1 text-end\">" +
                "<a href=\"/delete-from-cart?id=" + orders.get(i).getId()+ "\" class=\"btn btn-danger\">X</a>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>" +

                "</div>");
      }
    }
    out.write(
        "</div>" +
            "<div class=\"col-12\">" +
            "<div class=\"card rounded-3 mb-4\">" +
            "<div class=\"card-body p-4\">" +
            "<h2>Total : " + sumTotal + " </h2 >" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</form>" +
            "<a href=\"/\" class=\"btn btn-success\">back</a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}