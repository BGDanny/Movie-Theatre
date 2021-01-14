/**
* Class that represents the email that will be sent to a customer after they cancel
* after a 
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class CreditEmail extends Email{
  private int creditID;
  private double creditValue;

  /**
  * Constructor that takes the email adress of the recipiant, the ID of the ticket
  * being cancelled, the ID of the credit the recipiant will get, and the value of
  * the credit. It then calls the parent constructor and sets the relevant variables.
  */
  public CreditEmail(String recip, int ID, int CID, double cv){
    super(recip, ID);
    creditID = CID;
    creditValue = cv;
  }
  
  @Override
  /**
  * Organizes information contained in the email and the message and subject of the email,
  * then returns this as a String.
  * @return String holding the entirety of the email's information, subject, and message.
  */
  public String send(){
    subject = "Ticket Cancellation Email" + '\n';
    message = "Your ticket with id " + ticketID + " has been cancelled. You have received a credit of " + creditValue + " with an ID of " + creditID + "." + '\n' + "This credit is valid for 1 year.";
    return subject + message;
  }
}