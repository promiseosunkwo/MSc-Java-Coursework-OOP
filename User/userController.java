/**
 * @author Promise Osunkwo 22504972
 * @version 1.0
 * @return console testing for user methods
 */

import java.util.ArrayList;
import java.util.Scanner;

public class userController {

  public static void userController() {
    Scanner input = new Scanner(System.in);

    String userSelector;

    do {

      System.out.println(".........................");
      System.out.println("The Everything Store");
      System.out.println("Choose from this options");
      System.out.println(".........................");
      System.out.println("[1] List all Users");
      System.out.println("[2] Search User by ID");
      System.out.println("[3] Add New User");
      System.out.println("[4] Update a User by ID");
      System.out.println("[5] Delete a User by ID");
      System.out.println("[6] Check Login");
      System.out.println("[7] Exit");
      System.out.println();

      userSelector = input.nextLine();

      switch (userSelector) {
        // GETTING ALL USER FROM DATABASE
        case "1":
          ArrayList<User> allusers = UserDAO.getAllUsers();
          for (int i = 0; i < allusers.size(); i++) {
            System.out.println(allusers.get(i));
          }

          break;

        // GETTING A SINGLE USER FROM DATABASE
        case "2":
          System.out.println("Enter User Id:");
          int id = input.nextInt();
          System.out.println(UserDAO.getUser(id));
          break;

        // ADDING A USER TO DATABASE
        case "3":
          System.out.println("Enter Username: ");
          String username = input.nextLine();

          System.out.println("Enter Email: ");
          String email = input.nextLine();

          System.out.println("Enter Password: ");
          String password = input.nextLine();

          System.out.println("Enter isAdmin: ");
          int isAdmin = input.nextInt();
          input.nextLine();

          User user = new User(0, username, email, password, isAdmin);
          boolean isAdded = UserDAO.addUser(user);
          if (isAdded) {
            System.out.println("User Added Successfully");
          } else {
            System.out.println("Could not add User");
          }
          break;

        // UPDATING A USER IN DATABASE
        case "4":
          System.out.println("Enter ID: ");

          int updateId = input.nextInt();
          input.nextLine();

          System.out.println("Enter username: ");
          String uUsername = input.nextLine();

          System.out.println("Enter Email: ");
          String uEmail = input.nextLine();

          System.out.println("Enter Password: ");
          String uPassword = input.nextLine();

          System.out.println("Enter Admin: ");
          int uIsAdmin = input.nextInt();
          input.nextLine();

          User updateUser = new User(updateId, uUsername, uEmail, uPassword, uIsAdmin);
          boolean isUpdated = UserDAO.updateUser(updateUser);

          if (isUpdated) {
            System.out.println("User Updated Successfully");
          } else {
            System.out.println("Could not update User");
          }
          break;

        // DELETING A CUSTOMER FROM DATABASE
        case "5":
          System.out.println("Enter User Id:");
          int deleteId = input.nextInt();
          boolean isDeleted = UserDAO.deleteUser(deleteId);
          if (isDeleted) {
            System.out.println("User Deleted Successfully");
          } else {
            System.out.println("Could not Delete User");
          }
          break;

        // logining a user in console testing
        case "6":

            System.out.println("Enter username: ");
            String User = input.nextLine();
            
            System.out.println("Enter Password: ");
            String Pass = input.nextLine();
            
            Boolean isUser = UserDAO.loginUser(User,Pass);
            if(isUser){
            System.out.println("You have logged in successfully");
            }else{
            System.out.println("Username or Password is incorrect");
            }

          break;

          
          // Exit
        case "7":
          System.out.println("Exiting....");
          System.out.println("Exit complete...");
          System.out.println();
          userSelector = "7";
          default:
          break;
      }

    } while (!userSelector.equals("7"));
  }
}
