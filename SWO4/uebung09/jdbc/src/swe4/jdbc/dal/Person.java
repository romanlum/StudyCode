package swe4.jdbc.dal;

/**
 * Person data transfer object (DTO)
 */
public class Person {

  private int    id;         // -1: object not stored yet
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;

  /**
   * private constructor: needs an id
   */
  public Person(int id, String firstName, String lastName, String address, String phoneNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
  } 

  public Person(String firstName, String lastName, String address, String phoneNumber) {
    this(-1, firstName, lastName, address, phoneNumber);
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String fn) { firstName = fn; } 
  public String getLastName() { return lastName; }
  public void setLastName(String ln) { lastName = ln; }
  public String getAddress() { return address; }
  public void setAddress(String a) { address = a; }
  public String getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(String pn) { phoneNumber = pn; } 

  public String toString() {
    return String.format("(%s) %s %s; %s; %s", id, firstName, lastName, address, phoneNumber);
  } // toString
}
