
/**
 * This Data Access Object class contains all orderitem methods to add to cart
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class OrderItemDAO {

  /**
   * @return DB connection
   */
  public static Connection connect() {
    Connection conn = null;

    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      String url = "jdbc:sqlite:EverythinStoreDB.db";
      conn = DriverManager.getConnection(url);
      System.out.println("OrderItem Web services is Connected");
      return conn;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  /**
   * @return a boolean to insert to cart
   */
  public static Boolean insertToCart(OrderItem cartItem) {
    Connection conn = null;
    try {
      conn = connect();
      PreparedStatement statement = conn
          .prepareStatement("INSERT INTO orderitem(productId,customerId,quantity) VALUES(  ?, ?, ? )");
      statement.setInt(1, cartItem.getProductId());
      statement.setInt(2, cartItem.getCustomerId());
      statement.setInt(3, cartItem.getQuantity());
      statement.execute();
      System.out.println("item inserted to cart Successfully");
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @param deletes a cart item with the id
   */
  public static Boolean deleteCartItem(int id) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "DELETE FROM orderitem WHERE orderitemId = " + id + " ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.executeUpdate();
      // System.out.println("Product deleted successfully from cart");
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @return an arraylist containing all products in cart
   */
  public static ArrayList<OrderItem> getAllCartItems(int customerId) {
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;

    String query = "SELECT * FROM orderitem WHERE customerId = " + customerId + " ";
    ArrayList<OrderItem> cartResultList = new ArrayList();

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int orderItemId = rows.getInt("orderItemId");
        int productId = rows.getInt("productId");
        int custId = rows.getInt("customerId");
        int quantity = rows.getInt("quantity");
        OrderItem cart = new OrderItem(orderItemId, productId, custId, quantity);
        cartResultList.add(cart);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return cartResultList;
  }

  /**
   * @param update increment cart
   * @return updated cart
   */
  public static Boolean incrementCart(int orderitemId, int customerId, int quantity) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "UPDATE orderitem SET quantity = " + quantity + "+" + 1 + " WHERE orderItemId = " + orderitemId
          + " and customerId = " + customerId + " ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.executeUpdate();

    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @param update decrement cart
   * @return updated cart
   */
  public static Boolean decrementCart(int orderitemId, int customerId, int quantity) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "UPDATE orderitem SET quantity = " + quantity + "-" + 1 + " WHERE orderItemId = " + orderitemId
          + " and customerId = " + customerId + " ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.executeUpdate();

    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }
}