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

  /**
  * Passes payment information to payControl.
  * @param type String holding the card type of the user.
  * @param cardNumber String holding the card number of the user.
  * @param expiryDate String holding the expiry date of the user's card.
  */
  public void choosePayMethod(String type, String cardNumber, String expiryDate){
    payControl.setCardType(type);
    payControl.setCardNumber(cardNumber);
    payControl.setExpiryDate(expiryDate);
  }

  /**
  * If the customer has entered valid payment information, makes a payment using the
  * ticket controller and returns true. If the user has entered invalid information,
  * returns false.
  * @return boolean representing if the payment went through or not.
  */
  public boolean tryPayment(){
    if(payControl.verifyPaymentInformation()){
      payControl.makePayment();
      return true;
    }
    return false;
  }

  /**
  * Takes registration information about a customer so that they can become
  * a registered user. It then creates a new RegisteredUser object and sets theUser
  * member variables of ticketControl and theGui to the new object.
  * @param names String holding the name of the customer registering as a registered user
  * @param add String holding the address of the customer registering as a registered user 
  * @param email String holding the email of a customer registering as a registered user 
  * @param phone String holding the phone number of a customer registering as a registered user 
  * @param cardType String holding the type of card the reistered use has.
  * @param cardNumber String holding the card number of the registered user.
  * @param expiryDate String holding the expiry date of the registered users credit or debit card.
  */
  public void register(String name, String add, String email, String phone, String cardType, String cardNumber, String expiryDate){
    float pn = Float.parseFloat(phone);
    RegisteredUser ru = new RegisteredUser(theatreControl, DBControl, ticketControl, payControl, name, add, email, pn, cardType, cardNumber, expiryDate);
    ticketControl.setTheUser(ru);
    theGui.setTheUser(ru);
    ru.assignGUI(theGui);
  }

  /**
  * Changes the type of User object that the controllers can see when the customer
  * enterers a valid Registered User loggin. Then gives the registered user access to
  * all of the controllers this user object has.
  * @param ru RegisteredUser object that will replace this object.
  */
  public void loggedIn(RegisteredUser ru){
    ticketControl.setTheUser(ru);
    theGui.setTheUser(ru);
    ru.assignGUI(theGui);
    ru.setTheatreControl(theatreControl);
    ru.setTicketControl(ticketControl);
    ru.setDBControl(DBControl);
    ru.setPayControl(payControl);
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

  public void setTheatreControl(TheatreController t){
    theatreControl = t;
  }

  public void setTicketControl(TicketController t){
    ticketControl = t;
  }

  public void setDBControl(DataBaseController dbc){
    DBControl = dbc;
  }

  public void setPayControl(PaymentController p){
    payControl = p;
  }
  
}