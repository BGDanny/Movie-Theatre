import java.util.*;
/**
* Class represnting a specific point in time, down to the minute. It is used for
* storing information about showtimes.
* @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
*/
public class Date{
  private int month;
  private int day;
  private int hour;
  private int minute;

  public Date(int month, int day, int hour, int minute){
    setMonth(month);
    setDay(day);
    setHour(hour);
    setMinute(minute);
  }

  public boolean comp(Date theDate){
    boolean result = false;
    if(theDate.getMonth() == getMonth() && theDate.getDay() == getDay() && theDate.getHour() == getHour() && theDate.getMinute() == getMinute())
      result = true;

    return result;
  }

  public int getMonth(){
    return month;
  }

  public void setMonth(int m){
    month = m;
  }

  public int getDay(){
    return day;
  }

  public void setDay(int d){
    day = d;
  }

  public int getHour(){
    return hour;
  }

  public void setHour(int h){
    hour = h;
  }

  public int getMinute(){
    return minute;
  }

  public void setMinute(int m){
    minute = m;
  }
}