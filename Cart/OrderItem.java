/**
 * This class models the cart items
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

public class OrderItem {
  private int id;
  private int productId;
  private int customerId;
  private int quantity;

  /**
   * @param id          the autoincremented unique id
   * @param productId   id of the product
   * @param customerId  id of the customer
   * @param quantity    quantity of item added
   */
  public OrderItem(int id, int productId, int customerId, int quantity) {
    this.id = id;
    this.productId = productId;
    this.customerId = customerId;
    this.quantity = quantity;

  }

  /**
   * @return autoincrement id of the order
   */
  public int getId() {
    return this.id;
  }

   /**
   * @return product id of the the product
   */
  public int getProductId() {
    return this.productId;
  }

  /**
   * @params product id of the the product
   */
  public void setProductId(int productId) {
    this.productId = productId;
  }

   /**
   * @return customer id of the the product
   */
  public int getCustomerId() {
    return this.customerId;
  }

  /**
   * @params customer id of the the product
   */
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  /**
   * @return quantity of the the product
   */
  public int getQuantity() {
    return this.quantity;
  }

   /**
   * @params quantity of the the product
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  /**
   * @return  values as string
   */
  @Override
  public String toString() {
    return "Cart  [id=" + this.id + "," + "ProductId=" + "'" + this.productId + "'" + "," + " CustomerId=" + "'"
        + this.customerId
        + "'" + "," + " Quantity ="
        + "'" + this.quantity + "'" + "]";
  }

}