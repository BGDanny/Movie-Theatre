import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
* 
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class PaymentFrame extends JFrame {
  private JLabel title = new JLabel("Check Out");
  private JButton confirm = new JButton ("Confirm Payment");
  private JButton back = new JButton ("Back");
  private JButton cancel_ticket = new JButton("Cancel a Ticket");
  private JLabel card_type = new JLabel("Card Type");
  private JTextField card_type_text = new JTextField(5);
  private JLabel card = new JLabel("Card Number");
  private JTextField card_text = new JTextField(15);
  private JLabel expiry = new JLabel("Expiry Date");
  private JTextField expiry_text = new JTextField(10);
  private JLabel credit = new JLabel("Credit Code");
  private JTextField credit_text = new JTextField(15);
  private JLabel email = new JLabel("E-mail");
  private JTextField email1_text = new JTextField(7);
  private JTextField email2_text = new JTextField(7);
  private JLabel cost = new JLabel("Total Cost:");

  public PaymentFrame(){
    super("Payment");
    setSize(400,400);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //panel for title
    JPanel panel_title = new JPanel();
    panel_title.setLayout(new FlowLayout());
    panel_title.add(title);

    //card type
    JPanel panel_card_type = new JPanel();
		panel_card_type.setLayout(new FlowLayout());
		panel_card_type.add(card_type);
		panel_card_type.add(card_type_text);

    //card number
    JPanel panel_card = new JPanel();
		panel_card.setLayout(new FlowLayout());
		panel_card.add(card);
		panel_card.add(card_text);

    //expiry date
    JPanel panel_expiry = new JPanel();
		panel_expiry.setLayout(new FlowLayout());
		panel_expiry.add(expiry);
		panel_expiry.add(expiry_text);


    //ticket number
    JPanel panel_credit = new JPanel();
		panel_credit.setLayout(new FlowLayout());
		panel_credit.add(credit);
		panel_credit.add(credit_text);

    //email
    JPanel panel_email = new JPanel();
		panel_email.setLayout(new FlowLayout());
		panel_email.add(email);
    panel_email.add(email1_text);
    panel_email.add(new JLabel("@"));
		panel_email.add(email2_text);

    //buttons
    JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());
		panel_buttons.add(back);
    panel_buttons.add(cancel_ticket);
    panel_buttons.add(confirm);

    
    //adding above panels to a grid
    JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
    panel_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_title);
    panel_email.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel2.add(panel_email);
		panel_card_type.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_card_type);
		panel_card.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_card);
		panel_expiry.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_expiry);
    panel_credit.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel2.add(panel_credit);
    cost.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel2.add(cost);
    panel_buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_buttons);


    //add to frame
		add(panel2,BorderLayout.CENTER);
    pack();
  }
  /*
  public static void main (String []args){
      MainMenuFrame f = new MainMenuFrame();
      f.setVisible(true);
  }
  */

  /**
		 * add listener for the back button
		 * @param a is the ActionListener object
		 */
		public void addBackListener(ActionListener a) {
			this.back.addActionListener(a);
		}

    /**
		 * add listener for the confimr button
		 * @param a is the ActionListener object
		 */
		public void addConfirmListener(ActionListener a) {
			this.confirm.addActionListener(a);
		}
    /**
		 * add listener for the cancel ticket button
		 * @param a is the ActionListener object
		 */
		public void addCancelTicketListener(ActionListener a) {
			this.cancel_ticket.addActionListener(a);
		}

    public JButton getBack() {
			return this.back;
		}

    public JButton getConfirm() {
			return this.confirm;
		}

    public JButton getCancelTicket() {
			return this.cancel_ticket;
		}

    /**
		 * returns card type as string
		 * @return
		 */
		public String getCardType(){
			return getStringFromField(card_type_text);
		}

    /**
		 * returns card number as string
		 * @return
		 */
		public String getCard(){
			return getStringFromField(card_text);
		}

    /**
		 * returns expiry date as string
		 * @return
		 */
		public String getExpiryDate(){
			return getStringFromField(expiry_text);
		}

    /**
		 * returns credit as string
		 * @return
		 */
		public String getCreditCode(){
			return getStringFromField(credit_text);
		}

    /**
		 * returns email as string
		 * @return
		 */
		public String getEmail(){
			return getStringFromField(email1_text) + "@" + getStringFromField(email2_text);
		}

    /**
		 * helper function for text field
		 * @param a is text field
		 * @return
		 */
		private String getStringFromField(JTextField a) {
			return a.getText();
		}

    /**
		 * display Email 
		 * @param emailToSend is the email that the client receives
		 */
    
    public void displayEmail(String emailToSend){
      JOptionPane.showMessageDialog(this, emailToSend);
    }

    public void setCost(Double price){
      cost.setText("Total Cost: $" + Math.round((price*100)/100));
    }

    public void setRegisteredEmail(String email){
      String [] str = email.split("@");
      email1_text.setText(str[0]);
      email2_text.setText(str[0]);
    }
    
    /**
		 * set all text fields to null 
		 * @param 
		 */
    public void resetText() {
			card_type_text.setText("");
			card_text.setText("");
			expiry_text.setText("");
      email1_text.setText("");
      email2_text.setText("");
		}
}