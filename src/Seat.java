/**
 * Class representing a Seat in a specific showtime for a movie. It has
 * variables indicating the location of the seat, the type of the seat, the
 * seats cost if the seat is available to be booked, and information about the
 * showtime.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class Seat {
  private int row;
  private int column;
  private String type;
  private double cost;
  private boolean isAvailable;
  private Date showtimeDate;
  private String movieName;

  /**
  * Constructor used when accessing database for the list of Seats.
  * @param row int representing the the seat is in.
  * @param column int representing the cloumn the seat is in.
  * @param type String representing the type of seat.
  * @param cost double representing the cost of the seat.
  * @param month int represnting the month of the showtime this seat is in.
  * @param day int represnting the day of the showtime this seat is in.
  * @param hour int represnting the hour of the showtime this seat is in.
  * @param minute int represnting the minute of the showtime this seat is in.
  * @param movieName String holding the name of the movie being shown.
  * @param av boolean indicating if the seat has been booked or not.
  */
  public Seat(int row, int column, String type, double cost, int month, int day, int hour, int minute, String movieName, boolean av) {
    setRow(row);
    setColumn(column);
    setType(type);
    setCost(cost);
    setDate(new Date(month, day, hour, minute));
    setMovieName(movieName);
    isAvailable = av;
  }

/**
  * Constructor used when accessing database for the list of Seats.
  * @param row int representing the the seat is in.
  * @param column int representing the cloumn the seat is in.
  * @param type String representing the type of seat.
  * @param cost double representing the cost of the seat.
  */
  public Seat(int row, int column, String type, double cost) {
    setRow(row);
    setColumn(column);
    setType(type);
    setCost(cost);
  }

  public int getRow() {
    return row;
  }

  public void setRow(int r) {
    row = r;
    return;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int c) {
    column = c;
    return;
  }

  public String getType() {
    return type;
  }

  public void setType(String t) {
    type = t;
    return;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double c) {
    cost = c;
    return;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String m) {
    movieName = m;
    return;
  }

  public Date getDate() {
    return showtimeDate;
  }

  public void setDate(Date d) {
    showtimeDate = d;
    return;
  }

  public boolean getAvailability() {
    return isAvailable;
  }

  /**
  * Swaps variable isAvailable from true to false or from false to true.
  */
  public void swapAvailability() {
    if (isAvailable)
      isAvailable = false;
    else
      isAvailable = true;
    return;
  }

  public String convertType(){
    String result;
    if(type.equals("E"))
      result = "Exclusive";
    else
      result = "Normal";
    return result;
  }
  public String convertAvailability(){
    String result;
    if(isAvailable)
      result = "open";
    else
      result = "booked";
    return result;
  }

  public String toString() {
    String av = convertAvailability();
    String theType = convertType();
    return "row: " + row + " column: " + column + " Type: " + theType +  " cost: " + cost + " Availability: " + av;
  }
}