
/**
 * This cookie class is used to hold and presist user data while logged in, it is a replacement for session
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

import java.io.*;

public class Cookie {

  /**
   * on log in the buffred reader writes user data into file
   * 
   * @params indicates user id to write into file
   */
  public static void writeUserCookie(int id) throws IOException {
    BufferedWriter file = new BufferedWriter(new FileWriter("cookie.txt"));
    file.write(Integer.toString(id));
    file.close();
  }

  /**
   * Reads the file to keep a logged in user logged in
   * 
   * @returns a logged in user id to keep track
   */
  public static String readUserCookie() throws IOException {
    BufferedReader file = new BufferedReader(new FileReader("cookie.txt"));
    String row = file.readLine();
    file.close();
    return row;
  }

  /**
   * logs user out and destroys session
   */
  public static void logoutCookie() throws IOException {
    BufferedWriter file = new BufferedWriter(new FileWriter("cookie.txt"));
    file.write(Integer.toString(0));
    file.close();
  }

}