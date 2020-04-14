public class Month {

  String month, year;
  double[] days;

  public Month(String month, String year) {
    this.month = month;
    this.year  = year;

    switch (month) {
            case 1:  monthString = "January";
                     days = new int[31];
                     break;
            case 2:  monthString = "February";
                     days = new int[28];
                     break;
            case 3:  monthString = "March";
                     days = new int[31];
                     break;
            case 4:  monthString = "April";
                     days = new int[30];
                     break;
            case 5:  monthString = "May";
                     days = new int[31];
                     break;
            case 6:  monthString = "June";
                     days = new int[30];
                     break;
            case 7:  monthString = "July";
                     days = new int[31];
                     break;
            case 8:  monthString = "August";
                     days = new int[31]
                     break;
            case 9:  monthString = "September";
                     days = new int[30];
                     break;
            case 10: monthString = "October";
                     days = new int[31];
                     break;
            case 11: monthString = "November";
                     days = new int[30];
                     break;
            case 12: monthString = "December";
                     days = new int[31];
                     break;
            default: monthString = "Invalid month";
                     throw new IllegalArgumentException("Invalid month");
                     break;
        }
  }

  public void setDayMilk(double milkTot, int day) {
    days[day] = milkTot;
  }

  public double getDayMils(int day) {
    return days[day];
  }

  public String getYear() {
    return year;
  }

  public static void main(String[] args) {

  }

}
