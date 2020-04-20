package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Farm - Stores and manages the milk produced by a dairy farm
 *
 * @author Ben Rinvelt
 * @author Russel Mendes
 */
public class Farm
{
  private String ID;

  protected HashMap<String, Month> farmData;

  /** Creates a new Farm */
  public Farm(String ID)
  {
    this.ID = ID;
    farmData = new HashMap<String, Month>();
  }

  /**
   * Return the farms ID
   * 
   * @return String ID
   */
  public String getID()
  {
    return ID;
  }

  /**
   * update the milk weights on a given date
   * 
   * @param milkWeight   - integer value of milk weights
   * @param yearMonthKey - year+month string value key that access the month
   * @param day          - integer value of day
   */
  protected void updateMilkOnDate(int milkWeight, String month, String year, int day)
  {
    String yearMonthKey = year + month;

    // Make sure that a month exists, otherwise add it
    if (this.farmData.get(yearMonthKey) == null)
    {
      this.farmData.put(yearMonthKey, new Month(month, year));
    }
    this.farmData.get(yearMonthKey).setDayMilk(milkWeight, day - 1);
  }

  /**
   * add a new month to the farmData
   * 
   * @param month - integer
   * @param year  - integer
   */
  private void addNewMonth(String month, String year)
  {
    Month newMonth = new Month(month, year);
    String yearMonthKey = year + month;

    this.farmData.put(yearMonthKey, newMonth);
  }

  /**
   * Get the total milk contributed by a farm
   * 
   * @return Total Milk integer
   */
  public int getTotalMilk()
  {
    int totalMilk = 0;
    Iterator<Entry<String, Month>> farmDataIterator = farmData.entrySet().iterator();// iterator for Months HashMap

    while (farmDataIterator.hasNext())
    {
      Entry<String, Month> mapElement = farmDataIterator.next();
      totalMilk += mapElement.getValue().totalMilk();
    }
    return totalMilk;
  }

  public ArrayList<Month> getMonthsForYear(int year) {
	  ArrayList<Month> months = new ArrayList<Month>();
	  for (Month m : farmData.values()) {
		  if (m.getYear() == year) months.add(m);
	  }
	  return months;
  }

  public String toString()
  {
    return ID + " has produced " + getTotalMilk() + " Gallons of Milk in total.";
  }
}