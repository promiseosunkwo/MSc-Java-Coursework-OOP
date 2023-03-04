
/**
 * The Address class contains address fields of a customer
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */

public class Address {
  private String house;
  private String address1;
  private String address2;
  private String country;
  private String postcode;

  
  /**
   * @param house contains house number
   * @param address1 is the main residing address 
   * @param address2 is the secondary residing address  
   * @param postcode of residence 
   */

  public Address(String house, String address1, String address2, String country, String postcode) {
    this.house = house;
    this.address1 = address1;
    this.address2 = address2;
    this.country = country;
    this.postcode = postcode;
  }


  /**
   * @return house number 
   */
  public String getHouse() {
    return this.house;
  }

  /**
   * @param house number  
   */
  public void setHouse(String house) {
    this.house = house;
  }

  /**
   * @return main address 
   */
  public String getAddress1() {
    return this.address1;
  }

  /**
   * @param main address  
   */
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  /**
   * @return secondary address 
   */
  public String getAddress2() {
    return this.address2;
  }

  /**
   * @param secondary address 
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * @return country
   */
  public String getCountry() {
    return this.country;
  }

  /**
   * @param country  
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return postcode 
   */
  public String getPostcode() {
    return this.postcode;
  }

  /**
   * @param postcode  
   */
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }


  /**
   * @return coverts values to string
   */
  @Override
  public String toString() {
    return "Address  [house =" + "'" + this.house + "'" + "," + " address line 1 =" + "'" + this.address1
        + "'" + "," + " address line 2 =" + "'" + this.address2 + "'" + "," + " country =" + "'" + this.country + "'"
        + "," + " postcode =" + this.postcode + "]";
  }

}