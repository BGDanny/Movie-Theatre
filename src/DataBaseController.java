import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller class responsible for managing interactions between the system and
 * the database.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class DataBaseController {
	/*
	 * the connection to the database
	 */
	private Connection conn;
	/*
	 * the output of the database
	 */
	private ResultSet rs;

	/**
	 * constructs an instance of DataBaseController and initiates the * connection
	 * to the database
	 */
	public DataBaseController() {
		initializeConnection();
	}

	/**
	 * this method initiates the connection with the database
	 */
	public void initializeConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:theatre.db");
		} catch (SQLException e) {
			System.out.println("Problem, not connected");
			e.printStackTrace();
		}
	}

	/**
	 * this method closes all connections with the database and closes the objects
	 * used for those connections
	 */
	public void close() {
		try {
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// add a ticket to the database
	public void addTicket(Ticket theTicket) {
		String query = "insert into Ticket values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setInt(1, theTicket.getTicketNumber());
			pStat.setString(2, theTicket.getTheMovie());
			pStat.setInt(3, theTicket.getTheShowtime().getDate().getMonth());
			pStat.setInt(4, theTicket.getTheShowtime().getDate().getDay());
			pStat.setInt(5, theTicket.getTheShowtime().getDate().getHour());
			pStat.setInt(6, theTicket.getTheShowtime().getDate().getMinute());
			pStat.setInt(7, theTicket.getTheSeat().getRow());
			pStat.setInt(8, theTicket.getTheSeat().getColumn());
			pStat.setString(9, theTicket.getTheSeat().getType());
			pStat.setDouble(10, theTicket.getTheSeat().getCost());
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	// get a ticket from the database
	public Ticket getTicket(int ticketID) {
		int ticketNum = 0;
		String movieName = "";
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int row = 0;
		int column = 0;
		String type = "";
		double cost = 0;
		String query = "select * from Ticket";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {
				if (ticketID == rs.getInt(1)) {
					ticketNum = rs.getInt(1);
					movieName = rs.getString(2);
					month = rs.getInt(3);
					day = rs.getInt(4);
					hour = rs.getInt(5);
					minute = rs.getInt(6);
					row = rs.getInt(7);
					column = rs.getInt(8);
					type = rs.getString(9);
					cost = rs.getDouble(10);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		Ticket theTicket = new Ticket(ticketNum, movieName, month, day, hour, minute, row, column, type, cost);
		return theTicket;
	}

	// remove a ticket from the database
	public void removeTicket(int ticketID) {
		String query = "delete from Ticket where TicketNumber=?";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setInt(1, ticketID);
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	// verify the user information while login
	public boolean verifyUser(String username, String password) {
		boolean confirm = false;
		String query = "select * from Users";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {

				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					confirm = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return confirm;
	}

	// register and add information of the user to the database
	public void registerUser(String username, String password, RegisteredUser theUser) {
		String query = "insert into Users values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.setString(3, theUser.getName());
			pStat.setFloat(4, theUser.getPhoneNumber());
			pStat.setString(5, theUser.getAddress());
			pStat.setString(6, theUser.getEmailAddress());
			pStat.setString(7, theUser.getPaymentController().getType());
			pStat.setString(8, theUser.getPaymentController().getCardNumber());
			pStat.setString(9, theUser.getPaymentController().getNameOnCard());
			pStat.setString(10, theUser.getPaymentController().getExpiryDate());
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void updateSeat(Seat theSeat) {
		String query = "update Seat set Availability=? where Row=? and Column=? and Month=? and Day=? and Hour=? and Minute=? and MovieName=?";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setInt(1, 1);
			pStat.setInt(2, theSeat.getRow());
			pStat.setInt(3, theSeat.getColumn());
			pStat.setInt(4, theSeat.getDate().getMonth());
			pStat.setInt(5, theSeat.getDate().getDay());
			pStat.setInt(6, theSeat.getDate().getHour());
			pStat.setInt(7, theSeat.getDate().getMinute());
			pStat.setString(8, theSeat.getMovieName());
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	// read all seats from the database
	public ArrayList<Seat> readSeats() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		String query = "SELECT * FROM Seat";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {
				int row = rs.getInt("Row");
				int column = rs.getInt("Column");
				String type = rs.getString("Type");
				double cost = rs.getDouble("Cost");
				int month = rs.getInt("Month");
				int day = rs.getInt("Day");
				int hour = rs.getInt("Hour");
				int minute = rs.getInt("Minute");
				String movieName = rs.getString("MovieName");
				int avail = rs.getInt("Availability");
				boolean av = true;
				if (avail == 1)
					av = false;
				seats.add(new Seat(row, column, type, cost, month, day, hour, minute, movieName, av));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return seats;
	}

	// read the showtimes from the database
	public ArrayList<Showtime> readShowTimes() {
		ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
		String query = "SELECT * FROM Showtime";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {
				int month = rs.getInt("Month");
				int day = rs.getInt("Day");
				int hour = rs.getInt("Hour");
				int minute = rs.getInt("Minute");
				String movieName = rs.getString("MovieName");
				showtimes.add(new Showtime(month, day, hour, minute, movieName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return showtimes;
	}

	// read all movies from the database
	public ArrayList<Movie> readMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		String query = "SELECT * FROM Movie";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {
				String movieName = rs.getString("Name");
				movies.add(new Movie(movieName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return movies;
	}

	// add the record of credit to the database
	public void addCredit(Credit theCredit) {
		String query = "insert into Credit values(?,?)";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setInt(1, theCredit.getID());
			pStat.setDouble(2, theCredit.getValue());
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	// get the value of a typical credit coupon
	public double getCreditValue(String id) {
		double value = 0;
		String query = "select * from credit";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) {
				if (id.equals(rs.getString(1))) {
					value = rs.getDouble(2);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return value;
	}

	// remove credit from the database
	public void removeCredit(int id) {
		String query = "delete from Credit where ID=?";
		PreparedStatement pStat = null;
		try {

			pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			int i = pStat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStat != null)
				try {
					pStat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}