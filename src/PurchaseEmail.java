/**
 * Class representing the email that is sent to a customer when they purchase a
 * ticket. The customer receives all of the relevant information about the
 * ticket that they purchased.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class PurchaseEmail extends Email {
  private String paymentReceipt;

  /**
   * Constructor that takes in the email address, ticket ID, and payment receipt
   * information, calls the parent constructor, and assigns relevant variables.
   */
  public PurchaseEmail(String recip, int ID, String pr) {
    super(recip, ID);
    paymentReceipt = pr;
  }

  @Override
  /**
   * Organizes information contained in the email and the message and subject of
   * the email, then returns this as a String.
   * 
   * @return String holding the entirety of the email's information, subject, and
   *         message.
   */
  public String send() {
    subject = "Ticket Purchase Email" + '\n';
    message = "You have purchased a ticket with id " + ticketID + ". This ticket is for the following: " + '\n'
        + paymentReceipt;
    return subject + message;
  }

}