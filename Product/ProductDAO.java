/**
 * This Data Access Object class contains all product methods
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDAO {

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
      System.out.println("Product Web services is Connected");
      return conn;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  /**
   * @return an arraylist containing all products
   */
  public static ArrayList<Product> getAllProducts() {
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;

    String query = "SELECT * FROM product";
    ArrayList<Product> productResultList = new ArrayList();

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int productId = rows.getInt("id");
        String productSku = rows.getString("sku");
        String productDescription = rows.getString("description");
        String productCategory = rows.getString("category");
        int productPrice = rows.getInt("price");
        Product product = new Product(productId, productSku, productDescription, productCategory, productPrice);
        productResultList.add(product);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return productResultList;
  }

  /**
   * @param a single id to get the product with the id
   * @return single product
   */
  public static Product getProduct(int id) {
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;
    Product oneProduct = null;
    String query = "SELECT * FROM product WHERE id = " + id + ";";

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int productId = rows.getInt("id");
        String productSku = rows.getString("sku");
        String productDescription = rows.getString("description");
        String productCategory = rows.getString("category");
        int productPrice = rows.getInt("price");
        oneProduct = new Product(productId, productSku, productDescription,
            productCategory, productPrice);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    return oneProduct;
  }

  

  /**
   * @param a single id to get the product with the id
   * @return updated product
   */
  public static void updateProduct(Product updateProduct) {
    Connection conn = null;
    boolean isupdate = false;
    try {
      conn = connect();
      String query = "UPDATE product SET sku = ? , " + "description = ? , " + "category = ? , " + "price = ? "
          + " WHERE id = ? ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.setString(1, updateProduct.getSku());
      statement.setString(2, updateProduct.getDescription());
      statement.setString(3, updateProduct.getCategory());
      statement.setInt(4, updateProduct.getPrice());
      statement.setInt(5, updateProduct.getId());
      statement.executeUpdate();
      System.out.println("Product updated successfully");
      isupdate = true;

    } catch (Exception e) {
      System.out.println(e);
      isupdate = false;
    }
  }


  
  /**
   * @param a single id to delete the product with the id
   */
  public static void deleteProduct(int id) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "DELETE FROM product WHERE id = " + id + " ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.executeUpdate();
      System.out.println("Product deleted successfully");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * @param add new product
  * @return either true or false
   */
  public static void addProduct(Product newProduct) {
    Connection conn = null;
    try {
      conn = connect();
      PreparedStatement statement = conn
          .prepareStatement("INSERT INTO product(sku,description,category,price) VALUES( ?, ?, ?, ? )");
      statement.setString(1, newProduct.getSku());
      statement.setString(2, newProduct.getDescription());
      statement.setString(3, newProduct.getCategory());
      statement.setInt(4, newProduct.getPrice());
      statement.execute();
      System.out.println("Product inserted Successfully");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * @param search values to return product with similar values
   */
  public static ArrayList<Product> searchProducts(String input) {
    Connection conn = null;
    ResultSet rows = null;
    ArrayList<Product> productResultList = new ArrayList();

    try {
      String query = "SELECT * FROM product WHERE sku LIKE '%" + input + "%' OR description LIKE '%" + input
          + "%' OR category LIKE '%" + input + "%' OR price LIKE '%" + input + "%'";
      conn = connect();
      PreparedStatement statement = conn.prepareStatement(query);
      rows = statement.executeQuery();
      while (rows.next()) {
        int productId = rows.getInt("id");
        String productSku = rows.getString("sku");
        String productDescription = rows.getString("description");
        String productCategory = rows.getString("category");
        int productPrice = rows.getInt("price");
        Product searchedProduct = new Product(productId, productSku, productDescription, productCategory, productPrice);
        productResultList.add(searchedProduct);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return productResultList;
  }

  /**
   * @param filter integer values to return product objects less than values using lambda expression
   */
  public static List<Product> filterProducts(int price) {
    List<Product> filteredResult = new ArrayList();

    try {
      List<Product> productResult = ProductDAO.getAllProducts();
      filteredResult = productResult.stream()
          .filter(p -> p.getPrice() < price)
          .collect(Collectors.toList());
      System.out.print(filteredResult);

    } catch (Exception e) {
      System.out.println(e);
    }
    return filteredResult;
  }

}
