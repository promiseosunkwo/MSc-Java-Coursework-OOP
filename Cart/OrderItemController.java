
/**
 * @author Promise Osunkwo 22504972
 * @version 1.0
 * @return console testing for cart methods
 */

import java.util.ArrayList;
import java.util.Scanner;

public class OrderItemController {

  public static void OrderItemController() {
    Scanner input = new Scanner(System.in);

    String userSelector;

    do {

      System.out.println(".........................");
      System.out.println("The Everything Store");
      System.out.println("Choose from this options");
      System.out.println(".........................");
      System.out.println("[1] List all cart items");
      System.out.println("[2] Add cart item");
      System.out.println("[3] Increment cart item");
      System.out.println("[4] Decrement cart item");
      System.out.println("[5] Delete cart item");
      System.out.println("[6] Exit");
      System.out.println();

      userSelector = input.nextLine();

      switch (userSelector) {
        // GETTING ALL CART ITEMS FROM DATABASE
        case "1":
          System.out.println("Input Customer id: ");
          int custId = input.nextInt();
          input.nextLine();

          ArrayList<OrderItem> allorders = OrderItemDAO.getAllCartItems(custId);
          for (int i = 0; i < allorders.size(); i++) {
            System.out.println(allorders.get(i));
          }

          break;

        // ADD CART ITEM TO DATABASE
        case "2":
          System.out.println("Input Product id: ");
          int productId = input.nextInt();
          input.nextLine();

          System.out.println("Input Customer id: ");
          int CustomerId = input.nextInt();
          input.nextLine();

          System.out.println("Quantity: ");
          int quantity = input.nextInt();
          input.nextLine();

          OrderItem orders = new OrderItem(0, productId, CustomerId, quantity);
          boolean insertorders = OrderItemDAO.insertToCart(orders);

          if (insertorders) {
            System.out.println("Cart item Inserted");
          } else {
            System.out.println("Could not insert cart item");
          }

          break;

        // INCREMENT CART ITEM TO DATABASE
        case "3":
          System.out.println("Input Order id: ");
          int oId = input.nextInt();
          input.nextLine();
          System.out.println("Input Customer id: ");
          int cId = input.nextInt();
          input.nextLine();

          System.out.println("Quantity: ");
          int qty = input.nextInt();
          input.nextLine();

          Boolean increase = OrderItemDAO.incrementCart(oId, cId, qty);
          if (increase) {
            System.out.println("Cart item incremented");
          } else {
            System.out.println("Could not increment");
          }
          break;

        // DECREMENT CART ITEM
        case "4":
          System.out.println("Input Order id: ");
          int doId = input.nextInt();
          input.nextLine();
          System.out.println("Input Customer id: ");
          int dcId = input.nextInt();
          input.nextLine();

          System.out.println("Quantity: ");
          int dqty = input.nextInt();
          input.nextLine();

          Boolean descrease = OrderItemDAO.decrementCart(doId, dcId, dqty);
          if (descrease) {
            System.out.println("Cart item Decremented");
          } else {
            System.out.println("Could not Decrement");
          }
          break;

        // DELETING A Cart Item FROM DATABASE
        case "5":
          System.out.println("Input orderitem id: ");
          int orderId = input.nextInt();
          input.nextLine();

          Boolean isDeleted = OrderItemDAO.deleteCartItem(orderId);
          if (isDeleted) {
            System.out.println("Cart item Deleted");
          } else {
            System.out.println("Could not delete cart item");
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
