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
import java.util.HashMap;
import java.util.List;

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
  public static String readFromFile(String path, HashMap<String, Farm> farmMap,
      ArrayList<String> farmNames) {
    String[] dateSplit;
    String date = null;
    String message = null;

    int dataFormatErrors = 0;
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
            if (farmMap.get(farmID) == null) {
              farmMap.put(farmID, new Farm(farmID));
              farmNames.add(farmID);
            }
            dateSplit = date.split("-");

            if (dateSplit.length == 3) {
              String year = dateSplit[0];
              String month = dateSplit[1];
              int day = Integer.parseInt(dateSplit[2]);
              farmMap.get(farmID).updateMilkOnDate(milkWeight, month, year,
                  day);
            } else// Track Errors in date format
            {
              dataFormatErrors++;
            }
          } catch (NumberFormatException e)// catch errors in integer formatting
                                           // (numerical date/milk weight)
          {
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
  public static String readFromDir(String path, HashMap<String, Farm> farmMap,
      ArrayList<String> farmNames) {

    File[] files = new File(path).listFiles();
    String message = null;
    String response = "";
    // parse directory for all files. Even subdirectories for files
    // Try to add the file as a farm. The user has to be sure that the files are
    // data files
    for (File file : files) {
      response =
          FileManager.readFromFile(file.getAbsolutePath(), farmMap, farmNames);
      if (response != null) {
        message += "Error Occured in File: " + file.getAbsolutePath() + " : "
            + response + "\n";
      }
    }
    return message;
  }

  /**
   * The following information could be sent to a file
   *
   * @param farmID - id of target farm
   * @throws UnsupportedEncodingException
   * @throws FileNotFoundException
   */
  public static void writeStatsToFile(List<String> months, List<Integer> max,
      List<Integer> min, List<Float> avg, List<Float> dev, float monthlyDev,
      String path, String farmID, int year)
      throws FileNotFoundException, UnsupportedEncodingException {


    // Create a header
    PrintWriter writer = new PrintWriter(path, "UTF-8");
    writer.println("FarmID: " + farmID + " Year " + year + " Statistics");
    writer.println("-------------------");


    // Export Stats for the farm
    for (int i = 0; i < months.size(); i++) {
      writer.println(months.get(i) + ":");
      writer.println("------------------");
      writer.println("Minimum Sale: " + min.get(i));
      writer.println("Maximum Sale: " + max.get(i));
      writer.println("Average Sales: " + avg.get(i));
      writer.println("Deviation in Sales: " + dev.get(i));
    }
    writer.println("Deviation for All Months: " + monthlyDev);
    writer.close();



  }

  /**
   *
   * @param path
   * @param farmID
   * @throws FileNotFoundException
   * @throws UnsupportedEncodingException
   */
  public static void exportFarmToFile(String path, String farmID,
      HashMap<String, Farm> farmMap)
      throws FileNotFoundException, UnsupportedEncodingException {

    // Create Header
    PrintWriter writer = new PrintWriter(path, "UTF-8");
    writer.println("Date,FarmID,Weight");
    Farm targetFarm = farmMap.get(farmID);



    // For all data points in the target farm. Create new line with the data
    // Formate:Date,ID,Weight
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

  /**
   *
   * @param path
   * @param farmID
   * @throws FileNotFoundException
   * @throws UnsupportedEncodingException
   */
  public static void exportLogsToFile(String path, ArrayList<String> logs)
      throws FileNotFoundException, UnsupportedEncodingException {

    PrintWriter writer = new PrintWriter(path, "UTF-8");
    // For every line in logs, write a new line
    for (String line : logs) {
      writer.println(line);
    }

    writer.close();
  }

}
