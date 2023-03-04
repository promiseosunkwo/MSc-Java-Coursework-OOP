/**
 * The Customer class contains personal details of a customer
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */
public class Customer {
  private int id;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
  private Address address;


  /**
   * @param id contains autoincremented unique id
   * @param first name of customer
   * @param lastname of customer  
   * @param email of customer 
   * @param phone of customer
   * @param address class of customer 
   */
  public Customer(int id, String firstname, String lastname, String email, String phone, Address address) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.address = address;

  }

   /**
   * @return id 
   */
  public int getId() {
    return this.id;
  }

  /**
   * @return firstname 
   */
  public String getFirstName() {
    return this.firstname;
  }

   /**
   * @param firstname
   */
  public void setFirstName(String firstname) {
    this.firstname = firstname;
  }

  /**
   * @return lastname 
   */
  public String getLastName() {
    return this.lastname;
  }

  /**
   * @param lastname
   */
  public void setLastName(String lastname) {
    this.lastname = lastname;
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
   * @return phone
   */
  public String getPhone() {
    return this.phone;
  }

  /**
   * @param phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @address class 
   */
  public Address getAddress() {
    return this.address;
  }

  /**
   * @param address class
   */
  public void setAddress(Address address) {
    this.address = address;
  }


  /**
   * @return coverts values to string
   */
  @Override
  public String toString() {
    return "Customer  [id=" + this.id + "," + " firstname =" + "'" + this.firstname + "'" + "," + " lastname =" + "'"
        + this.lastname + "'" + "," + " email =" + "'" + this.email + "'" + "," + " phone =" + this.phone + "'" + ","
        + this.address + "'" + "]";
  }

}