import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
* 
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
//GUI class for resigering new user
public class RegisterFrame extends JFrame {
  private JLabel title = new JLabel("Register for New Account");
  private JLabel f_name = new JLabel("First Name");
  private JTextField f_name_text = new JTextField(10);
  private JLabel l_name = new JLabel("Last Name");
  private JTextField l_name_text = new JTextField(10);
  private JLabel username = new JLabel("Username");
  private JTextField username_text = new JTextField(15);
  private JLabel email = new JLabel("E-mail");
  private JTextField email1_text = new JTextField(7);
  private JTextField email2_text = new JTextField(7);
  private JLabel password = new JLabel("Password");
  private JTextField password_text = new JTextField(15);
  private JLabel phone = new JLabel("Phone Number");
  private JTextField phone_text = new JTextField(15);
  private JLabel address = new JLabel("Address");
  private JTextField address_text = new JTextField(30);
  private JLabel card_type = new JLabel("Card Type");
  private JTextField card_type_text = new JTextField(5);
  private JLabel card = new JLabel("Card Number");
  private JTextField card_text = new JTextField(15);
  private JLabel expiry = new JLabel("Expiry Date");
  private JTextField expiry_text = new JTextField(10);
  private JButton cancel = new JButton ("Cancel");
  private JButton register = new JButton ("Register");

  public RegisterFrame(){
    super("Register New User");
    setSize(400,400);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //panel for title
    JPanel panel_title = new JPanel();
    panel_title.setLayout(new FlowLayout());
    panel_title.add(title);

    //first name
    JPanel panel_f_name = new JPanel();
		panel_f_name.setLayout(new FlowLayout());
		panel_f_name.add(f_name);
		panel_f_name.add(f_name_text);

    //last name
    JPanel panel_l_name = new JPanel();
		panel_l_name.setLayout(new FlowLayout());
		panel_l_name.add(l_name);
		panel_l_name.add(l_name_text);

    //username
    JPanel panel_username = new JPanel();
		panel_username.setLayout(new FlowLayout());
		panel_username.add(username);
		panel_username.add(username_text);
    
    //email
    JPanel panel_email = new JPanel();
		panel_email.setLayout(new FlowLayout());
		panel_email.add(email);
    panel_email.add(email1_text);
    panel_email.add(new JLabel("@"));
		panel_email.add(email2_text);

    //password
    JPanel panel_pass = new JPanel();
		panel_pass.setLayout(new FlowLayout());
		panel_pass.add(password);
		panel_pass.add(password_text);

    //phone
    JPanel panel_phone = new JPanel();
		panel_phone.setLayout(new FlowLayout());
		panel_phone.add(phone);
		panel_phone.add(phone_text);

    //address
    JPanel panel_address = new JPanel();
		panel_address.setLayout(new FlowLayout());
		panel_address.add(address);
		panel_address.add(address_text);

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

    //buttons
    JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());
		panel_buttons.add(cancel);
    panel_buttons.add(register);

    //adding above panels to a grid
    JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		panel_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_title);
		panel_f_name.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_f_name);
		panel_l_name.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_l_name);
    panel_username.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_username);
    panel_email.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_email);
    panel_pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_pass);
    panel_phone.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_phone);
    panel_address.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_address);
    panel_card_type.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_card_type);
		panel_card.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_card);
		panel_expiry.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_expiry);
    panel_buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel2.add(panel_buttons);
    
    //add to frame
		add(panel2,BorderLayout.CENTER);
    pack();
  }

  /**
		 * add listener for the cancel button
		 * @param a is the ActionListener object
		 */
		public void addCancelListener(ActionListener a) {
			this.cancel.addActionListener(a);
		}

    /**
		 * add listener for the register button
		 * @param a is the ActionListener object
		 */
		public void addRegisterListener(ActionListener a) {
			this.register.addActionListener(a);
		}

   /**
		 * returns first name as string
		 * @return
		 */
		public String getFirstName(){
			return getStringFromField(f_name_text);
		}

    /**
		 * returns last name as string
		 * @return
		 */
		public String getLastName(){
			return getStringFromField(f_name_text);
		}

    /**
		 * returns username as string
		 * @return
		 */
		public String getUsername(){
			return getStringFromField(username_text);
		}

    /**
		 * returns email as string
		 * @return
		 */
		public String getEmail(){
			return getStringFromField(email1_text) + "@" + getStringFromField(email2_text);
		}

    /**
		 * returns password as string
		 * @return
		 */
		public String getPassword(){
			return getStringFromField(password_text);
		}

    /**
		 * returns phone as string
		 * @return
		 */
		public String getPhone(){
			return getStringFromField(phone_text);
		}

    /**
		 * returns course name as string
		 * @return
		 */
		public String getAddress(){
			return getStringFromField(address_text);
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
		 * helper function for text field
		 * @param a is text field
		 * @return
		 */
		private String getStringFromField(JTextField a) {
			return a.getText();
		}
    
   /**
		 * reset all text fields
     * @param
		 * @return
		 */
  public void resetText() {
			f_name_text.setText("");
			l_name_text.setText("");
      username_text.setText("");
			email1_text.setText("");
      email2_text.setText("");
      password_text.setText("");
      phone_text.setText("");
      address_text.setText("");
      card_type_text.setText("");
			card_text.setText("");
			expiry_text.setText("");
		}

  public JButton getRegister() {
		return this.register;
	}

  public JButton getCancel() {
		return this.cancel;
	}
}