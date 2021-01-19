import java.util.*;

/**
 * Class responsible for storing all of the theatres in the system. In this
 * implementation of the system it will only ever have one theatre object.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class TheatreController {
  private ArrayList<Theatre> theatres;
  private DataBaseController DBControl;
  private GUIController theGUI;

  /**
  * Constructor that takes a DataBaseController as an object and sets it to dbcon.
  * @param dbcon DataBaseController to set dbcon to.
  */
  public TheatreController(DataBaseController dbcon) {
    DBControl = dbcon;
    theatres = new ArrayList<Theatre>();
    populateApp();
  }

  public void populateApp() {
	    
	    Theatre theTheatre = new Theatre("CineFlex"); 
	    theatres.add(theTheatre);
	    theatres.get(0).setMovies(DBControl.readMovies());
	    ArrayList<Showtime> showtimes = DBControl.readShowTimes();
	    ArrayList<Seat> seats = DBControl.readSeats();
	    for (int i = 0; i < theatres.get(0).size(); i++) {
	      
	      for (int j = 0; j < showtimes.size(); j++) {
	        
	        for (int m = 0; m < seats.size(); m++) {
	          
	          if (showtimes.get(j).getDate().comp(seats.get(m).getDate())
	              && showtimes.get(j).getTheMovie().equals(seats.get(m).getMovieName()) && showtimes.get(j).getSeats().size() < 20){
	            showtimes.get(j).addSeat(seats.get(m));
	            
	              }
	        }
	        if (theatres.get(0).getMovies().get(i).getName().equals(showtimes.get(j).getTheMovie()))
	          theatres.get(0).getMovies().get(i).addShowTime(showtimes.get(j));
	      }
	    }

	  }


  public String[] getTheatres(){
    String [] theatrelist = new String[theatres.size()];
    for(int i = 0; i < theatres.size(); i++){
      theatrelist[i] = theatres.get(i).getName();
    }
    return theatrelist;
  }

  public ArrayList<Theatre> getTheatreAL(){
    return theatres;
  }
}
