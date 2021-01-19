import java.util.*;
  /**
  * Class representing a Theatre. It has variables storing all of the movies that are shown
  * at the theatre, and the name of the theatre. 
  * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
  */
public class Theatre{ 
 private String name;
 private ArrayList<Movie> movies;

 /**
 * Constructor that takes a string holding the name of the theatre, and sets 
 * member variable name to it.
 * @param n String holding the name of the theatre.
 */
 public Theatre(String n){
   name = n;
   movies = new ArrayList<Movie>();
 }

 public void setMovie(ArrayList<Movie> movie){
   movies=movie;
 }

 public int size(){
   return movies.size();
 }

 public void setName(String n){
   name = n;
 }
 
 public String getName(){
    return name;
 }

 public ArrayList<Movie> getMovies(){
   return movies;
 }

 public void setMovies(ArrayList<Movie> m){
   movies = m;
 }

 public String[] getMoviesArray(){
   String [] movieList = new String[movies.size()];
   for(int i = 0; i < movies.size(); i++){
     movieList[i] = movies.get(i).getName();
   }
   return movieList;
 }

}