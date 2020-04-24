package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * This class handles reading data from files and also exporting to files
 * 
 * @author Roland Jiang
 * @author Russel
 *
 */
public class FileManager {

  public static void main(String[] args) {

    FileManager.readFromFile("2019-1.csv");
    System.out.println(Main.farmMap.size());
  }

  public static void readFromFile(String path) {
    String[] dateSplit;
    String date = null;
    try {
      File file = new File(path);
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String[] tempArr;
      // First line of each file is "date,farm_id,weight"
      String line = br.readLine();
      line = br.readLine();
      while (line != null) {

        tempArr = line.split(",");

        date = tempArr[0];
        String farmID = tempArr[1];
        int milkWeight = Integer.parseInt(tempArr[2]);

        if (Main.farmMap.get(farmID) == null) {
          Main.farmMap.put(farmID, new Farm(farmID));
          Main.farmNames.add(farmID);
        }
        dateSplit = date.split("/");
        String year = dateSplit[2];
        String month = dateSplit[0];
        int day = Integer.parseInt(dateSplit[1]);

        Main.farmMap.get(farmID).updateMilkOnDate(milkWeight, month, year, day);
        line = br.readLine();
      }
      br.close();
      br = null;
      fr = null;
      file = null;

    } catch (IOException e) {
      System.out.println("File not found");
    } catch (Exception e) {
      System.out.println(date);
    }
  }

  public static void readFromDir(String path) {
    File[] files = new File(path).listFiles();
    for (File file : files) {
      FileManager.readFromFile(file.getAbsolutePath());
    }

  }


  public static void write(String farmID) {

  }

  public static void exportFarmToFile(String path, String farmID)
      throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter(path, "UTF-8");
    writer.println("Date,FarmID,Weight");
    Farm targetFarm = Main.farmMap.get(farmID);

    ArrayList<Month> allMonths = targetFarm.getAllMonths();
    String dataString = "";

    for (Month m : allMonths) {
      String year = Integer.toString(m.year);
      String month = Integer.toString(m.month);
      int[] days = m.getDays();
      for (int i = 0; i < days.length; i++) {
        dataString += year;
        dataString += "-";
        dataString += month;
        dataString += "-";
        dataString += Integer.toString(i + 1);
        dataString += ",";
        dataString += farmID;
        dataString += ",";
        dataString += Integer.toString(days[i]);
        writer.println(dataString);
        dataString = "";
      }
    }
    writer.close();
  }

}
