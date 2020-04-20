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

        System.out.println(line);
        tempArr = line.split(",");

        date = tempArr[0];
        String farmID = tempArr[1];
        int milkWeight = Integer.parseInt(tempArr[2]);

        if (Main.farmMap.get(farmID) == null) {
          Main.farmMap.put(farmID, new Farm(farmID));
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

}
