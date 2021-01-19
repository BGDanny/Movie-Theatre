/**
* Class representing a basic user. It contains variables allowing communication with
* the controllers. Its methods allow for a customer to pay for tickets, as well as 
* become a registered user.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class User{
  protected GUIController theGui;
  protected TheatreController theatreControl;
  protected DataBaseController DBControl;
  protected TicketController ticketControl;
  protected PaymentController payControl;

  /**
  * Constructor that takes in various controller objects and assignes them to the 
  * appropriate member variables.
  * @param tc TheatreController object that will be assigned to theatreControl.
  * @param dbc DataBaseController object that will be assigned to DBControl.
  * @param tktc TicketController object that will be assigned to ticketControl.
  * @param PaymentController object that will be assigned to payControl.
  */
  public User(TheatreController tc, DataBaseController dbc, TicketController tktc, PaymentController pc){
    theatreControl = tc;
    DBControl = dbc;
    ticketControl = tktc;
    payControl = pc;
  }

  public void choosePayMethod(){
    // Get regular user Pay info from GUI
    payControl.verifyPaymentInformation();
  }

  /**
  * Takes registration information about a customer so that they can become
  * a registered user. It then creates a new RegisteredUser object and sets theUser
  * member variables of ticketControl and theGui to the new object.
  * @param String holding the name of the customer registering as a registered user
  * @param String holding the address of the customer registering as a registered user 
  * @param String holding the email of a customer registering as a registered user 
  * @param String holding the phone number of a customer registering as a registered user 
  */
  public void register(String name, String add, String email, String phone, String cardType, String cardNumber, String expiryDate){
    float pn = Float.parseFloat(phone);
    payControl.setCardType(cardType);
    payControl.setCardNumber(cardNumber);
    payControl.setNameOnCard(name);
    payControl.setExpiryDate(expiryDate);
    RegisteredUser ru = new RegisteredUser(theatreControl, DBControl, ticketControl, payControl, name, add, email, pn);
    ticketControl.setTheUser(ru);
    theGui.setTheUser(ru);
    ru.assignGUI(theGui);
  }

  // Coming soon to a theatre near you!
  public void loggedIn(){

  }

  public void setPayAmount(double amount){
    payControl.setCost(amount);
  }

  public PaymentController getPaymentController(){
    return payControl;
  }

  public void assignGUI(GUIController gui){
    theGui = gui;
  }
}