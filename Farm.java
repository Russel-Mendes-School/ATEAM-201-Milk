package application;

import java.util.HashMap;

/**
 *
 * Farm - TODO Describe purpose of type
 *
 * @author
 * @author Russel Mendes
 *
 */
public class Farm {
  private String ID;

  protected HashMap<String, Month> farmData = new HashMap<String, Month>();

  public Farm(String ID) {
    this.ID = ID;
  }

  public String getID() {
    return ID;
  }

  /**
   * update the milk weights on a given date
   *
   * @param milkWeight   - integer value of milk weights
   * @param yearMonthKey - year+month string value key that access the month
   * @param day          - integer value of day
   */
  protected void updateMilkOnDate(int milkWeight, String month, String year,
      int day) {
    String yearMonthKey = year + month;

    // Make sure that a month exists, otherwise add it
    if (this.farmData.get(yearMonthKey) == null) {
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
  private void addNewMonth(String month, String year) {
    Month newMonth = new Month(month, year);
    String yearMonthKey = year + month;
    this.farmData.put(yearMonthKey, newMonth);
  }

  /**
  * Calculate the total milk stored in this farm
  * @return the total milk in this farm
  */
  public int getTotalMilk() {
    int totalMilk = 0;

    for(int i = 0; i < farmData.size(); i++){
      totalMilk += farmData.get(i).totalMilk();
    }

    return totalMilk;
  }

  public String toString() {
    return ID + "has produced " + getTotalMilk() + "Gallons of Milk in total.";
  }
}
