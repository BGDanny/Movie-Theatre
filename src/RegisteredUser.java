/**
* Class representing a registered user. It contains variables that hold information about
* the registered user. It also allows the registered user it represents to pay their 
* subscriber fees.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class RegisteredUser extends User{
  private String name;
  private float phoneNumber;
  private String address;
  private String emailAddress;

  /**
  * Constructor that takes in controller objects and information about a customer,
  * calls the parent class constructor, and assigns relavent member variables.
  * @param tc TheatreController object to be passed to the parent constructor.
  * @param dbc DatBaseController object to be passed to the parent constructor.
  * @param tktc TicketController object to be passed to the parent constructor.
  * @param pc PaymentController object to be passed to the parent constructor.
  * @param n String representing the name of the registered user.
  * @param add String representing the address of the registered user.
  * @param email String representing the email of the registered user.
  * @param phone float representing the phone number of the registered user.
  */
  public RegisteredUser(TheatreController tc, DataBaseController dbc, TicketController tktc, PaymentController pc, String n, String add, String email, float phone){
    super(tc, dbc, tktc, pc);
    name = n;
    address = add;
    emailAddress = email;
    phoneNumber = phone;
  }

  /**
  * Prompts the registered to pay their fee. Called after a new user registers.
  */
  public void payUserFee(){
    setPayAmount(20.00);
    choosePayMethod();
  }

  public String getEmailAddress(){
    return emailAddress;
  }

  public String getName(){
    return name;
  }

  public float getPhoneNumber(){
    return phoneNumber;
  }

  public String getAddress(){
    return address;
  }

}