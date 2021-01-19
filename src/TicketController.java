/**
* Class responsible for managing all operations relating to customer tickets. It contains
* variables allowing communication to the other controller classes, and methods managing
* the booking and cancelation processes for tickets.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class TicketController{
  private GUIController theGUI;
  private User theUser;
  private Ticket theTicket;
  private Email theEmail;
  private DataBaseController DBControl;
  private Credit theCredit;

  /**
  * Constructor that takes in a DataBaseController object and sets it to variables
  * DBControl.
  * @param dbcon DataBaseController object to set DBControl to.
  */
  public TicketController(DataBaseController dbcon){
    DBControl = dbcon;
  }


  /**
  *
  */
  public String bookTicket(String emailAddress, Ticket theTicket){

    double cost = getPaymentAmount();
    theUser.setPayAmount(cost);
    theUser.choosePayMethod();

    //After user confirms getPaymentAmount
    //String credit is not used?
    //removeCredit() needs int ticketID as argument
    if(cost != theTicket.getTheSeat().getCost()){       // If credit was applied
      int credit = theCredit.getID();
      DBControl.removeCredit(credit);
      theCredit = null;
    }
    
    theTicket.getTheSeat().swapAvailability();
    DBControl.updateSeat(theTicket.getTheSeat());

    String ticketInfo = theTicket.getTheTheatre().getName() + " " + theTicket.getTheMovie() + " " + theTicket.getTheShowtime().toString() + " " + theTicket.getTheSeat().toString() + ": " + cost;


    theEmail = new PurchaseEmail(emailAddress, theTicket.getTicketNumber(), ticketInfo);
    DBControl.addTicket(theTicket);

    return theEmail.send();
  }

  /**
  * Retrieves a credit from the database, then sets it to variable theCredit.
  * @param String holding the id number of a credit in the database. 
  */
  public void addCredit(String id){
    theCredit = new Credit(Integer.parseInt(id), DBControl.getCreditValue(id));
  }

/**
* Calculates if the listed price of a seat needs to be adjusted if a credit has 
* been entered.
*/
  public double getPaymentAmount(){
    double price = theTicket.getTheSeat().getCost();
    if(theCredit != null){
      price = price - theCredit.getValue();
    }
    return price;
  }

  /**
  * Takes in the customers email address and the ID of a ticket that is to be cancelled.
  * It then gets the information about the ticket from the database, gets the cost of the
  * ticket, and creates a credit with the appropirate value. The method then removes the
  * ticket form the data base, adds the credit to the database, and sends an email to the
  * customer with the credit number. The user will be able to use this credit number the
  * next time they purchase a ticket to get a discount.
  * @param emailAddress String holding the email address of the customer.
  * @param id String hilding the ID of the ticket to be cancelled.
  * @return String containing the email the customer will receive.
  */
  public String cancelTicket(String emailAddress, String id){
    int TicketID = Integer.parseInt(id);
    //Get ticket ID somehow
    theTicket = DBControl.getTicket(TicketID);

    double value = theTicket.getTheSeat().getCost();
    if(! (theUser instanceof RegisteredUser)){          // If the user is not registered
      value = value * 0.85;
    }

    theTicket.getTheSeat().swapAvailability();
    DBControl.updateSeat(theTicket.getTheSeat());
    
    theCredit = new Credit(theTicket.getTicketNumber(), value);
    theEmail = new CreditEmail(emailAddress, theTicket.getTicketNumber(), theTicket.getTicketNumber(), value);
    
    DBControl.addCredit(theCredit);
    DBControl.removeTicket(theTicket.getTicketNumber());

     return theEmail.send();
  }


  public void setTheUser(User r){
    theUser = r;
    return;
  }

  public Ticket getTicket(){
    return this.theTicket;
  }

  
}