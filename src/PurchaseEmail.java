/**
* Class representing the email that is sent to a customer when they purchase a ticket.
* The customer receives all of the relevant information about the ticket that they
* purchased.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class PurchaseEmail extends Email{
  private String paymentReceipt;

  public PurchaseEmail(String recip, int ID, String pr){
    super(recip, ID);
    paymentReceipt = pr;
  }

  //add return 
  @Override
  public String send(){
    subject = "Ticket Purchase Email" + '\n';
    message = "You have purchased a ticket with id " + ticketID + ". This ticket is for the following: " + '\n' + paymentReceipt;
    return subject + message;
  }

}