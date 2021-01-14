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
  * builds a ticket object using the given parameters
  * @param t is a Theatre Object
  * @param m is a Movie Object
  * @param st is a Showtime Object
  * @param s is a Seat Object
  */
  public void buildTicket(Theatre t, Movie m, Showtime st, Seat s){
    theTicket = new Ticket (t, m, st, s);
  }

  /**
  * Books a ticket based on a previously built ticket. Also resets theCredit and theTicket
  * to null so that they are not reused. Finally updates the database with all relevant 
  * information and returns a string holding the text of an email the user will recieve.
  * @param emailAddress email address to send the payment receipt to.
  * @param totalCost: the total cost that the user is being charged after credit has
  * been applied.
  * @return String holding all the information necessary to send a receipt to the user. 
  */
  public String bookTicket(String emailAddress, double totalCost){

    if (theCredit != null){       // If credit was applied
      if(theCredit.getValue() > theTicket.getTheSeat().getCost()){
         DBControl.updateCredit(theCredit.getID(), theCredit.getValue() - theTicket.getTheSeat().getCost());
      }
      else{
        DBControl.removeCredit(theCredit.getID());
      }
      theCredit = null;
    }
    
    theTicket.getTheSeat().swapAvailability();
    DBControl.updateSeat(theTicket.getTheSeat(), 1);

    String ticketInfo = theTicket.getTheTheatre().getName() + " " + theTicket.getTheMovie() + " " + theTicket.getTheShowtime().toString() + " " + theTicket.getTheSeat().emailString() + '\n' +  "at a price of: " + totalCost;


    theEmail = new PurchaseEmail(emailAddress, theTicket.getTicketNumber(), ticketInfo);
    DBControl.addTicket(theTicket);

    theTicket = null;

    return theEmail.send();
  }

  /**
  * Retrieves a credit from the database, then sets it to variable theCredit.
  * @param String holding the id number of a credit in the database. 
  * @return the new cost after the credit is applied.
  */
  public double addCredit(String id){
    theCredit = new Credit(Integer.parseInt(id), DBControl.getCreditValue(id));
    return getPaymentAmount();
  }

/**
* Calculates if the listed price of a seat needs to be adjusted if a credit has 
* been entered.
* @return the total amount that the user will pay.
*/
  public double getPaymentAmount(){
    double price = theTicket.getTheSeat().getCost();
    if(theCredit != null){
      if(theCredit.getValue() > price){
        price = 0;
      }
      else{
        price = price - theCredit.getValue();
      }

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
    theTicket = DBControl.getTicket(TicketID);

    double value = theTicket.getTheSeat().getCost();
    if(! (theUser instanceof RegisteredUser)){          // If the user is not registered
      value = value * 0.85;
    }

    theTicket.getTheSeat().swapAvailability();
    DBControl.updateSeat(theTicket.getTheSeat(), 0);
    
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

  public Credit getTheCredit(){
    return theCredit;
  }

  
}