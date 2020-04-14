import java.time.Month;
import java.util.HashMap;

public class Farm
{
  private String ID;
  private list<month> months;

  public Farm(File input, String ID)// Farm Constructor
  {
    this.ID = ID;

    // TODO Parse input from file making us of FileManager
    // list<month> = FileManager.read();
  }
  public String getID()
  {
    return ID;
  }
  public month getMonth(String month, int year)//return the specified month in this farms history
  {
    for(month m : months)
    {
      if(m.month.equals(month) && m.getYear() == year))
      {
        return m;
      }
    }
    return null;
  }

  public int getTotalMilk()
  {
    int totalMilk = 0;
    for (month m : months)
    {
      //sum days in the month
    }
  }

  public String toString()
  {
    return ID + "has produced " + getTotalMilk() + "Gallons of Milk in total.";
  }
}
