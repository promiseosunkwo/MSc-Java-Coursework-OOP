/**
 * This Data Access Object class contains all product methods
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.util.ArrayList;
import java.sql.*;

public class CustomerDAO {

  public CustomerDAO() {
  }

  /**
   * @return DB connection
   */
  public static Connection connect() {
    Connection dbConnection = null;
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      String dbURL = "jdbc:sqlite:EverythinStoreDB.db";
      dbConnection = DriverManager.getConnection(dbURL);
      System.out.println("customers Web services is connected ...");
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return dbConnection;
  }

  /**
   * @return an arraylist containing all customer
   */
  public static ArrayList<Customer> getAllCustomers() {
    System.out.println("Retrieving all customers ...");
    Connection dbConnection = null;
    Statement statement = null;
    ResultSet result = null;
    String query = "SELECT * FROM customer";
    ArrayList<Customer> allCustomers = new ArrayList<>();

    try {
      dbConnection = connect();
      statement = dbConnection.createStatement();
      result = statement.executeQuery(query); // Execute SQL query and record response to string
      while (result.next()) {

        int id = result.getInt("customerId");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String email = result.getString("email");
        String phone = result.getString("phone");
        String house = result.getString("house");
        String address1 = result.getString("address1");
        String address2 = result.getString("address2");
        String country = result.getString("country");
        String postcode = result.getString("postcode");
        Address address = new Address(house, address1, address2, country, postcode);
        Customer newCust = new Customer(id, firstname, lastname, email, phone, address);
        allCustomers.add(newCust);
      }
    } catch (Exception e) {
      System.out.println("get all customers: " + e);
    }

    return allCustomers;
  }


  /**
   * @param a single id to get the product with the id
   * @return single customer
   */
  public static Customer getCustomer(int customerId) {

    Customer temp = null;
    Connection dbConnection = null;
    Statement statement = null;
    ResultSet result = null;

    String query = "SELECT * FROM customer WHERE customerId =" + customerId + ";";

    try {
      dbConnection = connect();
      statement = dbConnection.createStatement();
      result = statement.executeQuery(query);

      while (result.next()) {

        int id = result.getInt("customerId");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        String email = result.getString("email");
        String phone = result.getString("phone");
        String house = result.getString("house");
        String address1 = result.getString("address1");
        String address2 = result.getString("address2");
        String country = result.getString("country");
        String postcode = result.getString("postcode");
        Address address = new Address(house, address1, address2, country, postcode);
        temp = new Customer(id, firstname, lastname, email, phone, address);

      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return temp;
  }

  /**
   * @param a single id to delete the customer with the id
   */
  public static Boolean deleteCustomer(int customerId) {
    System.out.println("Deleting customer");
    Connection dbConnection = null;
    PreparedStatement statement = null;
    int result = 0;
    String query = "DELETE FROM customer WHERE customerId = " + customerId + ";";
    try {
      dbConnection = connect();
      statement = dbConnection.prepareStatement(query);
      // execute SQL query
      result = statement.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    }

    if (result == 1) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @param a single id to get the customer with the id
   * @return updated product
   */
  public static Boolean updateCustomer(Customer customer) {
    Connection dbConnection = null;
    PreparedStatement statement = null;
    try {
      dbConnection = connect();
      String query = "UPDATE customer SET firstname = ? , " + " lastname = ? , " + " email = ? , " + " phone = ? ,"
          + " house = ? ," + " address1 = ? ," + "address2 = ? ," + "country = ? ," + "postcode = ? "
          + " WHERE customerId = ? ";
      statement = dbConnection.prepareStatement(query);
      statement.setString(1, customer.getFirstName());
      statement.setString(2, customer.getLastName());
      statement.setString(3, customer.getEmail());
      statement.setString(4, customer.getPhone());
      statement.setString(5, customer.getAddress().getHouse());
      statement.setString(6, customer.getAddress().getAddress1());
      statement.setString(7, customer.getAddress().getAddress2());
      statement.setString(8, customer.getAddress().getCountry());
      statement.setString(9, customer.getAddress().getPostcode());
      statement.setInt(10, customer.getId());
      // execute SQL update
      statement.executeUpdate();

    } catch (SQLException e) {

      System.out.println(e.getMessage());
      return false;

    }

    return true;
  }

   /**
   * @param add new product
   */
  public static boolean addCustomer(Customer newCustomer) {
    Connection dbConnection = null;
    try {
      dbConnection = connect();
      PreparedStatement statement = dbConnection.prepareStatement(
          "INSERT INTO customer ( firstname, lastname, email, phone, house, address1, address2, country, postcode) VALUES (?,?,?,?,?,?,?,?,?)");

      statement.setString(1, newCustomer.getFirstName());
      statement.setString(2, newCustomer.getLastName());
      statement.setString(3, newCustomer.getEmail());
      statement.setString(4, newCustomer.getPhone());
      statement.setString(5, newCustomer.getAddress().getHouse());
      statement.setString(6, newCustomer.getAddress().getAddress1());
      statement.setString(7, newCustomer.getAddress().getAddress2());
      statement.setString(8, newCustomer.getAddress().getCountry());
      statement.setString(9, newCustomer.getAddress().getPostcode());
      // execute SQL query
      statement.execute();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }

    return true;
  }
}
