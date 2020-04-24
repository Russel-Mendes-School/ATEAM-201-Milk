package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * Main - The GUI of the program
 *
 * @author Russel Mendes 2020
 *
 */
public class Main extends Application {

  // Final Fields
  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 600;
  private static final String APP_TITLE = "ATEAM 201 Milk Weights GUI";

  // Data Field --> FarmID, Farm
  protected static HashMap<String, Farm> farmMap = new HashMap<String, Farm>();
  protected static ArrayList<String> farmNames = new ArrayList<String>();

  private String commandFlag;
  private int actionFlag;
  private String exportFarmID;


  // Init Fields
  // BORDER PANE
  BorderPane borderPaneRoot;
  // TOP PANEL
  Label title;
  Label spacerTop;
  ComboBox<String> loadBox;
  ComboBox<String> editBox;
  ComboBox<String> newBox;
  ComboBox<String> statsBox;
  ComboBox<String> asgBox;
  HBox topHBox;
  // LEFT PANEL
  Label msgBoxLabel;
  TextField msgTextField;
  Label spacerLeft;
  VBox leftVBox;
  // CENTER PANEL
  Label UTILabel;
  TextField UTITextField;
  Label spacerCenter;
  VBox centerVBox;
  // RIGHT PANEL
  Label executeLabel;
  Button execButton;
  Label purgeLabel;
  Button purgeButton;
  Label spacerRight;
  VBox rightVBox;

