/**
 * Class representing a registered user. It contains variables that hold
 * information about the registered user. It also allows the registered user it
 * represents to pay their subscriber fees.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class RegisteredUser extends User {
  private String name;
  private float phoneNumber;
  private String address;
  private String emailAddress;
  private String type;
  private String cardNumber;
  private String expiryDate;

  /**
   * Constructor that takes in controller objects and information about a
   * customer, calls the parent class constructor, and assigns relavent member
   * variables.
   * 
   * @param tc    TheatreController object to be passed to the parent constructor.
   * @param dbc   DatBaseController object to be passed to the parent constructor.
   * @param tktc  TicketController object to be passed to the parent constructor.
   * @param pc    PaymentController object to be passed to the parent constructor.
   * @param n     String representing the name of the registered user.
   * @param add   String representing the address of the registered user.
   * @param email String representing the email of the registered user.
   * @param phone float representing the phone number of the registered user.
   * @param t     String indicating the type of card the registered user has.
   * @param cn    String holding the card number of the registered user.
   * @param ed    String holding the expiry date of the registered user's card.
   */
  public RegisteredUser(TheatreController tc, DataBaseController dbc, TicketController tktc, PaymentController pc,
      String n, String add, String email, float phone, String t, String cn, String ed) {
    super(tc, dbc, tktc, pc);
    name = n;
    address = add;
    emailAddress = email;
    phoneNumber = phone;
    type = t;
    cardNumber = cn;
    expiryDate = ed;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getName() {
    return name;
  }

  public float getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setType(String t) {
    type = t;
  }

  public String getType() {
    return type;
  }

  public void setCardNumber(String c) {
    cardNumber = c;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setExpiryDate(String e) {
    expiryDate = e;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

}