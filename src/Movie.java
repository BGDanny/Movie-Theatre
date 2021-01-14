import java.util.*;
/**
* Class represeting a movie that is being played at a theatre. It contains variables
* holding information about the movie's different showtimes, and if regular users of
* the system are able to book tickets for it yet. 
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class Movie{
  private String name;
  private ArrayList<Showtime> showtimes;
  private int isPublic;

  /**
  * Constructor that takes in the name of the movie and whether it has been released to
  * the public, assigns the relavent variables. Also sets an empty arraylist to showtimes.
  */ 
  public Movie(String movieName, int p){
    setName(movieName);
    setPublic(p);
    showtimes = new ArrayList<Showtime>();
  }

  /**
  * Checks that each showtime in variable showtimes for at least one available seat,
  * in which case it will add that showtime to an ArrayList of showtimes which is 
  * returned to the calling function.
  * @return An ArrayList listing all seats that are still available to be booked. 
  */
  public ArrayList<Showtime> showAvailableTimes(){
    ArrayList<Showtime> available = new ArrayList<Showtime>();

    for(int i = 0; i < showtimes.size(); i++){
      if(showtimes.get(i).showAvailable().size() > 0)
        available.add(showtimes.get(i));
    }

    return available;
  }

  public void addShowTime(Showtime st){
   showtimes.add(st);
  }

  public int getPublic(){
    return isPublic;
  }

  public void setPublic(int b){
    isPublic = b;
  }

  public ArrayList<Showtime> getShowTime(){
    return showtimes;
  }

  public void setShowTime(ArrayList<Showtime> s){
    showtimes = s;
  }

  public String getName(){
    return name;
 }

 public void setName(String n){
   name = n;
 }
  /**
	 * converts the showtime Arrayist into a String array and returns it
	 * @return A string array listing all available showtimes for this movie.
	 */
 public String[] getShowtimeArray(){
   String [] showtimeList = new String[showtimes.size()];
   for(int i = 0; i < showtimes.size(); i++){
     showtimeList[i] = showtimes.get(i).toString();
   }
   return showtimeList;
 }
}