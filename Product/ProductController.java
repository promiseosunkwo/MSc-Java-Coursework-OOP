/**
 * @author Promise Osunkwo 22504972
 * @version 1.0
 * @return console testing for product methods
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ProductController {

  public static void controller() {
    Scanner input = new Scanner(System.in);

    String userSelector;

    do {

      System.out.println(".........................");
      System.out.println("The Everything Store");
      System.out.println("Choose from this options");
      System.out.println(".........................");
      System.out.println("[1] List all Products");
      System.out.println("[2] Search Product by ID");
      System.out.println("[3] Add New Product");
      System.out.println("[4] Update a Product by ID");
      System.out.println("[5] Delete a Product by ID");
      System.out.println("[6] Filter Product by Price");
      System.out.println("[7] Search Product by any paramenter");
      System.out.println("[8] Exit");
      System.out.println();

      userSelector = input.nextLine();

      switch (userSelector) {
        case "1":

          ArrayList<Product> allProducts = ProductDAO.getAllProducts();
          for (int i = 0; i < allProducts.size(); i++) {
            System.out.println(allProducts.get(i));
          }

          break;

        // GETTING A SINGLE PRODUCT FROM DATABASE
        case "2":
          System.out.println("Enter Product Id:");
          int id = input.nextInt();
          System.out.println(ProductDAO.getProduct(id));
          break;

        // ADDING A PRODUCT TO DATABASE
        case "3":
          System.out.println("Enter Sku: ");
          String sku = input.nextLine();

          System.out.println("Enter Description: ");
          String description = input.nextLine();

          System.out.println("Enter Category: ");
          String category = input.nextLine();

          System.out.println("Enter Price: ");
          int price = input.nextInt();

          Product product = new Product(0, sku, description, category, price);
          ProductDAO.addProduct(product);
          break;

        // UPDATE PRODUCT
        case "4":
          int updateId = input.nextInt();
          input.nextLine();

          String updateSku = input.nextLine();

          System.out.println("Enter Descrption: ");
          String updateDescription = input.nextLine();

          System.out.println("Enter Category: ");
          String updateCategory = input.nextLine();

          System.out.println("Enter Price: ");
          int updatePrice = input.nextInt();

          Product updateProduct = new Product(updateId, updateSku, updateDescription, updateCategory, updatePrice);
          ProductDAO.updateProduct(updateProduct);
          break;

        // DELETING A PRODUCT FROM DATABASE
        case "5":
          int deleteId = input.nextInt();
          input.nextLine();
          ProductDAO.deleteProduct(deleteId);
          break;

        //  FILTERING A PRODUCT FROM DATABASE BY PRICE IN DESCENDING ORDER
        case "6":
          System.out.println("Enter Price Range: ");
          int priceRange = input.nextInt();
          System.out.println(ProductDAO.filterProducts(priceRange));

          break;

          // SEARCHING A PRODUCT FROM DATABASE
        case "7":
          System.out.println("Enter Search: ");
          String searchRange = input.nextLine();
          System.out.println(ProductDAO.searchProducts(searchRange));

          break;


        // EXIT TERMINAL
        case "8":
          System.out.println("Exiting....");
          System.out.println("Exit complete...");
          System.out.println();
          userSelector = "8";
          default:
          break;

      }

    } while (!userSelector.equals("8"));
  }
}
