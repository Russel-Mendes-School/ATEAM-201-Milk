package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
    try {
      File file = new File(path);
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String[] tempArr;
      // First line of each file is "date,farm_id,weight"
      while (br.readLine() != null) {
        tempArr = br.readLine().split(",");

        String date = tempArr[0];
        String farmID = tempArr[1];
        int milkWeight = Integer.parseInt(tempArr[2]);

        if (Main.farmMap.get(farmID) == null) {
          Main.farmMap.put(farmID, new Farm(farmID));
        }

        String[] dateSplit = date.split("-");
        String year = dateSplit[0];
        String month = dateSplit[1];
        int day = Integer.parseInt(dateSplit[2]);

        Main.farmMap.get(farmID).updateMilkOnDate(milkWeight, month, year, day);

      }
      br.close();

    } catch (IOException e) {
      System.out.println("File not found");
    }
  }


  public static void write(String farmID) {

  }

}
