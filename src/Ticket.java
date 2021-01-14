/**
* Class representing a ticket for a specific seat, showtime and movie at a theatre.
* It has variables to hold this information, and a unique ticket id number.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class Ticket{
  private Theatre theTheatre;
  private String theMovie;
  private Showtime theShowtime;
  private Seat theSeat;
  private int ticketNumber;

  /**
  * This constructor is used by the database when working with Ticket Objects.
  * @param id int holding the ID number of the ticket to be assigned to ticketNumber.
  * @param movieName String holding the name of the movie to be assigned to theMovie.
  * @param month int holding the month the specific showtime takes place.
  * @param day int holding the day the specific showtime takes place.
  * @param hour int holding the hour the specific showtime takes place.
  * @param minute int holding the minute the specific showtime takes place.
  * @param row int holding the row number of the seat the ticket it for.
  * @param column int holding the column number of the seat the ticket is for.
  * @param seatType String holding the type of seat.
  * @param cost double indicating the cost of the ticket.
  */
  public Ticket(int id, String movieName, int month, int day, int hour, int minute, int row, int column, String seatType, double cost){
    setTicketNumber(id);
    setTheMovie(movieName);
    setTheShowtime(new Showtime(month, day, hour, minute));
    setTheSeat(new Seat(row, column, seatType, cost));
  }
  /**
  * This constructor is used by the database when working with Ticket Objects.
  * @param id int holding the ID number of the ticket to be assigned to ticketNumber.
  * @param movieName String holding the name of the movie to be assigned to theMovie.
  * @param month int holding the month the specific showtime takes place.
  * @param day int holding the day the specific showtime takes place.
  * @param hour int holding the hour the specific showtime takes place.
  * @param minute int holding the minute the specific showtime takes place.
  * @param row int holding the row number of the seat the ticket is for.
  * @param column int holding the column number of the seat the ticket is for.
  * @param seatType String holding the type of seat.
  * @param cost double indicating the cost of the ticket.
  * @param ID int holding the ID of the seat the ticket is for
  */
  public Ticket(int id, String movieName, int month, int day, int hour, int minute, int row, int column, String seatType, double cost, int ID){
    setTicketNumber(id);
    setTheMovie(movieName);
    setTheShowtime(new Showtime(month, day, hour, minute));
    setTheSeat(new Seat(row, column, seatType, cost, ID));
  }

  /**
  * Contructor that takes no input and sets all member variables to null or 0.
  */
  public Ticket(){
    theTheatre = null;
    theMovie = null;
    theShowtime = null;
    theSeat = null;
    ticketNumber = 0;
  }
  
  /**
  * Constructor that takes in four objects. Used when a ticket is being booked.
  * @param t Theatre object representing the theatre of the showtime the ticket is for.
  * @param m Movie object represnting the movie the ticket is for.
  * @param st Showtime object represnting the showtimg the ticket is for.
  * @param s Seat object representing the specific seat the ticket entitiles the user to.
  */
  public Ticket(Theatre t, Movie m, Showtime st, Seat s){
    theTheatre = t;
    theMovie = m.getName();
    theShowtime = st;
    theSeat = s;
    ticketNumber = (int)System.currentTimeMillis();
  }

  public Theatre getTheTheatre(){
    return theTheatre;
  }

  public void setTheatre(Theatre t){
    theTheatre = t;
    return;
  }

  public String getTheMovie(){
    return theMovie;
  }

  public void setTheMovie(String m){
    theMovie = m;
    return;
  }

  public Showtime getTheShowtime(){
    return theShowtime;
  }

  public void setTheShowtime(Showtime s){
    theShowtime = s;
    return;
  }

  public Seat getTheSeat(){
    return theSeat;
  }

  public void setTheSeat(Seat s){
    theSeat = s;
    return;
  }

  public int getTicketNumber(){
    return ticketNumber;
  }

  public void setTicketNumber(int t){
    ticketNumber = t;
    return;
  }
}