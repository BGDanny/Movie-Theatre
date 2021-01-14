import java.util.*;

/**
 * Class represnting a specific point in time, down to the minute. It is used
 * for storing information about showtimes.
 * 
 * @author Michael Vassilev, Minh Vo, Matthew Wells, Junhao Xue
 */
public class Date {
  private int month;
  private int day;
  private int hour;
  private int minute;

  /**
   * Constructor that takes four integers representing a month, day, hour, and
   * minute, and sets them to the relevant variables.
   * 
   * @param month  Integer holding the month of the new Date object.
   * @param day    Integer holding the day of the new Date object.
   * @param hour   Integer holding the hour of the new Date object.
   * @param minute Integer holding the minute of the new Date object.
   */
  public Date(int month, int day, int hour, int minute) {
    setMonth(month);
    setDay(day);
    setHour(hour);
    setMinute(minute);
  }

  /**
   * returns true if the two Dates compared are the same, false otherwise
   * 
   * @param theDate is a Date object
   * @return boolean representing if the incoming date object has the same values
   *         as this one
   */
  public boolean comp(Date theDate) {
    boolean result = false;
    if (theDate.getMonth() == getMonth() && theDate.getDay() == getDay() && theDate.getHour() == getHour()
        && theDate.getMinute() == getMinute())
      result = true;

    return result;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int m) {
    month = m;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int d) {
    day = d;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int h) {
    hour = h;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int m) {
    minute = m;
  }
}