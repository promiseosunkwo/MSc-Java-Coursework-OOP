/**
 * @author Promise Osunkwo 22504972
 * @version 1.0
 * @return console testing for customer methods
 */

import java.util.ArrayList;
import java.util.Scanner;
   
   public class customerController {

  public static void customerController() {
    Scanner input = new Scanner(System.in);

    String userSelector;

    do {

      System.out.println(".........................");
      System.out.println("The Everything Store");
      System.out.println("Choose from this options");
      System.out.println(".........................");
      System.out.println("[1] List all Customer");
      System.out.println("[2] Search Customer by ID");
      System.out.println("[3] Add New Customer");
      System.out.println("[4] Update a Customer by ID");
      System.out.println("[5] Delete a Customer by ID");
      System.out.println("[6] Exit");
      System.out.println();

      userSelector = input.nextLine();

      switch (userSelector) {
        // GETTING ALL CUSTOMER FROM DATABASE
        case "1":
          ArrayList<Customer> allcustomers = CustomerDAO.getAllCustomers();
          for (int i = 0; i < allcustomers.size(); i++) {
            System.out.println(allcustomers.get(i));
          }

          break;

        // GETTING A SINGLE CUSTOMER FROM DATABASE
        case "2":
          System.out.println("Enter Customer Id:");
          int id = input.nextInt();
          System.out.println(CustomerDAO.getCustomer(id));
          break;

        // ADDING A CUSTOMER TO DATABASE
        case "3":
          System.out.println("Enter First Name: ");
          String firstname = input.nextLine();

          System.out.println("Enter Last Name: ");
          String lastname = input.nextLine();

          System.out.println("Enter Email: ");
          String email = input.nextLine();

          System.out.println("Enter Phone: ");
          String phone = input.nextLine();

          System.out.println("Enter House Number: ");
          String house = input.nextLine();

          System.out.println("Enter Address Line 1: ");
          String address1 = input.nextLine();

          System.out.println("Enter Address Line 2 (optional): ");
          String address2 = input.nextLine();

          System.out.println("Enter Country: ");
          String country = input.nextLine();

          System.out.println("Enter Post Code: ");
          String postcode = input.nextLine();

          Address address = new Address(house, address1, address2, country, postcode);
          Customer customer = new Customer(0, firstname, lastname, email, phone, address);
          boolean isAdded = CustomerDAO.addCustomer(customer);
          if (isAdded) {
            System.out.println("Customer Added Successfully");
          } else {
            System.out.println("Could not add Customer");
          }
          break;

        // UPDATING A CUSTOMER IN DATABASE
        case "4":
          System.out.println("Enter ID: ");

          int updateId = input.nextInt();
          input.nextLine();

          System.out.println("Enter First Name: ");
          String uFirstname = input.nextLine();

          System.out.println("Enter Last Name: ");
          String uLastname = input.nextLine();

          System.out.println("Enter Email: ");
          String uEmail = input.nextLine();

          System.out.println("Enter Phone: ");
          String uPhone = input.nextLine();

          System.out.println("Enter House Number: ");
          String uHouse = input.nextLine();

          System.out.println("Enter Address Line 1: ");
          String uAddress1 = input.nextLine();

          System.out.println("Enter Address Line 2 (optional): ");
          String uAddress2 = input.nextLine();

          System.out.println("Enter Country: ");
          String uCountry = input.nextLine();

          System.out.println("Enter Post Code: ");
          String uPostcode = input.nextLine();

          Address updatedAddress = new Address(uHouse, uAddress1, uAddress2, uCountry, uPostcode);
          Customer updateCustomer = new Customer(updateId, uFirstname, uLastname, uEmail, uPhone, updatedAddress);
          boolean isUpdated = CustomerDAO.updateCustomer(updateCustomer);

          if (isUpdated) {
            System.out.println("Customer Updated Successfully");
          } else {
            System.out.println("Could not update Customer");
          }
          break;

        // DELETING A CUSTOMER FROM DATABASE
        case "5":
          System.out.println("Enter Customer Id:");
          int deleteId = input.nextInt();
          boolean isDeleted = CustomerDAO.deleteCustomer(deleteId);
          if (isDeleted) {
            System.out.println("Customer Deleted Successfully");
          } else {
            System.out.println("Could not Delete Customer");
          }
          break;

        // EXIT TERMINAL
        case "6":
          System.out.println("Exiting....");
          System.out.println("Exit complete...");
          System.out.println();
          userSelector = "6";
          default:
          break;

      }

    } while (!userSelector.equals("6"));
  }
}
