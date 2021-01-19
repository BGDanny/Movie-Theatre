/**
* Abstract class representing an email that will be sent to a customer.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public abstract class Email{
  protected String recipient;
  protected String subject;
  protected String message;
  protected int ticketID;

  public Email(String recip, int ID){
    recipient = recip;
    ticketID = ID;
  }

  //send the email to the user
  public abstract String send();
}