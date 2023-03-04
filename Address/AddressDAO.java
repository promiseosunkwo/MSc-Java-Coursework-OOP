/**
 * The address class which are all commented out was merged with customer class
 * making this code unnecessary
 * 
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

// import java.sql.*;
// import java.util.ArrayList;

// public class AddressDAO {

// // public ProductDA() {}

// // should get getDBConnection
// public static Connection connect() {
// Connection conn = null;

// try {
// Class.forName("org.sqlite.JDBC");
// } catch (ClassNotFoundException e) {
// System.out.println(e.getMessage());
// }
// try {
// String url = "jdbc:sqlite:EverythinStoreDB.db";
// conn = DriverManager.getConnection(url);
// System.out.println("Connected");
// return conn;
// } catch (SQLException e) {
// System.out.println(e.getMessage());
// }
// return conn;
// }

// // GETTING ALL PRODUCTS
// public static ArrayList<Address> getAllAddress() {
// Connection conn = null;
// Statement statement = null;
// ResultSet rows = null;

// String query = "SELECT * FROM address";
// ArrayList<Address> addressResultList = new ArrayList();

// try {
// conn = connect();
// statement = conn.createStatement();
// rows = statement.executeQuery(query);
// while (rows.next()) {
// // productResultList.add();
// int addressId = rows.getInt("addressId");
// String house = rows.getString("house");
// String address1 = rows.getString("address1");
// String address2 = rows.getString("address1");
// String postcode = rows.getInt("postcode");
// Address allAddress = new Address(addressId, house, address1, address2,
// postcode);
// addressResultList.add(allAddress);
// }
// } catch (Exception e) {
// System.out.println(e);
// }
// return addressResultList;
// }

// // GETTING SINGLE PRODUCT BY ID
// public static Address getAddress(int addressId) {
// Connection conn = null;
// Statement statement = null;
// ResultSet rows = null;
// Product oneAddress = null;
// String query = "SELECT * FROM address WHERE addressId = " + addressId + ";";

// try {
// conn = connect();
// statement = conn.createStatement();
// rows = statement.executeQuery(query);
// while (rows.next()) {
// // productResultList.add();
// int addressId = rows.getInt("addressId");
// String house = rows.getString("house");
// String address1 = rows.getString("address1");
// String address2 = rows.getString("address1");
// String postcode = rows.getInt("postcode");
// Address oneAddress = new Address(addressId, house, address1, address2,
// postcode);
// }

// } catch (Exception e) {
// System.out.println(e);
// }
// return oneAddress;
// }

// // UPDATE ADDRESS BY ID
// public static void updateAddress(Address updatedAddress) {
// Connection conn = null;
// // boolean isupdate = false;
// try {
// conn = connect();
// String query = "UPDATE address SET house = ? , " + "address1 = ? , " +
// "address2 = ? , " + "postcode = ? "
// + " WHERE addressId = ? ";
// PreparedStatement statement = conn.prepareStatement(query);
// statement.setString(1, updatedAddress.getHouse());
// statement.setString(2, updatedAddress.getAddress1());
// statement.setString(3, updatedAddress.getAddress2());
// statement.setInt(4, updatedAddress.getPostcode());
// statement.setInt(5, updatedAddress.getId());
// statement.executeUpdate();
// System.out.println("Address updated successfully");
// isupdate = true;

// } catch (Exception e) {
// System.out.println(e);
// // isupdate = false;
// }
// }

// // DELETE ADDRESS BY ID
// public static void deleteAddress(int addressId) {
// Connection conn = null;
// try {
// conn = connect();
// String query = "DELETE FROM address WHERE addressId = " + addressId + " ";
// PreparedStatement statement = conn.prepareStatement(query);
// statement.executeUpdate();
// System.out.println("Address deleted successfully");
// } catch (Exception e) {
// System.out.println(e);
// }
// }

// // ADDING NEW PRODUCT
// public static void addAddress(Address newAddress) {
// Connection conn = null;
// try {
// conn = connect();
// PreparedStatement statement = conn
// .prepareStatement("INSERT INTO address(house,address1,address2,postcode)
// VALUES( ?, ?, ?, ? )");
// // statement.setInt(1, newProduct.getId());
// statement.setString(1, newAddress.getHouse());
// statement.setString(2, newAddress.getAddress1());
// statement.setString(3, newAddress.getAddress2());
// statement.setInt(4, newAddress.getPostcode());
// statement.execute();
// System.out.println("Address inserted Successfully");
// // statement.close();
// // conn.close();
// } catch (Exception e) {
// System.out.println(e);
// }

// }

// }
