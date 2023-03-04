
/**
 * This class contains the html product form to add a new product which can only be done by an admin
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
import java.sql.SQLException;;

public class AddProductForm implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {

    System.out.println("Add Handler Called");
    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

    out.write(
        "<html>" +
            "<head> <title>Add New Product</title> </head>" +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "<body style=\"background-color:#F7F7F7; font-family:cursive;\">" +
            "<div class=\"container p-5 mb-3 mt-5 \" style=\"background-color:white; -webkit-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); -moz-box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); box-shadow: 4px 10px 5px 0px rgba(0,0,0,0.75); \">"
            +
            "<h1 style=\"text-align:center; \" > Add Product</h1>" +
            "<form action=\"/processAddProduct\" method=\"GET\">" +
            "<div class=\"form-group\">" +
            "<label for=\"sku-input\">SKU</label>" +
            "<input type=\"text\" name=\"sku\" class=\"form-control\" id=\"sku\" placeholder=\"Enter SKU No\" required>"
            +
            "</div>" +
            "<div class=\"form-group \">" +
            "<label for=\"description-input\">Description</label>" +
            "<input type=\"text\" name=\"description\" class=\"form-control\" id=\"description\" placeholder=\"Enter Product Description\" required>"
            +
            "</div>" +
            "<div class=\"form-group\">" +
            "<label for=\"category-input\">Category</label>" +
            "<input type=\"text\" name=\"category\" class=\"form-control\" id=\"category\" placeholder=\"Enter Product Category\" required>"
            +
            "<div class=\"form-group\">" +
            "<label for=\"price-input\">Price</label>" +
            "<input type=\"number\" name=\"price\" class=\"form-control\" id=\"description\" placeholder=\"Enter Product Price\" required>"
            +
            "</div>" +
            "</div>" +
            "<button type=\"submit\" class=\"btn btn-dark btn-lg btn-block\">Submit</button>" +
            "</form>" +
            "<a href=\"/\" class=\"btn btn-outline-dark\">Back to List </a>" +
            "</div> " +
            "</body>" +
            "</html>");
    out.close();

  }

}