  /**
   * Initialization Function
   */
  @SuppressWarnings("unchecked")
  @Override
  public void start(Stage primaryStage) throws Exception {

    borderPaneRoot = new BorderPane();

    Scene mainScene = new Scene(borderPaneRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);


    // TOP PANEL
    // Create Title
    title = new Label("Milk Weights Dashboard");
    title.setStyle("-fx-font-weight: bold");
    title.setFont(new Font("Arial", 30));
    // Spacer
    spacerTop = new Label("        ");
    // Load Combo box
    String[] loadOptions = {"Load File", "Load Dir", ""};
    loadBox =
        new ComboBox<String>(FXCollections.observableArrayList(loadOptions));
    // loadBox.setOnAction(e -> System.out.println("Calleed"));
    loadBox.setOnAction(e -> handleLoadSelection());
    // Edit Combo box
    String[] editOptions = {"Edit File", "Edit Farm", "Delete Farm", ""};
    editBox =
        new ComboBox<String>(FXCollections.observableArrayList(editOptions));
    editBox.setOnAction(e -> handleEditSelection());
    // New Combo box
    String[] newOptions = {"Export Farm", "Export Stats", "New Farm", ""};
    newBox =
        new ComboBox<String>(FXCollections.observableArrayList(newOptions));
    newBox.setOnAction(e -> handleNewSelection());
    // Stats Combo box
    String[] statsOptions =
        {"Max Stats", "Min Sales", "Avg Sales", "Dev. Sales", ""};
    statsBox =
        new ComboBox<String>(FXCollections.observableArrayList(statsOptions));
    statsBox.setOnAction(e -> handleStatSelection());
    // Assignment Combo box
    String[] AssignmentOptions = {"Farm Report", "Annual Report",
        "Monthly Sales", "Data Range Report", ""};
    asgBox = new ComboBox<String>(
        FXCollections.observableArrayList(AssignmentOptions));
    asgBox.setOnAction(e -> handleAsgSelection());

    // Packing
    topHBox = new HBox();
    topHBox.getChildren().addAll(title, spacerTop, loadBox, editBox, newBox,
        statsBox, asgBox);
    borderPaneRoot.setTop(topHBox);

    // LEFT PANEL
    // Message Box Label
    msgBoxLabel = new Label("Message Output");
    msgBoxLabel.setStyle("-fx-font-weight: bold");
    msgBoxLabel.setFont(new Font("Arial", 18));
    // Text Field
    msgTextField = new TextField();
    msgTextField.setPrefWidth(400);
    msgTextField.setPrefHeight(100);

    msgTextField.setPrefHeight(150);
    msgTextField.setMaxWidth(400);
    // Spacer
    spacerLeft = new Label("        ");
    // Packing
    VBox leftVBox = new VBox();
    leftVBox.getChildren().addAll(spacerLeft, msgBoxLabel, msgTextField);

    borderPaneRoot.setLeft(leftVBox);

    // CENTER PANEL
    // User Text Interface Label
    UTILabel = new Label("User Text Interface");
    UTILabel.setStyle("-fx-font-weight: bold");
    UTILabel.setFont(new Font("Arial", 18));
    // Text Field
    UTITextField = new TextField();
    UTITextField.setPrefWidth(100);
    UTITextField.setPrefHeight(200);

    UTITextField.setPrefHeight(200);
    UTITextField.setMaxWidth(200);

    // Spacer
    spacerCenter = new Label("        ");
    // Packing
    centerVBox = new VBox();
    centerVBox.getChildren().addAll(spacerCenter, UTILabel, UTITextField);
    centerVBox.setAlignment(Pos.TOP_CENTER);
    borderPaneRoot.setCenter(centerVBox);

    // RIGHT PANEL
    // Execute Label
    executeLabel = new Label("EXECUTE");
    executeLabel.setStyle("-fx-font-weight: bold");
    executeLabel.setFont(new Font("Arial", 24));
    // Text Field
    execButton = new Button();
    // Format Works
    // Lambda Functions
    execButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
        e -> executeSelection());
    purgeLabel = new Label("PURGE COMMAND");
    purgeLabel.setStyle("-fx-font-weight: bold");
    purgeLabel.setFont(new Font("Arial", 24));
    // Text Field
    purgeButton = new Button();
    // Format Works
    // Lambda Functions
    purgeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      this.actionFlag = 0;
      this.commandFlag = null;
      this.msgTextField.clear();
      this.UTITextField.clear();
      this.borderPaneRoot.setBottom(null);
    });

    // Spacer
    spacerRight = new Label("        ");
    // Packing
    rightVBox = new VBox();
    rightVBox.getChildren().addAll(spacerRight, executeLabel, execButton,
        purgeLabel, purgeButton);
    rightVBox.setAlignment(Pos.TOP_CENTER);
    borderPaneRoot.setRight(rightVBox);

    // Bottom Panel
    // Going to be a VBOX of info. This will be populated by functions



    primaryStage.show();
  }

  // helper functions
  /**
   * add a new farm with a given ID
   * 
   * @param ID
   */
  protected void addFarm(String ID) {
    Farm newFarm = new Farm(ID);
    this.farmMap.put(ID, newFarm);
  }

  /**
   * update a farm with a given ID
   * 
   * @param ID
   */
  protected void updateFarm(String ID) {

  }

  /**
   * executing the commands selected by the user
   */
  private void executeSelection() {

    // If there is no actions taken, get the latest command by the user
    if (actionFlag == 0) {
      String command = this.msgTextField.getText();
      String[] commandSplit = command.split(": ");
      commandFlag = commandSplit[1];
    }

    if (commandFlag.equals("Load File")) {
      this.loadDataFromCSV();

    } else if (commandFlag.equals("Load Dir")) {
      this.loadDataFromDir();

    } else if (commandFlag.equals("Edit File")) {
      this.editFile();

    } else if (commandFlag.equals("Edit Farm")) {
      this.editFarm();

    } else if (commandFlag.equals("Delete Farm")) {
      this.deleteFarm();

    } else if (commandFlag.equals("Export Farm")) {
      this.newExportFarmToFile();

    } else if (commandFlag.equals("Export Stats")) {
      this.newExportStats();

    } else if (commandFlag.equals("New Farm")) {
      this.newCustomFarm();

    } else if (commandFlag.equals("Max Stats")) {
      this.showMaxSales();

    } else if (commandFlag.equals("Min Sales")) {
      this.showMinSales();

    } else if (commandFlag.equals("Avg Sales")) {
      this.showAvgSales();

    } else if (commandFlag.equals("Dev. Sales")) {
      this.showDevSales();

    } else if (commandFlag.equals("Farm Report")) {
      this.farmReport();

    } else if (commandFlag.equals("Annual Report")) {
      this.annualReport();

    } else if (commandFlag.equals("Monthly Sales")) {
      this.monthlyReport();

    } else if (commandFlag.equals("Data Range Report")) {
      this.dateRangeReport();
    } else {
      // Awaiting Further Command
      commandFlag = "";
    }

  }



  /**
   * Display the command on the screen via a text field
   * 
   * @param command
   */
  private void showLoadSelection(String command) {
    this.msgTextField.clear();
    String message = "Loaded Command: ";
    message += command;
    this.msgTextField.setText(message);

  }

  /**
   * event handler for the loadbox dropdown
   */
  private void handleLoadSelection() {
    // Get the command
    String command = this.loadBox.getSelectionModel().getSelectedItem();
    // Set all other boxes to empty
    this.editBox.getSelectionModel().clearSelection();
    this.newBox.getSelectionModel().clearSelection();
    this.statsBox.getSelectionModel().clearSelection();
    this.asgBox.getSelectionModel().clearSelection();

    // Send off the appropriate command
    showLoadSelection(command);
  }

  /**
   * event handler for the editbox dropdown
   */
  private void handleEditSelection() {
    // Get the command
    String command = this.editBox.getSelectionModel().getSelectedItem();
    // Set all other boxes to empty
    this.loadBox.getSelectionModel().clearSelection();
    this.newBox.getSelectionModel().clearSelection();
    this.statsBox.getSelectionModel().clearSelection();
    this.asgBox.getSelectionModel().clearSelection();

    // Send off the appropriate command
    showLoadSelection(command);
  }

  /**
   * event handler for the newbox dropdown
   */
  private void handleNewSelection() {
    // Get the command
    String command = this.newBox.getSelectionModel().getSelectedItem();
    // Set all other boxes to empty
    this.editBox.getSelectionModel().clearSelection();
    this.loadBox.getSelectionModel().clearSelection();
    this.statsBox.getSelectionModel().clearSelection();
    this.asgBox.getSelectionModel().clearSelection();

    // Send off the appropriate command
    showLoadSelection(command);
  }

  /**
   * event handler for the statsbox dropdown
   */
  private void handleStatSelection() {
    // Get the command
    String command = this.statsBox.getSelectionModel().getSelectedItem();
    // Set all other boxes to empty
    this.editBox.getSelectionModel().clearSelection();
    this.newBox.getSelectionModel().clearSelection();
    this.loadBox.getSelectionModel().clearSelection();
    this.asgBox.getSelectionModel().clearSelection();

    // Send off the appropriate command
    showLoadSelection(command);
  }

  /**
   * event handler for the asgbox dropdown
   */
  private void handleAsgSelection() {
    // Get the command
    String command = this.asgBox.getSelectionModel().getSelectedItem();
    // Set all other boxes to empty
    this.editBox.getSelectionModel().clearSelection();
    this.newBox.getSelectionModel().clearSelection();
    this.statsBox.getSelectionModel().clearSelection();
    this.loadBox.getSelectionModel().clearSelection();

    // Send off the appropriate command
    showLoadSelection(command);
  }


  // Commands demanded by Rubric

  @SuppressWarnings("rawtypes")
  /**
   * Prompt user for a farm id and year (or use all available data)
   * 
   * Then, display the total milk weight and percent of the total of all farm
   * for each month.
   * 
   * Sort, the list by month number 1-12, show total weight, then that farm's
   * percent of the total milk received for each month.
   */
  private void farmReport() {
    String targetFarm;
    int targetYear;

    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {

      String[] args = this.UTITextField.getText().split(",");
      targetFarm = args[0];
      targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        return;
      }

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();

      // HashMap<String, Month> farmData = farmMap.get(targetFarm).farmData;

      int totalMilkOfYear = 0;
      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);

      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });

      for (Month m : months) {
        totalMilkOfYear += m.totalMilk();
      }

      lines.add(new TextField("FarmID: " + targetFarm + " Year " + targetYear));
      for (Month m : months) {
        int milk = m.totalMilk();
        int percent = (milk * 100) / totalMilkOfYear;
        lines.add(
            new TextField(m.getName() + ": " + milk + " (" + percent + "%)"));
      }
      lines.add(new TextField("Total: " + totalMilkOfYear));

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }

  /**
   * Delete a farm from the data structure
   */
  private void deleteFarm() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Purge Farm ID: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {

      String deleteID = this.UTITextField.getText();
      farmMap.remove(deleteID);

      this.msgTextField.clear();
      this.msgTextField.setText("Task Completed Successfully");
      actionFlag = 0;
      return;
    }
  }

  /**
   * Ask for year.
   * 
   * Then display list of total weight and percent of total weight of all farms
   * by farm for the year.
   * 
   * Sort by Farm ID, or you can allow the user to select display ascending or
   * descending by weight.
   */
  private void annualReport() {
    int targetYear;

    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {

      String args = this.UTITextField.getText();
      targetYear = Integer.parseInt(args);
      this.msgTextField.clear();

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();
      lines.add(new TextField("Year: "));

      // Since we know all farms we can parse through farm names

      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      int totalMilkOfEveryFarm = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);
        int totalMilkOfFarmInYear = 0;
        for (Month m : months) {
          totalMilkOfFarmInYear += m.totalMilk();
        }
        totalMilkOfEveryFarm += totalMilkOfFarmInYear;
      }

      lines.add(new TextField("Total: " + totalMilkOfEveryFarm));

      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);
        int totalMilkOfFarmInYear = 0;
        for (Month m : months) {
          totalMilkOfFarmInYear += m.totalMilk();
        }
        float percent = totalMilkOfFarmInYear / totalMilkOfEveryFarm;
        lines.add(new TextField(
            targetFarm + ": " + totalMilkOfFarmInYear + " (" + percent + "%)"));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }


  /**
   * Ask for year and month.
   * 
   * Then, display a list of totals and percent of total by farm.
   * 
   * The list must be sorted by Farm ID, or you can prompt for ascending or
   * descending by weight.
   */
  private void monthlyReport() {
    int targetYear = 0;
    int targetMonth = 0;

    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Numerically: Year,Month: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {

      String[] args = this.UTITextField.getText().split(",");
      targetYear = Integer.parseInt(args[0]);
      targetMonth = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();
      lines
          .add(new TextField("Year: " + targetYear + " Month: " + targetMonth));

      // Since we know all farms we can parse through farm names
      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      int totalMilkOfEveryFarmMonthly = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        int totalMilkOfFarmInMonth = 0;
        for (Month m : months) {
          if (m.month == targetMonth) {
            totalMilkOfFarmInMonth = m.totalMilk();
          }
        }
        totalMilkOfEveryFarmMonthly += totalMilkOfFarmInMonth;
      }

      lines.add(new TextField("Total: " + totalMilkOfEveryFarmMonthly));

      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        int totalMilkOfFarmInMonth = 0;
        for (Month m : months) {
          if (m.month == targetMonth) {
            totalMilkOfFarmInMonth = m.totalMilk();
          }
        }
        float percent = totalMilkOfFarmInMonth / totalMilkOfEveryFarmMonthly;
        lines.add(new TextField(targetFarm + ": " + totalMilkOfFarmInMonth
            + " (" + percent + "%)"));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }

  /**
   * Prompt user for start date (year-month-day) and end month-day,
   * 
   * Then display the total milk weight per farm and the percentage of the total
   * for each farm over that date range.
   * 
   * The list must be sorted by Farm ID, or you can prompt for ascending or
   * descending order by weight or percentage
   */
  private void dateRangeReport() {
    int targetYear = 0;
    int startMonth = 0;
    int endMonth = 0;
    int startDay = 0;
    int endDay = 0;

    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField
          .setText("Numerically Start:End-> Year,Month,Day:Month,Day");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {

      String[] args = this.UTITextField.getText().split(":");
      targetYear = Integer.parseInt(args[0].split(",")[0]);
      startMonth = Integer.parseInt(args[0].split(",")[1]);
      startDay = Integer.parseInt(args[0].split(",")[2]);

      endMonth = Integer.parseInt(args[1].split(",")[0]);
      endDay = Integer.parseInt(args[1].split(",")[1]);
      this.msgTextField.clear();

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();
      lines.add(new TextField(
          "Year: " + targetYear + " Month: " + startMonth + " Day: " + startDay
              + " || EndMonth: " + endMonth + " End Day: " + endDay));

      // Since we know all farms we can parse through farm names
      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      int totalMilkOfEveryFarmRange = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        int totalMilkOfFarmInRange = 0;
        for (Month m : months) {
          if (m.month >= startMonth || m.month <= endMonth) {
            int[] days = m.getDays();
            // Case 1: Range is within 1 month
            if (startMonth == endMonth) {
              for (int i = startDay; i < endDay; i++) {
                totalMilkOfFarmInRange += days[i];
              }
            }
            // Case 2: Range is between 2+ months
            if ((startMonth != endMonth) && (endMonth - startMonth > 1)) {
              if (m.month == startMonth) {
                for (int i = startDay; i < days.length; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              } else if (m.month == endMonth) {
                for (int i = 0; i < endDay; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              } else {
                for (int i = 0; i < days.length; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              }
            }
          }
        }
        totalMilkOfEveryFarmRange += totalMilkOfFarmInRange;
      }

      lines.add(new TextField("Total: " + totalMilkOfEveryFarmRange));

      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        int totalMilkOfFarmInRange = 0;
        for (Month m : months) {
          if (m.month >= startMonth || m.month <= endMonth) {
            int[] days = m.getDays();
            // Case 1: Range is within 1 month
            if (startMonth == endMonth) {
              for (int i = startDay; i < endDay; i++) {
                totalMilkOfFarmInRange += days[i];
              }
            }
            // Case 2: Range is between 2+ months
            if ((startMonth != endMonth) && (endMonth - startMonth > 1)) {
              if (m.month == startMonth) {
                for (int i = startDay; i < days.length; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              } else if (m.month == endMonth) {
                for (int i = 0; i < endDay; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              } else {
                for (int i = 0; i < days.length; i++) {
                  totalMilkOfFarmInRange += days[i];
                }
              }
            }
          }
        }
        float percent = totalMilkOfEveryFarmRange / totalMilkOfFarmInRange;
        lines.add(new TextField(targetFarm + ": " + totalMilkOfFarmInRange
            + " (" + percent + "%)"));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }

  // Commands Based on GUI Selection
  /**
   * Load data from a csv/txt file into the data structure
   */
  private void loadDataFromCSV() {

    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("File Path: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String path = this.UTITextField.getText();
      FileManager.readFromFile(path);
      this.msgTextField.clear();
      this.msgTextField.setText("Task Completed Succesfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }

  /**
   * Load every file in a folder into the data structure
   */
  private void loadDataFromDir() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("File Folder Path: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String path = this.UTITextField.getText();
      FileManager.readFromDir(path);
      this.msgTextField.clear();
      this.msgTextField.setText("Task Completed Succesfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }

  }

  private void editFile() {

  }

  private void editFarm() {

  }

  private void newExportFarmToFile() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Please Give Target Farm: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String farmID = this.UTITextField.getText();

      // Check if input is valid
      if (farmID == null || !farmMap.containsKey(farmID)) {
        this.msgTextField.clear();
        this.msgTextField.setText("Please give a real ID");
        return;
      }
      this.exportFarmID = farmID;

      this.msgTextField.clear();
      this.msgTextField.setText("Please give export file path:");
      actionFlag++;
      return;
    }
    if (actionFlag == 2) {
      String filePath = this.UTITextField.getText();
      try {
        FileManager.exportFarmToFile(filePath, this.exportFarmID);
        this.msgTextField.clear();
        this.msgTextField.setText("Task Completed Succesfully:");
        actionFlag = 0;
        return;
      } catch (Exception e) {
        this.msgTextField.clear();
        this.msgTextField.setText("Task Failed: " + e.getMessage());
        actionFlag = 0;
        return;
      }
    }
  }

  /**
   * Export the statistics of the data structure into a text file
   */
  private void newExportStats() {

  }

  /**
   * Allows the user to create a custom from the GUI
   */
  private void newCustomFarm() {

  }

  /**
   * Show the maximum sales it made for a given month """ Out of X Months Max
   * Sales =
   * 
   */
  private void showMaxSales() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        return;
      }

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();

      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);

      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });


      lines.add(new TextField("FarmID: " + targetFarm + " Year " + targetYear));
      for (Month m : months) {
        int maxMilk = 0;
        int[] days = m.getDays();
        for (int i = 0; i < days.length; i++) {
          if (days[i] > maxMilk) {
            maxMilk = days[i];
          }
        }
        lines.add(new TextField(m.getName() + ": " + maxMilk));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }



  /**
   * Shows the minimum sales a farm made for a given month out of x months
   */
  private void showMinSales() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        return;
      }

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();

      // HashMap<String, Month> farmData = farmMap.get(targetFarm).farmData;

      int totalMilkOfYear = 0;
      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);

      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });


      lines.add(new TextField("FarmID: " + targetFarm + " Year " + targetYear));
      for (Month m : months) {
        int[] days = m.getDays();
        int minMilk = days[0];
        for (int i = 0; i < days.length; i++) {
          if (days[i] < minMilk) {
            minMilk = days[i];
          }
        }
        lines.add(new TextField(m.getName() + ": " + minMilk));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }

  /**
   * Shows the average sales a farm made monthly
   */
  private void showAvgSales() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        return;
      }

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();

      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);

      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });


      lines.add(new TextField("FarmID: " + targetFarm + " Year " + targetYear));
      for (Month m : months) {
        int[] days = m.getDays();
        int runningTotal = 0;
        for (int i = 0; i < days.length; i++) {
          runningTotal += days[i];
        }
        float avgMilk = runningTotal / days.length;
        lines.add(new TextField(m.getName() + ": " + avgMilk));
      }

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }

  }

  /**
   * Shows the deviation in sales for a specified farm
   */
  private void showDevSales() {
    if (actionFlag == 0) {
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) {
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        return;
      }

      // Vbox
      VBox vBox = new VBox();

      ObservableList list = vBox.getChildren();

      ArrayList<TextField> lines = new ArrayList<TextField>();

      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);

      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });

      float monthlyDev = 0;
      float monthlyRunningTotal = 0;
      float monthlyAvg = 0;
      // Dev = sqrt((sum of mean - xi)^2/N)
      lines.add(new TextField("FarmID: " + targetFarm + " Year " + targetYear));
      for (Month m : months) {
        int[] days = m.getDays();
        float runningTotal = 0;
        for (int i = 0; i < days.length; i++) {
          runningTotal += days[i];
        }

        monthlyRunningTotal += runningTotal; // deviation for months

        float avgMilk = runningTotal / days.length;

        runningTotal = 0;
        for (int i = 0; i < days.length; i++) {
          runningTotal += (avgMilk - days[i]) * (avgMilk - days[i]);
        }
        runningTotal /= days.length;
        float dev = (float) Math.pow(runningTotal, .5);

        lines.add(new TextField(m.getName() + ": " + dev));
      }


      monthlyAvg = monthlyRunningTotal / months.size();
      for (Month m : months) {
        int[] days = m.getDays();
        float totalMilk = 0;
        for (int i = 0; i < days.length; i++) {
          totalMilk += days[i];
        }
        monthlyDev += (monthlyAvg - totalMilk) * (monthlyAvg - totalMilk);
      }
      monthlyDev /= months.size();
      monthlyDev = (float) Math.pow(monthlyDev, .5);
      lines.add(new TextField("Deviation For All Months: " + monthlyDev));

      list.addAll(lines);

      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.UTITextField.clear();
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
