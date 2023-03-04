
/**
 * The Roothandler is the homepage of the application and contains all products
 * @author Promise Osunkwo 22504972
 * @version 1.0
 *To login as and admin use username:admin and password: admin 
 */

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RootHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // get all products from database
    ArrayList<Product> allProducts = ProductDAO.getAllProducts();

    // use cookie to check if there is a logged in user
    int user_id = Integer.parseInt(Cookie.readUserCookie());

    // used to get a particualr users cart items
    ArrayList<OrderItem> orders = OrderItemDAO.getAllCartItems(user_id);

    out.write(
        "<html>" +
            "<head> <title>Home</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<link rel=\"stylesheet\" href=\"path/to/font-awesome/css/font-awesome.min.css\">" +
            "<body>" +
            "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">" +
            "<a class=\"navbar-brand text-lg\" href=\"/\">Everything Store</a>" +
            "<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
            +
            "<span class=\"navbar-toggler-icon\"></span>" +
            "</button>" +

            " <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">" +
            "<ul class=\"navbar-nav mr-auto \">" +
            "<li class=\"nav-item  \">");
    // check if user is admin to enable some features
    if (user_id != 0) {
      User logged_user = UserDAO.getUser(user_id);
      if (logged_user.getIsAdmin() == 1) {
        out.write(
            "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Products </a>"
                +
                "<ul class=\"dropdown-menu\">" +
                "<li><a class=\"dropdown-item\" href=\"/products\">View all Products</a></li>" +
                "<li><a class=\"dropdown-item\" href=\"/add-product\">Add a new Product</a></li>" +
                "</ul>" +
                "</li>" +

                "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Customers </a>"
                +
                "<ul class=\"dropdown-menu\">" +
                "<li><a class=\"dropdown-item\" href=\"/customers\">View all Customers</a></li>" +
                "<li><a class=\"dropdown-item\" href=\"add-customer\">Add a new Customer</a></li>" +
                "</ul>" +
                "</li>" +

                "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"
                + logged_user.getUsername() + "</a>"
                +
                "<ul class=\"dropdown-menu\">" +

                "<li><a class=\"dropdown-item\" href=\"/logout\" onClick=\"return alert('Are you sure you want to logout?')\">Logout</a></li>"
                +

                "<li><a class=\"dropdown-item\" href=\"/users\">All Users</a></li>" +
                "<li><a class=\"dropdown-item\" href=\"/register\">Register New User</a></li>" +
                "</ul>" +
                "</li>" +
                "</tr>");

      } else {
        out.write(
            "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Products </a>"
                +
                "<ul class=\"dropdown-menu\">" +
                "<li><a class=\"dropdown-item\" href=\"#\">Admin Protected</a></li>" +

                "</ul>" +
                "</li>" +

                "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Customers </a>"
                +
                "<ul class=\"dropdown-menu\">" +
                "<li><a class=\"dropdown-item\" href=\"#\">Admin Protected</a></li>" +
                "</ul>" +
                "</li>" +

                "<li class=\"nav-item dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">"
                + logged_user.getUsername() + "</a>"
                +
                "<ul class=\"dropdown-menu\">" +
                "<li><a class=\"dropdown-item\" href=\"/logout\" onClick=\"return alert('Are you sure you want to logout?')\">Logout</a></li>"
                +
                "</ul>" +
                "</li>"

        );
      }

    } else {
      out.write(
          "<li class=\"nav-item dropdown\">" +
              "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Products </a>"
              +
              "<ul class=\"dropdown-menu\">" +
              "<li><a class=\"dropdown-item\" href=\"#\">Admin Restriction</a></li>" +
              "</ul>" +
              "</li>" +

              "<li class=\"nav-item dropdown\">" +
              "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> Customers </a>"
              +
              "<ul class=\"dropdown-menu\">" +
              "<li><a class=\"dropdown-item\" href=\"#\">Admin Restriction</a></li>" +
              "</ul>" +
              "</li>" +

              "<li class=\"nav-item dropdown\">" +
              "<a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\"> user </a>"
              +
              "<ul class=\"dropdown-menu\">" +

              "<li><a class=\"dropdown-item\" href=\"/login\">Login</a></li>" +
              "<li><a class=\"dropdown-item\" href=\"/register\">Register</a></li>" +
              "</ul>" +
              "</li>"

      );
    }

    out.write(
        "</ul>" +

            "<form action=\"/SearchProduct\" method=\"GET\" class=\"form-inline my-2 my-lg-0\">" +
            "<input class=\"form-control mr-sm-2\" name=\"searchInput\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\" required>"
            +
            " <button class=\"btn btn-outline-secondary mr-3 my-sm-0\" type=\"submit\">Search</button>" +
            "</form>" +

            "<a href=\"/cart\" class=\"btn btn-secondary\">Cart<span style=\" background-color:white; color:black; padding:0px 5px; margin:3px; border-radius:12px; \">"
            + orders.size() + "</span></a>"
            +
            "</div>" +
            "</nav>"+

//Bootsrap cdn to enable drop down menus
"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN\" crossorigin=\"anonymous\"></script>"

    );

    // this is a list of all products
    out.write(
        "<div class=\"container mt-3 p-4 \">" +

            "<form action=\"/FilterProduct\" method=\"GET\" class=\"form-inline my-2 my-lg-0\">" +
            "<div class=\"form-group\">" +
            "<input type=\"number\" name=\"filterInput\" placeholder=\"Filter by Price\" class=\"form-control mx-sm-3\" required>"
            +
            "<button class=\"btn btn-outline-secondary mr-3 my-sm-0\" type=\"submit\">Filter</button>" +
            "</div>" +
            "</form>" +

            "<div class=\"row\">");
    // list products
    for (Product prod : allProducts) {
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
        out.write("<p><a class=\"btn btn-dark btn-lg btn-block\">Login to add to cart</a></p>");
      }
      out.write("</div>" +
          "</div>");
    }

    out.write(
        "</div>" +
            "</div>" +

            "</body>" +
            "</html>");

    out.close();

  }

}