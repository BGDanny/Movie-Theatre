import java.util.ArrayList;
import java.sql.*;

/**
 * Controller class responsible for managing interactions between the system and
 * the database.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class DataBaseController implements DBCredentials {
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
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
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

	/**
	 * adds a ticket to the Ticket table in the database
	 * 
	 * @param theTicket is a Ticket object
	 */
	public void addTicket(Ticket theTicket) {
		String query = "insert into Ticket values(?,?,?,?,?,?,?,?,?,?,?)";
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
			pStat.setInt(11, theTicket.getTheSeat().getID());
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

	/**
	 * finds a ticket in the database using the ticketID and returns the ticket
	 * object
	 * 
	 * @param ticketID is a ticket id
	 * @return
	 */
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
		int ID = 0;
		String query = "select * from ticket";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
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
					ID = rs.getInt(11);
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
		Ticket theTicket = new Ticket(ticketNum, movieName, month, day, hour, minute, row, column, type, cost, ID);
		return theTicket;
	}

	/**
	 * removes a ticket with the given ticket id from the Ticket table * in the
	 * database
	 * 
	 * @param ticketID is a ticket id
	 */
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

	/**
	 * verifies the login information entered by the user and returns a
	 * registeredUser object
	 * 
	 * @param username is the users username
	 * @param pasword  is the users password
	 * @return
	 */
	public RegisteredUser verifyUser(String username, String password) {
		RegisteredUser ru = null;
		String query = "select * from users";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
			while (rs.next()) {

				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					ru = new RegisteredUser(null, null, null, null, rs.getString(3), rs.getString(5), rs.getString(6),
							rs.getFloat(4), rs.getString(7), rs.getString(8), rs.getString(10));
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
		return ru;
	}

	/**
	 * adds a new user to the User table in the database
	 * 
	 * @param username is the users username
	 * @param pasword  is the users password
	 * @param theUser  is the User object
	 */
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
			pStat.setString(7, theUser.getType());
			pStat.setString(8, theUser.getCardNumber());
			pStat.setString(9, theUser.getName());
			pStat.setString(10, theUser.getExpiryDate());
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

	/**
	 * updates the availability of the given seat in the Seat table in the database
	 * 
	 * @param theSeat is the Seat object
	 * @param a       specifies whether the seat is booked or not
	 */
	public void updateSeat(Seat theSeat, int a) {
		String query = "UPDATE seat SET Availability=? WHERE ID=?";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setInt(1, a);
			pStat.setInt(2, theSeat.getID());
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

	/**
	 * Reads in all the Seats from the Seat table in the database and returns them
	 * in an ArrayList
	 * 
	 * @return
	 */
	public ArrayList<Seat> readSeats() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		String query = "SELECT * FROM SEAT";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("ID");
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
				seats.add(new Seat(id, row, column, type, cost, month, day, hour, minute, movieName, av));
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

	/**
	 * Reads in all the Showtimes from the Showtime table in the database and
	 * returns them in an ArrayList
	 * 
	 * @return
	 */
	public ArrayList<Showtime> readShowTimes() {
		ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
		String query = "SELECT * FROM SHOWTIME";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
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

	/**
	 * Reads in all the Movies from the Movie table in the database and returns them
	 * in an ArrayList
	 * 
	 * @return
	 */
	public ArrayList<Movie> readMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		String query = "SELECT * FROM MOVIE";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
			while (rs.next()) {
				String movieName = rs.getString("Name");
				int isPublic = rs.getInt("Public");
				movies.add(new Movie(movieName, isPublic));
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

	/**
	 * adds a credit to the Credit table in the database
	 * 
	 * @param theCredit is a Credit object
	 */
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

	/**
	 * finds a credit in the database using the id and returns the value of the
	 * credit
	 * 
	 * @param id is a creditid
	 * @return
	 */
	public double getCreditValue(String id) {
		double value = 0;
		String query = "select * from credit";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery(query);
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

	/**
	 * removes a credit with the given credit id from the Credit table * in the
	 * database
	 * 
	 * @param id is a credit id
	 */
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

	/**
	 * updates the value of the credit with the given id in the Credit table in the
	 * database
	 * 
	 * @param id       is the credit id
	 * @param newValue is the new value of the credit
	 */
	public void updateCredit(int id, double newValue) {
		String query = "UPDATE credit SET Value=? WHERE ID=?";
		PreparedStatement pStat = null;
		try {
			pStat = conn.prepareStatement(query);
			pStat.setDouble(1, newValue);
			pStat.setInt(2, id);
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