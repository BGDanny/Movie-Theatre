import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Main menu class, what users see when they first run the program
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */

// GUI class for main menu
public class MainMenuFrame extends JFrame {
	private JLabel cinema = new JLabel("Welcome to Cinema Name");
	private JButton guest = new JButton("Continue as guest");
	private JButton register = new JButton("Register");
	private JButton signin = new JButton("Sign in");
	private JButton cancel_ticket = new JButton("Cancel Ticket");
	private JLabel username = new JLabel("Username");
	private JTextField username_text = new JTextField(15);
	private JLabel password = new JLabel("Password");
	private JTextField password_text = new JTextField(15);

	public MainMenuFrame() {
		super("Main Menu");
		setSize(400, 400);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// panel for cinema Name
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(cinema);

		// username
		JPanel panel_username = new JPanel();
		panel_username.setLayout(new FlowLayout());
		panel_username.add(username);
		panel_username.add(username_text);

		// password
		JPanel panel_pass = new JPanel();
		panel_pass.setLayout(new FlowLayout());
		panel_pass.add(password);
		panel_pass.add(password_text);

		// panel for buttons
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(guest);
		panel2.add(register);
		panel2.add(signin);
		panel2.add(cancel_ticket);

		// panel for email and password
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel_username.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel3.add(panel_username);
		panel_pass.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel3.add(panel_pass);

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.SOUTH);
		add(panel3, BorderLayout.CENTER);
		pack();
	}

	/**
	 * add listener for the guest button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addGuestListener(ActionListener a) {
		this.guest.addActionListener(a);
	}

	/**
	 * add listener for the register button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addRegisterListener(ActionListener a) {
		this.register.addActionListener(a);
	}

	/**
	 * add listener for the signin button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addSignInListener(ActionListener a) {
		this.signin.addActionListener(a);
	}

	/**
	 * add listener for the cancel ticket button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addCancelTicketListener(ActionListener a) {
		this.cancel_ticket.addActionListener(a);
	}

	/**
	 * returns username as string
	 * 
	 * @return
	 */
	public String getUsername() {
		return getStringFromField(username_text);
	}

	/**
	 * returns password as string
	 * 
	 * @return
	 */
	public String getPassword() {
		return getStringFromField(password_text);
	}

	/**
	 * helper function for text field
	 * 
	 * @param a is text field
	 * @return
	 */
	private String getStringFromField(JTextField a) {
		return a.getText();
	}

	/**
	 * set all text fields to blank
	 * 
	 * @return
	 */
	public void resetText() {
		username_text.setText("");
		password_text.setText("");
	}

	public JButton getCancelTicket() {
		return this.cancel_ticket;
	}

	public JButton getRegister() {
		return this.register;
	}

	public JButton getSignIn() {
		return this.signin;
	}

	public JButton getGuest() {
		return this.guest;
	}
}