import java.util.*;
/**
* Class representing a showtime of a specific movie at a theatre. It contains variables
* listing the seats in that showtime, seats that are only available to be preordered, and
* information about when the showtime is.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class Showtime{
  private Date time;
  private ArrayList<Seat> seats;
  private String movieName;
  private ArrayList<Seat> preorderSeats;

  /**
  * Constructor that takes in the date of the showtime and the name of the corrosponding
  * movie.
  * @param month int holding the month of the showtime.
  * @param day int holding the day of the showtime.
  * @param hour int holding the hour of the showtime.
  * @param minute int holding the minute of the showtime.
  * @param String holding the name of the movie the showtime corrosponds to.
  */
  public Showtime(int month, int day, int hour, int minute, String movieName){
    setDate(new Date(month, day, hour, minute));
    setTheMovie(movieName);
    seats = new ArrayList<Seat>();
  } 
  
  /**
  * Constructor that takes in the date of the showtime.
  * @param month int holding the month of the showtime.
  * @param day int holding the day of the showtime.
  * @param hour int holding the hour of the showtime.
  * @param minute int holding the minute of the showtime.
  */
  public Showtime(int month, int day, int hour, int minute){
    setDate(new Date(month, day, hour, minute));
    seats = new ArrayList<Seat>();
  }

  public ArrayList<Seat> getSeats(){
	  return seats;
  }

  public void addSeat(Seat s){
    seats.add(s);
    return;
  }

  public Date getDate(){
    return time;
  }

  public void setDate(Date d){
    time = d;
  }

  public String getTheMovie(){
    return movieName;
  }

  public void setTheMovie(String m){
    movieName = m;
    return;
  }

  /**
  * Returns a list of all the seats still availible to be booked.
  * @return An Arraylist of availible seats.
  */
  public ArrayList<Seat> showAvailable(){
    ArrayList<Seat> temp = new ArrayList<Seat>();

    for(int i = 0; i < seats.size() ; i++){
      if(seats.get(i).getAvailability())
        temp.add(seats.get(i));
    }
    return temp;
  } 

  /**
	 * converts the int that specifies Month into a string and returns it
	 * @return String holding the month of the showtime.
	 */
  public String convertMonth(){
    String month;
    if(time.getMonth() == 1){
      month = "January";
    }else if(time.getMonth() == 2){
      month = "February";
    }else if(time.getMonth() == 3){
      month = "March";
    }else if(time.getMonth() == 4){
      month = "April";
    }else if(time.getMonth() == 5){
      month = "May";
    }else if(time.getMonth() == 6){
      month = "June";
    }else if(time.getMonth() == 7){
      month = "July";
    }else if(time.getMonth() == 8){
      month = "August";
    }else if(time.getMonth() == 9){
      month = "September";
    }else if(time.getMonth() == 10){
      month = "October";
    }else if(time.getMonth() == 11){
      month = "November";
    }else {
      month = "December";
    }
    return month;
  }
  /**
	 * converts the showtime information into a string and returns it
	 * @return String holding the showtime information.
	 */
  public String toString(){
   String month = convertMonth();
   String message = "Month: "+ month +" Day: "+time.getDay()+" Hour: "+time.getHour()+" Minute: "+time.getMinute();
   return message;
 }
  /**
	 * converts the ArrayList of Seats into a String array and returns it
	 * @return String array holding the names of all available seats.
	 */
 public String[] getSeatArray(){
   ArrayList<Seat> avSeats = showAvailable();
   String [] seatList = new String[avSeats.size()];
   for(int i = 0; i < avSeats.size(); i++){
     seatList[i] = avSeats.get(i).toString();
   }
   return seatList;
 }
}