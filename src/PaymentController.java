/**
 * Controller class responsible for managing payments for tickets and subscriber
 * fees. It verifies valid payment information, and makes payments from user to
 * the owners of the system.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class PaymentController {
  private String type;
  private String cardNumber;
  private String expiryDate;
  private double cost;

  /**
   * Constructor that creates and empty object, and sets the cost to 0.
   */
  public PaymentController() {
    type = null;
    cardNumber = null;
    expiryDate = null;
    cost = 0;
  }

  /**
   * returns true if the payment info entered is formatted right, returns false
   * otherwise
   * 
   * @return boolean indicating if the payment information was verified
   *         successfully.
   */
  public boolean verifyPaymentInformation() {
    if (cardNumber.length() != 16) {
      return false;
    }
    for (int i = 0; i < 16; i++) {
      if (!Character.isDigit(cardNumber.charAt(i))) {
        return false;
      }
    }
    if (expiryDate.length() != 4) {
      return false;
    }
    for (int i = 0; i < expiryDate.length(); i++) {
      if (!Character.isDigit(expiryDate.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double c) {
    cost = c;
    return;
  }

  /**
   * Simulates making a payment.
   */
  public void makePayment() {
    // Contacting bank server... payment success!
  }

  public String getType() {
    return type;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setCardType(String t) {
    type = t;
  }

  public void setCardNumber(String num) {
    cardNumber = num;
  }

  public void setExpiryDate(String date) {
    expiryDate = date;
  }
}