import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class SelectionFrame extends JFrame {
	private JLabel title = new JLabel("CineFlex");
	private JButton confirm = new JButton("Confirm Booking");
	private JButton back = new JButton("Back");
	private JLabel theatre = new JLabel("Choose a Theatre");
	private JLabel movie = new JLabel("Choose a Movie");
	private JLabel times = new JLabel("Choose a Time");
	private JLabel seat = new JLabel("Choose a Seat");
	private String[] theatre_list = new String[0];
	private String[] movie_list = new String[0];
	private String[] time_list = new String[0];
	private String[] seat_list = new String[0];
	private JButton confirm_theatre = new JButton("Accept");
	private JButton confirm_movie = new JButton("Accept");
	private JButton confirm_showtime = new JButton("Accept");
	private JButton confirm_seat = new JButton("Accept");
	private JComboBox<String> theatre_choice = new JComboBox<String>(theatre_list);
	private JComboBox<String> movie_choice = new JComboBox<String>(movie_list);
	private JComboBox<String> time_choice = new JComboBox<String>(time_list);
	private JComboBox<String> seat_choice = new JComboBox<String>(seat_list);
	private JLabel cost = new JLabel("Total Cost:");

	public SelectionFrame() {
		super("Book Ticket");
		setSize(400, 400);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// panel for title
		JPanel panel_title = new JPanel();
		panel_title.setLayout(new FlowLayout());
		panel_title.add(title);

		// theatre
		JPanel panel_theatre_list = new JPanel();
		panel_theatre_list.setLayout(new BoxLayout(panel_theatre_list, BoxLayout.Y_AXIS));
		panel_theatre_list.add(theatre);
		panel_theatre_list.add(theatre_choice);

		// movie
		JPanel panel_movie_list = new JPanel();
		panel_movie_list.setLayout(new BoxLayout(panel_movie_list, BoxLayout.Y_AXIS));
		panel_movie_list.add(movie);
		panel_movie_list.add(movie_choice);

		// show times
		JPanel panel_time_list = new JPanel();
		panel_time_list.setLayout(new BoxLayout(panel_time_list, BoxLayout.Y_AXIS));
		panel_time_list.add(times);
		panel_time_list.add(time_choice);

		// seats
		JPanel panel_seat_list = new JPanel();
		panel_seat_list.setLayout(new BoxLayout(panel_seat_list, BoxLayout.Y_AXIS));
		panel_seat_list.add(seat);
		panel_seat_list.add(seat_choice);

		// first panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(panel_theatre_list);
		panel1.add(confirm_theatre);

		// second panel
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(panel_movie_list);
		panel2.add(confirm_movie);

		// third panel
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(panel_time_list);
		panel3.add(confirm_showtime);

		// fourth panel
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		panel4.add(panel_seat_list);
		panel4.add(confirm_seat);

		// buttons
		JPanel panel_buttons = new JPanel();
		panel_buttons.setLayout(new FlowLayout());
		panel_buttons.add(back);
		panel_buttons.add(confirm);

		// adding above panels to a grid
		JPanel panel5 = new JPanel();
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
		panel_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel_title);
		panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel1);
		panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel2);
		panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel3);
		panel4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel4);
		cost.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(cost);
		panel_buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel5.add(panel_buttons);

		// add to frame
		add(panel5, BorderLayout.CENTER);
		pack();
	}

	/**
	 * add listener for the back button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addBackListener(ActionListener a) {
		this.back.addActionListener(a);
	}

	/**
	 * add listener for the confimr button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addConfirmListener(ActionListener a) {
		this.confirm.addActionListener(a);
	}

	/**
	 * add listener for the confirm theatre button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addConfirmTheatreListener(ActionListener a) {
		this.confirm_theatre.addActionListener(a);
	}

	/**
	 * add listener for the confirm movie button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addConfirmMovieListener(ActionListener a) {
		this.confirm_movie.addActionListener(a);
	}

	/**
	 * add listener for the confirm showtime button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addConfirmShowtimeListener(ActionListener a) {
		this.confirm_showtime.addActionListener(a);
	}

	/**
	 * add listener for the confirm seat button
	 * 
	 * @param a is the ActionListener object
	 */
	public void addConfirmSeatListener(ActionListener a) {
		this.confirm_seat.addActionListener(a);
	}

	/**
	 * add theatres to combo box using a helper function
	 * 
	 * @param theatres is a string array of theatre names
	 */

	public void addAllTheatre(String[] theatres) {
		for (String t : theatres) {
			addTheatre(t);
		}
	}

	/**
	 * add movies to combo box using a helper function
	 * 
	 * @param movies is a string array of movie names
	 */

	public void addAllMovie(String[] movies) {
		for (String m : movies) {
			addMovie(m);
		}
	}

	/**
	 * add showtimes to combo box using a helper function
	 * 
	 * @param showtimes is a string array of showtimes
	 */

	public void addAllShowtime(String[] showtimes) {
		for (String s : showtimes) {
			addTime(s);
		}
	}

	/**
	 * add seats to combo box using a helper function
	 * 
	 * @param seats is a string array of seats
	 */
	public void addAllSeat(String[] seats) {
		for (String s : seats) {
			addSeat(s);
		}
	}

	// helper function
	private void addTheatre(String theatre) {
		theatre_choice.addItem(theatre);
	}

	// helper function
	private void addMovie(String movie) {
		movie_choice.addItem(movie);
	}

	// helper function
	private void addTime(String showtime) {
		time_choice.addItem(showtime);
	}

	// helper function
	private void addSeat(String seat) {
		seat_choice.addItem(seat);
	}

	/**
	 * get ticket information from fields
	 * 
	 * @param
	 * @return str which is a string array containing the information
	 */
	public String[] getTicketInfo() {
		String[] str = new String[4];
		str[0] = getCurrentTheatre();
		str[1] = getCurrentMovie();
		str[2] = getCurrentShowtime();
		str[3] = getCurrentSeat();
		return str;
	}

	public void resetTheatreList() {
		theatre_choice.removeAllItems();
	}

	public void resetMovieList() {
		movie_choice.removeAllItems();
	}

	public void resetShowtimeList() {
		time_choice.removeAllItems();
	}

	public void resetSeatList() {
		seat_choice.removeAllItems();
	}

	public void resetAllList() {
		resetMovieList();
		resetShowtimeList();
		resetSeatList();
	}

	public void setCost(String cst) {
		cost.setText("Total cost: $" + cst);
	}

	public String getCurrentTheatre() {
		return (String) theatre_choice.getItemAt(theatre_choice.getSelectedIndex());
	}

	public String getCurrentMovie() {
		return (String) movie_choice.getItemAt(movie_choice.getSelectedIndex());
	}

	public String getCurrentSeat() {
		return (String) seat_choice.getItemAt(seat_choice.getSelectedIndex());
	}

	public String getCurrentShowtime() {
		return (String) time_choice.getItemAt(time_choice.getSelectedIndex());
	}

	public JButton getBack() {
		return this.back;
	}

	public JButton getConfirm() {
		return this.confirm;
	}

	public JButton getConfirmTheatre() {
		return this.confirm_theatre;
	}

	public JButton getConfirmMovie() {
		return this.confirm_movie;
	}

	public JButton getConfirmShowtime() {
		return this.confirm_showtime;
	}

	public JButton getConfirmSeat() {
		return this.confirm_seat;
	}

}