/**
 * This Data Access Object class contains all user methods
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

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
      System.out.println("User Web services is Connected");
      return conn;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }


  
  /**
  * @param takes the string of paassword in text
   * @return hashed MD5 password
   */
  public static String hashPassword(String pwd) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.update(pwd.getBytes());
      byte[] resultByteArray = messageDigest.digest();
      StringBuilder sb = new StringBuilder();
      for (byte b : resultByteArray) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * @return an arraylist containing all users
   */
  public static ArrayList<User> getAllUsers() {
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;

    String query = "SELECT * FROM user";
    ArrayList<User> userResultList = new ArrayList();

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int userId = rows.getInt("userId");
        String userUsername = rows.getString("username");
        String userEmail = rows.getString("email");
        String userPassword = rows.getString("password");
        int userisAdmin = rows.getInt("isAdmin");
        User user = new User(userId, userUsername, userEmail, userPassword, userisAdmin);
        userResultList.add(user);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return userResultList;
  }

  /**
   * @param a single id to get the user with the id
   * @return single user
   */
  public static User getUser(int id) {
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;
    User oneUser = null;
    String query = "SELECT * FROM user WHERE userId = " + id + ";";

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int userId = rows.getInt("userId");
        String userUsername = rows.getString("username");
        String userEmail = rows.getString("email");
        String userPassword = rows.getString("password");
        int userIsAdmin = rows.getInt("isAdmin");
        oneUser = new User(userId, userUsername, userEmail,
            userPassword, userIsAdmin);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    return oneUser;
  }

  /**
   * @param a single id to get the user with the id
   * @return updated user
   */
  public static Boolean updateUser(User updateUser) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "UPDATE user SET username = ? , " + "email = ? , " + "password = ? , " + "isAdmin = ? "
          + " WHERE userId = ? ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.setString(1, updateUser.getUsername());
      statement.setString(2, updateUser.getEmail());
      statement.setString(3, hashPassword(updateUser.getPassword()));
      statement.setInt(4, updateUser.getIsAdmin());
      statement.setInt(5, updateUser.getId());
      statement.executeUpdate();
      System.out.println("User updated successfully");

    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @param a single id to delete the user with the id
   */
  public static Boolean deleteUser(int id) {
    Connection conn = null;
    try {
      conn = connect();
      String query = "DELETE FROM user WHERE userId = " + id + " ";
      PreparedStatement statement = conn.prepareStatement(query);
      statement.executeUpdate();
      System.out.println("user deleted successfully");
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @param add new user
   * @return either true or false
   */
  public static Boolean addUser(User newUser) {
    Connection conn = null;
    try {
      conn = connect();
      PreparedStatement statement = conn
          .prepareStatement("INSERT INTO user(username,email,password,isAdmin) VALUES( ?, ?, ?, ? )");
      statement.setString(1, newUser.getUsername());
      statement.setString(2, newUser.getEmail());
      statement.setString(3, hashPassword(newUser.getPassword()));
      statement.setInt(4, newUser.getIsAdmin());
      statement.execute();
      System.out.println("User inserted Successfully");
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  /**
   * @param username
   * @param password
   * @return true if user and false in no user
   */
  public static Boolean loginUser(String userName, String pass) {
    String hashPass = hashPassword(pass);
    Connection conn = null;
    Statement statement = null;
    ResultSet rows = null;
    User loggedUser = null;
    Boolean isLoggedIn = false;
    String query = "SELECT * FROM user WHERE username='" + userName + "' and password='" + hashPass + "'";

    try {
      conn = connect();
      statement = conn.createStatement();
      rows = statement.executeQuery(query);
      while (rows.next()) {
        int loggedId = rows.getInt("userId");
        String loggedUsername = rows.getString("username");
        String loggedEmail = rows.getString("email");
        String loggedPassword = rows.getString("password");
        int loggedIsAdmin = rows.getInt("isAdmin");
        loggedUser = new User(loggedId, loggedUsername, loggedEmail, loggedPassword, loggedIsAdmin);
        isLoggedIn = true;
        // System.out.println(loggedUser); testing purposes

        // writegetUserCookie appends the user to a txt file to ensure session data is
        // maintained
        Cookie.writeUserCookie(loggedId);

      }

    } catch (Exception e) {
      System.out.println(e);
      isLoggedIn = false;
    }
    return isLoggedIn;
  }

}
