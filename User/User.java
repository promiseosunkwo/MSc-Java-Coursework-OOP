/**
 * The User class contains user values
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.io.Serializable;

public class User implements Serializable {
  private int id;
  private String username;
  private String email;
  private String password;
  private int isAdmin;

  /**
   * @param id the autoincremented unique integer
   * @param username of a user
   * @param email of a user  
   * @param password of a user
   * @param admin and role utilization 
   */
 
  public User(int id, String username, String email, String password, int isAdmin) {

    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
  }

    /**
   * @return id
   */
  public int getId() {
    return this.id;
  }

    /**
   * @return username
   */
  public String getUsername() {
    return this.username;
  }

    /**
   * @param username
   */
  public void setUsername(String username) {
    this.username = username;
  }

    /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

    /**
   * @param email 
   */
  public void setEmail(String email) {
    this.email = email;
  }

    /**
   * @return password
   */
  public String getPassword() {
    return this.password;
  }

    /**
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

    /**
   * @return isAdmin
   */
  public int getIsAdmin() {
    return this.isAdmin;
  }


  /**
   * @param isAdmin
   */
  public void setIsAdmin(int isAdmin) {
    this.isAdmin = isAdmin;
  }


  /**
   * @return coverts values to string
   */
  @Override
  public String toString() {
    return "User  [id=" + this.id + "," + " username =" + this.username + "," + "email=" + this.email + ","
        + "password =" + this.password + "," + "isAdmin =" + this.isAdmin + "]";
  }

}