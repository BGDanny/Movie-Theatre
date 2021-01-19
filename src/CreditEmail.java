/**
* Class that represents the email that will be sent to a customer after they cancel
* after a 
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class CreditEmail extends Email{
  private int creditID;
  private double creditValue;

  public CreditEmail(String recip, int ID, int CID, double cv){
    super(recip, ID);
    creditID = CID;
    creditValue = cv;
  }

  @Override
  public String send(){
    subject = "Ticket Cancellation Email" + '\n';
    message = "Your ticket with id " + ticketID + " has been cancelled. You have received a credit of " + creditValue + " with an ID of " + creditID;
    return subject + message;
  }
}