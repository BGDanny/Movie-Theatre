/**
* Controller class responsible for managing payments for tickets and subscriber fees.
* It verifies valid payment information, and makes payments from user to the owners
* of the system.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class PaymentController {
  private String type;
  private String cardNumber;
  private String nameOnCard;
  private String expiryDate;
  private double cost;

  public PaymentController(){
    type = null;
    cardNumber = null;
    nameOnCard = null;
    expiryDate = null;
    cost = 0;
  }

  public boolean verifyPaymentInformation(){
    if(cardNumber.length()!=16){
      return false;
    }
    for(int i=0;i<16;i++){
       if(!Character.isDigit(cardNumber.charAt(i))){
        return false;
      }
    }
    for(int i=0;i<nameOnCard.length();i++){
      if(!Character.isLetter(nameOnCard.charAt(i))){
        return false;
      }
    }
    if(expiryDate.length()!=4){
      return false;
    }
    for(int i=0;i<expiryDate.length();i++){
      if(!Character.isDigit(expiryDate.charAt(i))){
        return false;
      }
    }
    return true;
  }

  public double getCost(){
    return cost;
  }

  public void setCost(double c){
    cost = c;
    return;
  }

  public void makePayment() {
    
    //pretend to make a payment like displaying a message saying "you've made a payment of $(cost)" 

  }

  public String getType(){
    return type;
  }

  public String getCardNumber(){
    return cardNumber;
  }

  public String getNameOnCard(){
    return nameOnCard;
  }

  public String getExpiryDate(){
    return expiryDate;
  }

  public void setCardType(String t){
    type = t;
  }

  public void setCardNumber(String num){
    cardNumber = num;
  }

  public void setNameOnCard(String name){
    nameOnCard = name;
  }

  public void setExpiryDate(String date){
    expiryDate = date;
  }
}