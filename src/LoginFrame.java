// also displays email
//keeps track of credits
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
* This class displays the User's profile
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class LoginFrame extends JFrame {
  private JLabel title = new JLabel("User Profile");
  private JLabel greeting = new JLabel("Hello,");
  private JButton announcement = new JButton ("Announcements");
  private JButton book = new JButton ("Book Ticket");
  private JButton cancel_ticket = new JButton("Cancel Ticket");
  private String announce = "The Dark Knight Returns. Pre-order Tickets Now!";

  public LoginFrame(){
    super("Profile");
    setSize(400,400);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //user info
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.add(greeting,BorderLayout.WEST);

    //panel for title
    JPanel panel_title = new JPanel();
    panel_title.setLayout(new FlowLayout());
    panel_title.add(title);

    //buttons
    JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());
		panel_buttons.add(announcement);
    panel_buttons.add(book);
    panel_buttons.add(cancel_ticket);

    
    //adding above panels to a grid
    JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
    panel_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_title);
		panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel1);
		panel_buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel2.add(panel_buttons);
    

    //add to frame
    add(panel2);
    pack();
  }


  /**
		 * add listener for the announcement button
		 * @param a is the ActionListener object
		 */
		public void addAnnouncementListener(ActionListener a) {
			this.announcement.addActionListener(a);
		}

    /**
		 * add listener for the book ticket button
		 * @param a is the ActionListener object
		 */
		public void addBookListener(ActionListener a) {
			this.book.addActionListener(a);
		}

    /**
		 * add listener for the cancel ticket button
		 * @param a is the ActionListener object
		 */
		public void addCancelTicketListener(ActionListener a) {
			this.cancel_ticket.addActionListener(a);
		}

    /**
		 * display announcement 
		 * @param 
		 */
    public void displayAnnouncement(){
      JOptionPane.showMessageDialog(this, announce);
    }

   /**
		 * display the greeting to user's name
		 * @param a is the User's name
		 */
    public void setGreeting(String name) {
			greeting.setText("Hello, " + name  + "!");
		}

    public void setAnnouncements(String a){
      announce = a;
    }

        public JButton getCancelTicket() {
			return this.cancel_ticket;
		}

    public JButton getBook() {
			return this.book;
		}

    public JButton getAnnouncementButton() {
			return this.announcement;
		}
}