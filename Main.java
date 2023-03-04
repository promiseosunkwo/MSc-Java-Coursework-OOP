
/**
 * The main class houses the routing to web pages, you can also uncomment commented code to run in terminal
 *To login as and admin use username:admin and password: admin 
 *To enable better expereince, please open in chrome, mozilla, safari or internet explorer browser
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.Scanner;

class Main {

  static final private int PORT = 8080;

  public static void main(String[] args) throws IOException {

    /**
     * use the selections to navigate to any of the sub categories using the console
     * 
     * 
     * 
     */
    String mainSelector;
    Scanner input = new Scanner(System.in);
    do {
      System.out.println();
      System.out.println("----------------------------");
      System.out.println("The Everthing Store");
      System.out.println("Choose from these Options");
      System.out.println("----------------------------");

      System.out.println();
      System.out.println("[1] Access Product Options");
      System.out.println("[2] Access Customer Options ");
      System.out.println("[3] Access User Options ");
      System.out.println("[4] Access Cart Options ");
      System.out.println("[5] Launch Web services ");
      System.out.println("[99] Exit");
      System.out.println();

      mainSelector = input.nextLine();
      switch (mainSelector) {
        case "1" -> {
          ProductDAO.connect();
          ProductController.controller();
        }
        case "2" -> {
          CustomerDAO.connect();
          customerController.customerController();
        }
        case "3" -> {
          UserDAO.connect();
          userController.userController();
        }

        case "4" -> {
          OrderItemDAO.connect();
          OrderItemController.OrderItemController();
        }

        case "5" -> {

          HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
          server.createContext("/", new RootHandler());
          server.createContext("/products", new ProductDisplay());
          server.createContext("/add-product", new AddProductForm());
          server.createContext("/login", new Login());
          server.createContext("/register", new Register());
          server.createContext("/processAddProduct", new ProcessAddProductHandler());
          server.createContext("/delete", new DeleteHandler());
          server.createContext("/edit", new EditProductForm());
          server.createContext("/processEditProduct", new ProcessEditProductHandler());
          server.createContext("/customers", new CustomerDisplay());
          server.createContext("/add-customer", new AddCustomerForm());
          server.createContext("/processAddCustomer", new ProcessAddCustomerHandler());
          server.createContext("/processEditCustomer", new ProcessEditCustomerHandler());
          server.createContext("/edit-customer", new EditCustomerForm());
          server.createContext("/delete-customer", new DeleteCustomerHandler());
          server.createContext("/processAddUser", new ProcessAddUserHandler());
          server.createContext("/users", new UserDisplay());
          server.createContext("/edit-user", new EditUserForm());
          server.createContext("/processEditUser", new ProcessEditUserHandler());
          server.createContext("/delete-user", new DeleteUserHandler());
          server.createContext("/processLoginUser", new ProcessLoginUserHandler());
          server.createContext("/logout", new ProcessLogoutHandler());
          server.createContext("/SearchProduct", new SearchProductHandler());
          server.createContext("/FilterProduct", new FilterProductHandler());
          server.createContext("/cart", new CartPage());
          server.createContext("/add-to-cart", new ProcessAddToCart());
          server.createContext("/delete-from-cart", new ProcessDeleteFromCart());

          server.setExecutor(null);
          server.start();
          System.out.println("The server is listening on port " + PORT);

        }
        case "99" -> {
          System.out.println("Exiting....");
          System.out.println("Exit complete...");
          System.out.println();
          mainSelector = "99";
        }
      }
    } while (!mainSelector.equals("99"));
    input.close();

  }
}