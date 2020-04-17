package application;

/**
 *
 * Month - This class holds a data node. This data node called month as the
 * following information: year, its month number, and the values in its days
 * array
 *
 * @author Akhsay Bodla 2020
 * @author Russel Mendes 2020
 *
 */
public class Month {

  private int month, year;
  private String monthString; //the month name represented by the month variable
  private int[] days;

  /**
   * Default Constructor
   *
   * @param month - string namesake of the node
   * @param year  - the year of which the nodes belongs to
   */
  public Month(String month, String year) {
    // Make sure that the constructor receives a valid month
    try {
      this.month = Integer.parseInt(month);
      if (this.month > 12 || this.month < 1) {
        throw new IllegalArgumentException("Invalid month");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid input");
    }

    this.year = Integer.parseInt(year);
    // Depending on the integer month, set the appropriate day array
    switch (this.month) {
      case 1:
        monthString = "January";
        days = new int[31];
        break;
      case 2:
        monthString = "February";
        days = new int[28];
        break;
      case 3:
        monthString = "March";
        days = new int[31];
        break;
      case 4:
        monthString = "April";
        days = new int[30];
        break;
      case 5:
        monthString = "May";
        days = new int[31];
        break;
      case 6:
        monthString = "June";
        days = new int[30];
        break;
      case 7:
        monthString = "July";
        days = new int[31];
        break;
      case 8:
        monthString = "August";
        days = new int[31];
        break;
      case 9:
        monthString = "September";
        days = new int[30];
        break;
      case 10:
        monthString = "October";
        days = new int[31];
        break;
      case 11:
        monthString = "November";
        days = new int[30];
        break;
      case 12:
        monthString = "December";
        days = new int[31];
        break;
      default:
        monthString = "Invalid month";
    }
  }

  /**
   * For a given day within the month, insert milkWeight
   *
   * @param milkTot - integer value of milk weight
   * @param day     - integer value of the day
   */
  public void setDayMilk(int milkTot, int day) {

    if (day < 0) {
      throw new IllegalArgumentException("Days cannot be negative");
    }
    try {
      days[day] = milkTot;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "That day does not exist in this month");
    }

  }

  /**
   * For a given day within the month, get the milkWeight
   *
   * @param day - integer value of day
   * @return
   */
  public double getDayMilks(int day) {
    if (day < 0) {
      throw new IllegalArgumentException("Days cannot be negative");
    }
    try {
      return days[day];
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "That day does not exist in this month");
    }
  }

  /**
  * @return int - year of this month
  */
  public int getYear() {
    return this.year;
  }

  /**
  * @return String - the month this object represents
  */
  public String getName() {
    return this.monthString;
  }

}
