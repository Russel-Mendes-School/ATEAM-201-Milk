package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles reading data from files and also exporting to files. This
 * file has static methods that can be called by the main driver. It has no
 * other purpose other than a helper class
 * 
 * @author Roland Jiang
 * @author Russel
 */
public class FileManager {

  /**
   * read the data from a csv file and store it into the Main driver database of
   * farms
   * 
   * @param path - path to the file
   * @return message - possible string reporting anything abnormalities
   */
  public static String readFromFile(String path) {
    String[] dateSplit;
    String date = null;
    String message = null;

    int dataFormatErrors = 0;
    // int dataRead = 0;
    System.out.println("Errors detected in " + path + ":");
    try {
      File file = new File(path);
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);

      // for each row in the loaded csv, store the data into this array
      String[] tempArr;

      // Skip first line of each file ("date,farm_id,weight")
      String line = br.readLine();

      line = br.readLine();

      while (line != null) {
        // dataRead++;

        tempArr = line.split(",");

        // Only allow lines that have three distinct sections, else its an error
        if (tempArr.length == 3) {
          try {
            date = tempArr[0];
            String farmID = tempArr[1];
            int milkWeight = Integer.parseInt(tempArr[2]);

            // Check if farm exists, if not add it
            if (Main.farmMap.get(farmID) == null) {
              Main.farmMap.put(farmID, new Farm(farmID));
              Main.farmNames.add(farmID);
            }
            dateSplit = date.split("-");

            if (dateSplit.length == 3) {
              String year = dateSplit[0];
              String month = dateSplit[1];
              int day = Integer.parseInt(dateSplit[2]);
              Main.farmMap.get(farmID).updateMilkOnDate(milkWeight, month, year,
                  day);
            } else// Track Errors in date format
            {
              System.out.println(Arrays.toString(tempArr));
              dataFormatErrors++;
            }
          } catch (NumberFormatException e)// catch errors in integer formatting
                                           // (numerical date/milk weight)
          {
            System.out.println(Arrays.toString(tempArr));
            dataFormatErrors++;
          }
        } else {
          dataFormatErrors++;
        }

        line = br.readLine();
      }
      br.close();
      br = null;
      fr = null;
      file = null;

    } catch (IOException e) {
      return ("File " + path + " Not Found.");
    } catch (Exception e) {
      e.printStackTrace();
      return ("Unknown Error Detected");
    }

    // Dynamic Responses - provide user with better understanding of load
    // results
    if (dataFormatErrors != 0)
      message = dataFormatErrors + " Format Errors detected.";
    return message;
  }

  /**
   * Parses a directory for files and reads the data from a csv file and store
   * it into the Main driver database of farms
   * 
   * @param path - path to directory
   * @return message - possible string message that reports abnormalities
   */
  public static String readFromDir(String path) {
    
    File[] files = new File(path).listFiles();
    String message = null;
    String response = "";
    
    for (File file : files) {
      response = FileManager.readFromFile(file.getAbsolutePath());
      if (response != null) {
        message += "Error Occured in File: " + file.getAbsolutePath() + " : "
            + response + "\n";
      }
    }
    System.out.println();
    return message;
  }

  /**
   * 
   * @param farmID
   */
  public static void write(String path, String message) {
//TODO
  }

  /**
   * 
   * @param path
   * @param farmID
   * @throws FileNotFoundException
   * @throws UnsupportedEncodingException
   */
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
