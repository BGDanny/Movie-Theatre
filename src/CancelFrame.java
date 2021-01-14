import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
* This class performs cancellation on a ticket after user enters a ticket number
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class CancelFrame extends JFrame {
  //declaring member variables
  private JLabel title = new JLabel("Cancel Purchase");
  private JButton confirm = new JButton ("Confirm Cancel");
  private JButton back = new JButton ("Back");
  private JLabel ticket_number = new JLabel("Ticket Number");
  private JTextField ticket_number_text = new JTextField(15);
  private JLabel email = new JLabel("E-mail");
  private JTextField email1_text = new JTextField(7);
  private JTextField email2_text = new JTextField(7);

  public CancelFrame(){
    super("Cancel Ticket");
    setSize(400,400);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //panel for title
    JPanel panel_title = new JPanel();
    panel_title.setLayout(new FlowLayout());
    panel_title.add(title);

    //ticket number
    JPanel panel_ticket_number = new JPanel();
		panel_ticket_number.setLayout(new FlowLayout());
		panel_ticket_number.add(ticket_number);
		panel_ticket_number.add(ticket_number_text);

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
    panel_buttons.add(confirm);

    
    //adding above panels to a grid
    JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
    panel_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_title);
		panel_ticket_number.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_ticket_number);
		panel_email.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_email);
    panel_buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_buttons);


    //add to frame
		add(panel2,BorderLayout.CENTER);
    pack();
  }

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
		 * returns card type as string
		 * @return
		 */
		public String getTicketNumber(){
			return getStringFromField(ticket_number_text);
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

    /**
		 * setting the email text fields with an email
		 * @param email is the email
		 * @return
		 */
    public void setRegisteredEmail(String email){
      String [] str = email.split("@");
      email1_text.setText(str[0]);
      email2_text.setText(str[1]);
    }


    /**
		 * set all text fields to null 
		 * @param 
		 */
    public void resetText() {
			ticket_number_text.setText("");
			email1_text.setText("");
      email2_text.setText("");
		}


    public JButton getBack() {
			return this.back;
		}

    public JButton getConfirm() {
			return this.confirm;
		}
}