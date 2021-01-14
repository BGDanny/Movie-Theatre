/**
 * Abstract class representing an email that will be sent to a customer.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public abstract class Email {
  protected String recipient;
  protected String subject;
  protected String message;
  protected int ticketID;

  /**
   * Constructor that takes in the email address of the recipiant and the ID of
   * the ticket being bought or cancelled, and sets them to the relevant
   * variables.
   */
  public Email(String recip, int ID) {
    recipient = recip;
    ticketID = ID;
  }

  /**
   * Abstract method that returns a string holding information about the email.
   * 
   * @return String holding the email in String form.
   */
  public abstract String send();
}