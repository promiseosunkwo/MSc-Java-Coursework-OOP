/**
 * This class models the product
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

public class Product {
  private int id;
  private String sku;
  private String description;
  private String category;
  private int price;

  /**
   * @param id          the autoincremented unique id
   * @param sku         unique number of the product
   * @param description describes the product
   * @param category    of where the product falls under
   * @param price       how much the product is worth
   */

  public Product(int id, String sku, String description, String category, int price) {
    this.id = id;
    this.sku = sku;
    this.description = description;
    this.category = category;
    this.price = price;
  }

  /**
   * @return autoincrement id of the product
   */
  public int getId() {
    return this.id;
  }

  /**
   * @return sku of the product
   */
  public String getSku() {
    return this.sku;
  }

  /**
   * @param sku of the product
   */
  public void setSku(String sku) {
    this.sku = sku;
  }

  /**
   * @return sku of the product
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @params description of the product
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return sku of the product
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * @param category of the product
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return sku of the product
   */
  public int getPrice() {
    return this.price;
  }

  /**
   * @param price of the product
   */
  public void setPrice(int price) {
    this.price = price;
  }


   /**
   * @return  values as string
   */
  @Override
  public String toString() {
    return "Product  [id=" + this.id + "," + " SKU=" + "'" + this.sku + "'" + "," + " Description=" + "'"
        + this.description
        + "'" + "," + " Category="
        + "'" + this.category + "'" + ","
        + " Price=" + this.price + "]";
  }

}