package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main - The GUI of the program
 *
 * @author Russel Mendes 2020
 */
public class Main extends Application {

  //==========================================================================================
  // Fields
  //==========================================================================================
	
  // Final Fields
  private static final int WINDOW_WIDTH = 1200;
  private static final int WINDOW_HEIGHT = 600;
  private static final String APP_TITLE = "ATEAM 201 Milk Weights GUI";

  // Data Field --> FarmID, Farm
  private HashMap<String, Farm> farmMap = new HashMap<String, Farm>();
  private ArrayList<String> farmNames = new ArrayList<String>();
  // Keeps track of actions done by user
  private ArrayList<String> actionLog = new ArrayList<String>();

  private String commandFlag;
  private int actionFlag;
  private String exportFarmID;

  // Init Fields
  // BORDER PANE
  BorderPane borderPaneRoot;
  // TOP PANEL
  Label title;
  Label spacerTop;
  VBox loadVBox;
  VBox editVBox;
  VBox newVBox;
  VBox statsVBox;
  VBox asgVBox;
  Label loadLabel;
  Label editLabel;
  Label newLabel;
  Label statsLabel;
  Label asgLabel;
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
  Label exitLabel;
  Button exitButton;
  Label spacerRight;
  VBox rightVBox;

  //==========================================================================================
  // Initializing GUI Components
  //==========================================================================================
  
  /**
   * Initialization Function
   */
  @SuppressWarnings("unchecked")
  @Override
  public void start(Stage primaryStage) throws Exception {

    borderPaneRoot = new BorderPane();
    borderPaneRoot.setStyle("-fx-background-color: #EDDBB7");
    
    Scene mainScene = new Scene(borderPaneRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);

    // TOP PANEL
    // Create Title
    title = new Label("Milk Weights Dashboard");
    title.setFont(new Font("Montserrat", 35));
    title.setTextFill(Color.web("#268AE3"));
    // Spacer
    spacerTop = new Label("        ");
    
    // Load Combo box
    // The blank option allows the combobox have a "empty" screen
    String[] loadOptions = {"Load File", "Load Directory", ""};
    loadVBox = new VBox();
    loadLabel = new Label("Load Commands    ");
    loadLabel.setStyle("-fx-font-weight: bold");
    loadLabel.setFont(new Font("Montserrat", 16));
    loadLabel.setTextFill(Color.web("#BF3604"));
    loadLabel.setTextFill(Color.web("#BF3604"));
    loadBox =
        new ComboBox<String>(FXCollections.observableArrayList(loadOptions));
    loadBox.setOnAction(e -> handleBoxSelection(loadBox,"Load"));
    loadBox.setMaxWidth(115);
    loadVBox.getChildren().addAll(loadLabel, loadBox);

    // Edit Combo box
    String[] editOptions =
        {"Edit Farm", "New Farm", "Delete Farm", ""};
    editVBox = new VBox();
    editLabel = new Label("Edit Commands    ");
    editLabel.setStyle("-fx-font-weight: bold");
    editLabel.setFont(new Font("Montserrat", 16));
    editLabel.setTextFill(Color.web("#BF3604"));
    editBox =
        new ComboBox<String>(FXCollections.observableArrayList(editOptions));
    editBox.setOnAction(e -> handleBoxSelection(editBox,"Edit"));
    editVBox.getChildren().addAll(editLabel, editBox);

    // New Combo box
    String[] newOptions =
        {"Export Farm", "Export Stats", "Show Log", "Export Log", ""};
    newVBox = new VBox();
    newLabel = new Label("Export Commands   ");
    newLabel.setStyle("-fx-font-weight: bold");
    newLabel.setFont(new Font("Montserrat", 16));
    newLabel.setTextFill(Color.web("#BF3604"));
    newBox =
        new ComboBox<String>(FXCollections.observableArrayList(newOptions));
    newBox.setOnAction(e -> handleBoxSelection(newBox,"New"));
    newBox.setMaxWidth(127);
    newVBox.getChildren().addAll(newLabel, newBox);

    // Stats Combo box
    String[] statsOptions =
        {"Max Sales", "Min Sales", "Avg Sales", "Dev. Sales", ""};
    statsVBox = new VBox();
    statsLabel = new Label("Stats Commands  ");
    statsLabel.setStyle("-fx-font-weight: bold");
    statsLabel.setFont(new Font("Montserrat", 16));
    statsLabel.setTextFill(Color.web("#BF3604"));
    statsBox =
        new ComboBox<String>(FXCollections.observableArrayList(statsOptions));
    statsBox.setOnAction(e -> handleBoxSelection(statsBox,"Stats"));
    statsBox.setMaxWidth(118);
    statsVBox.getChildren().addAll(statsLabel, statsBox);

    // Assignment Combo box
    String[] AssignmentOptions = {"Farm Report", "Annual Report",
        "Monthly Sales", "Data Range Report", ""};
    asgVBox = new VBox();
    asgLabel = new Label("Asg Commands ");
    asgLabel.setStyle("-fx-font-weight: bold");
    asgLabel.setFont(new Font("Montserrat", 16));
    asgLabel.setTextFill(Color.web("#BF3604"));
    asgBox = new ComboBox<String>(
        FXCollections.observableArrayList(AssignmentOptions));
    asgBox.setOnAction(e -> handleBoxSelection(asgBox,"Assignments"));
    asgVBox.getChildren().addAll(asgLabel, asgBox);

    // Packing
    topHBox = new HBox();
    topHBox.getChildren().addAll(title, spacerTop, loadVBox, editVBox, newVBox,
        statsVBox, asgVBox);
    topHBox.setSpacing(20);
    borderPaneRoot.setTop(topHBox);

    // LEFT PANEL
    // Message Box Label
    msgBoxLabel = new Label("Message Output");
    msgBoxLabel.setFont(new Font("Montserrat", 18));
    msgBoxLabel.setTextFill(Color.web("#268AE3"));
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
    UTILabel.setFont(new Font("Montserrat", 18));
    UTILabel.setTextFill(Color.web("#268AE3"));
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

    // Text Field
    execButton = new Button();
    execButton.setText("EXECUTE");
    execButton.setStyle("-fx-font-weight: bolder");
    execButton.setFont(new Font("Montserrat", 24));
    execButton.setStyle("-fx-font-size: 24");
    execButton.setTextFill(Color.web("#A8131E"));
    // Format Works
    // Lambda Functions
    execButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            e -> {
                try { // prevent index out of bounds error when execute button is clicked without
                      // using a command
                    executeSelection();
                }
                catch(ArrayIndexOutOfBoundsException x) {
                    this.msgTextField.setText("Please enter a command before executing");
                }
            });

    // Text Field
    purgeButton = new Button();
    purgeButton.setText("CLEAR CONSOLE");
    purgeButton.setStyle("-fx-font-weight: bolder");
    purgeButton.setFont(new Font("Montserrat", 24));
    purgeButton.setStyle("-fx-font-size: 24");
    purgeButton.setTextFill(Color.web("#A8131E"));
    
    
    // Format Works
    // Lambda Functions
    purgeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      this.actionFlag = 0;
      this.commandFlag = null;
      this.msgTextField.clear();
      this.UTITextField.clear();
      this.borderPaneRoot.setBottom(null);
      this.actionLog.add("User has selected Purge");
    });

    //Exit button - quits program when pressed
    exitButton = new Button();
    exitButton.setText("EXIT PROGRAM");
    exitButton.setStyle("-fx-font-weight: bolder");
    exitButton.setFont(new Font("Montserrat", 24));
    exitButton.setStyle("-fx-font-size: 24");
    exitButton.setTextFill(Color.web("#A8131E"));
    
    exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
        e -> ((Stage) exitButton.getScene().getWindow()).close());

    // Spacer
    spacerRight = new Label("        ");
    // Packing
    rightVBox = new VBox();
    rightVBox.getChildren().addAll(spacerRight, execButton,
        purgeButton, exitButton);
    rightVBox.setSpacing(5);
    rightVBox.setAlignment(Pos.TOP_CENTER);
    borderPaneRoot.setRight(rightVBox);

    // Bottom Panel
    // Going to be a VBOX of info. This will be populated by functions

    this.actionLog.add("Start Up Complete");
    primaryStage.show();
  }

  //==========================================================================================
  // Selection Functions
  //==========================================================================================
  
  /**
   * executing the commands selected by the user
   */
  private void executeSelection() {

    // If there is no actions taken, get the latest command by the user
    if (actionFlag == 0) {
      String command = this.msgTextField.getText();
      String[] commandSplit = command.split(": ");
      commandFlag = commandSplit[1];
      this.actionLog.add("Excution Parsed Command: " + commandFlag);
    }

    this.actionLog.add("Entering Command: " + commandFlag + " Action Step: "
        + this.actionFlag);

    // Depending on the command chosen by the user execute the command
    if (commandFlag.equals("Load File")) this.loadDataFromCSV();
    else if (commandFlag.equals("Load Directory")) this.loadDataFromDir();
    else if (commandFlag.equals("Edit Farm")) this.editFarm();
    else if (commandFlag.equals("Delete Farm")) this.deleteFarm();
    else if (commandFlag.equals("Export Farm")) this.newExportFarmToFile();
    else if (commandFlag.equals("Export Stats")) this.newExportStats();
    else if (commandFlag.equals("Show Log")) this.showLogs();
    else if (commandFlag.equals("Export Log")) this.exportLogsToFile();
    else if (commandFlag.equals("New Farm")) this.newCustomFarm();
    else if (commandFlag.equals("Max Sales"))  this.showMaxSales();
    else if (commandFlag.equals("Min Sales")) this.showMinSales();
    else if (commandFlag.equals("Avg Sales")) this.showAvgSales();
    else if (commandFlag.equals("Dev. Sales")) this.showDevSales();
    else if (commandFlag.equals("Farm Report")) this.farmReport();
    else if (commandFlag.equals("Annual Report")) this.annualReport();
    else if (commandFlag.equals("Monthly Sales")) this.monthlyReport();
    else if (commandFlag.equals("Data Range Report")) this.dateRangeReport();
    else {
      // Awaiting Further Command
      commandFlag = "";
    }
  }
  
  /**
   * Helper method for selecting boxes
   */
  private void handleBoxSelection(ComboBox<String> vbox, String boxName) {
	// Get the command
	    String command = vbox.getSelectionModel().getSelectedItem();
	    // Set all other boxes to empty
	    this.editBox.getSelectionModel().clearSelection();
	    this.newBox.getSelectionModel().clearSelection();
	    this.statsBox.getSelectionModel().clearSelection();
	    this.loadBox.getSelectionModel().clearSelection();

	    // Send off the appropriate command
	    this.actionLog.add("User has entered the "+boxName+" Selection Box");
	    showLoadSelection(command);
  }
  
  /**
   * Display the command on the screen via a text field
   * 
   * @param command - command chosen by the user
   */
  private void showLoadSelection(String command) {
    this.msgTextField.clear();
    String message = "Loaded Command: ";
    message += command;
    this.msgTextField.setText(message);

    // Keep Track of Commands
    this.actionLog.add("User Selected The Following Command: " + command);
  }

  //==========================================================================================
  // Load Functions
  //==========================================================================================
  
  /**
   * Load data from a csv/txt file into the data structure
   */
  private void loadDataFromCSV() {

    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Enter File Path: ");
      this.actionLog.add("Program Prompted for File Path");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String path = this.UTITextField.getText();
      
      // Read file
      String response =
          FileManager.readFromFile(path, this.farmMap, this.farmNames);
      
      // Update text fields
      this.msgTextField.clear();
      if (response == null) {
        this.msgTextField.setText("Task Completed Succesfully");
        this.actionLog.add("Reading from CSV Completed Successfully");
      } else {
        this.actionLog.add("Errors Occured: " + response);
        this.msgTextField.setText("File Loaded: " + response);
      }
      this.UTITextField.clear();
      actionFlag = 0;

      return;
    }
  }

  /**
   * Load every file in a folder into the data structure
   */
  private void loadDataFromDir() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("File Folder Path: ");
      this.actionLog.add("Program Prompts File Folder Path");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String path = this.UTITextField.getText();
      
      // Read files
      String response =
          FileManager.readFromDir(path, this.farmMap, this.farmNames);
      
      // Update text fields
      this.msgTextField.clear();
      if (response == null) {
        this.msgTextField.setText("Task Completed Succesfully");
        this.actionLog.add("Reading from Dir Completed Successfully");
      } else {
        this.actionLog.add("Errors Occured: " + response);
        this.msgTextField.setText(response);
        System.out.println(response);
      }

      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }

  }
  
  //==========================================================================================
  // Edit Functions
  //==========================================================================================
  
  /**
   * Delete a farm from the data structure
   */
  private void deleteFarm() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Purge Farm ID: ");
      this.actionLog.add("Program Prompts Purge ID");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String deleteID = this.UTITextField.getText();
      farmMap.remove(deleteID);

      // Update text fields
      this.msgTextField.clear();
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Farm Delete Completed Successfully");
      actionFlag = 0;
      return;
    }
  }

  /**
   * Edits a specific day of a farm
   */
  private void editFarm() {
	  if (actionFlag == 0) { // Upon executing this command
          this.msgTextField.clear();
          this.msgTextField.setText("Edit the milk for a day of a farm: farmID,YYYY-MM-DD,milk");
          this.actionLog.add("Program Prompted for Day");
          this.actionFlag++;
          return;
        }
      if (actionFlag == 1) { // Upon executing this command again
          this.actionLog.add("User Args: " + this.UTITextField.getText());
          String[] args = this.UTITextField.getText().split(",");
          String[] dateComponents = args[1].split("-");
    	  String farmID = args[0];
    	  
    	  try {
    		  farmMap.get(farmID).updateMilkOnDate(Integer.parseInt(args[2]), dateComponents[1], dateComponents[0],
    				  Integer.parseInt(dateComponents[2]));
    	  } catch (NumberFormatException e) {
    		  
    	  }
    	  
    	  // Update text fields
    	  this.msgTextField.setText("Task Completed Successfully");
          this.actionLog.add("Edited Milk Completed Successfully");
          this.UTITextField.clear();
          actionFlag = 0;
          return;
      }
  }
  
  /**
   * Allows the user to create a custom from the GUI
   */
  private void newCustomFarm() {
      if (actionFlag == 0) { // Upon executing this command
          this.msgTextField.clear();
          this.msgTextField.setText("Enter desired Farm ID: ");
          this.actionLog.add("Program Prompted for Farm ID");
          this.actionFlag++;
          return;
        }
      if (actionFlag == 1) { // Upon executing this command again
          this.actionLog.add("User Args: " + this.UTITextField.getText());
    	  String farmID = this.UTITextField.getText();
    	  farmMap.put(farmID, new Farm(farmID));
    	  
    	  // Update text fields
    	  this.msgTextField.setText("Task Completed Successfully");
          this.actionLog.add("Create Custom Farm Completed Successfully");
          this.UTITextField.clear();
          actionFlag = 0;
          return;
      }
  }
  
  //==========================================================================================
  // Export Functions
  //==========================================================================================
  
  private void newExportFarmToFile() {
	    if (actionFlag == 0) { // Upon executing this command
	      this.msgTextField.clear();
	      this.msgTextField.setText("Please Give Target Farm: ");
	      this.actionLog.add("Program Prompts Target Farm");
	      this.actionFlag++;
	      return;
	    }
	    if (actionFlag == 1) { // Upon executing this command again
	      this.actionLog.add("User Args: " + this.UTITextField.getText());
	      String farmID = this.UTITextField.getText();

	      // Check if input is valid
	      if (farmID == null || !farmMap.containsKey(farmID)) {
	        this.msgTextField.clear();
	        this.msgTextField.setText("Please give a real ID");
	        this.actionLog.add("User Gave invalid Farm ID");
	        return;
	      }
	      this.exportFarmID = farmID;

	      // Update text fields
	      this.msgTextField.clear();
	      this.actionLog.add("Program Prompts File Export Path");
	      this.msgTextField.setText("Please give export file path:");
	      actionFlag++;
	      return;
	    }
	    if (actionFlag == 2) { // Upon executing this command third time
	      this.actionLog.add("User Args: " + this.UTITextField.getText());
	      String filePath = this.UTITextField.getText();
	      try {
	        FileManager.exportFarmToFile(filePath, this.exportFarmID, farmMap);
	        
	        // Update text fields
	        this.msgTextField.clear();
	        this.actionLog.add("Exporting Farm Task Completed Successfully");
	        this.msgTextField.setText("Task Completed Succesfully:");
	        actionFlag = 0;
	        return;
	      } catch (Exception e) {
	        this.msgTextField.clear();
	        this.msgTextField.setText("Task Failed: " + e.getMessage());
	        this.actionLog.add("Task Failed: " + e.getMessage());
	        actionFlag = 0;
	        return;
	      }
	    }
	  }

  /**
   * Export the statistics of the data structure into a text file
   */
  private void newExportStats() {
    String targetFarm = "";
      int targetYear = 0;
      if (actionFlag == 0) { // Upon clicking the drop down
          this.msgTextField.clear();
          this.msgTextField.setText("Farm ID,Year: ");
          this.actionLog.add("Program Prompts: Farm ID,Year");
          this.actionFlag++;
          return;
        }
      if (actionFlag == 1) { // Upon executing the command
          this.actionLog.add("User Args: " + this.UTITextField.getText());
          String[] args = this.UTITextField.getText().split(",");
          targetFarm = args[0];
          targetYear = Integer.parseInt(args[1]);
          this.msgTextField.clear();

          if (!farmMap.containsKey(targetFarm)) {
            this.msgTextField.setText("Farm Does Not Exist");
            this.actionLog.add("User Farm Does Not Exist");
            return;
          }
          
          // Update text fields
          this.msgTextField.clear();
          this.actionLog.add("Program Prompts File Export Path");
          this.msgTextField.setText("Please give export file path:");
          actionFlag++;
          return;
        }
        if (actionFlag == 2) { // Getting next input
          this.actionLog.add("User Args: " + this.UTITextField.getText());
          // Calculate all the stats
          List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);
          List<String> month = new ArrayList<String>();
          List<Integer> max = new ArrayList<Integer>();
          List<Integer> min = new ArrayList<Integer>();
          List<Float> devi = new ArrayList<Float>();
          List<Float> avg = new ArrayList<Float>();

          for (Month m : months) {
              month.add(m.getName());
              int[] days = m.getDays();
              int minMilk = days[0];
              int maxMilk = 0;
              int runningTotal = 0;
              // Calculate minsales
              for (int i = 0; i < days.length; i++) {
                if (days[i] < minMilk) {
                  minMilk = days[i];
                }
              }
              min.add(minMilk);
              // Calculate maxsales
              for (int i = 0; i < days.length; i++) {
                  if (days[i] > maxMilk) {
                    maxMilk = days[i];
                  }
                }
              max.add(maxMilk);
              // Calculate averagesales
              for (int i = 0; i < days.length; i++) {
                  runningTotal += days[i];
                }
              float avgMilk = runningTotal / days.length;
              avg.add(avgMilk);
          }
          // Calculate the deviation in sales
          float monthlyDev = 0;
          float monthlyRunningTotal = 0;
          float monthlyAvg = 0;
          // Dev = sqrt((sum of mean - xi)^2/N)
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
            devi.add(dev);
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

          // Get path and write
          String filePath = this.UTITextField.getText();
          try {
            FileManager.writeStatsToFile(month, max, min, avg, devi, monthlyDev, filePath, targetFarm, targetYear);
            
            // Update text fields
            this.msgTextField.clear();
            this.actionLog.add("Exporting Statistics Task Completed Successfully");
            this.msgTextField.setText("Task Completed Succesfully:");
            actionFlag = 0;
            return;
          } catch (Exception e) {
            this.msgTextField.clear();
            this.msgTextField.setText("Task Failed: " + e.getMessage());
            this.actionLog.add("Task Failed: " + e.getMessage());
            actionFlag = 0;
            return;
          }
        }
  }
  
  /**
   * Show the logs of the entire program so far
   */
  private void showLogs() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.actionFlag = 0;

      // Create Vbox of lines
      VBox masterVbox = new VBox();
      for (int i = 0; i < this.actionLog.size(); i++) {
        Label tmpLabel = new Label();
        tmpLabel.setText(this.actionLog.get(i));
        masterVbox.getChildren().add(tmpLabel);
      }

      // Makes it scrollable
      ScrollPane sp = new ScrollPane();
      sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      sp.prefHeightProperty().set(300);
      sp.setContent(masterVbox);
      this.borderPaneRoot.setBottom(sp);
      return;
    }
  }

  /**
   * Export the logs to a file defined by the user
   */
  private void exportLogsToFile() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Please Give Path: ");
      this.actionLog.add("Program Prompts: Path");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String filePath = this.UTITextField.getText();
      try {
    	  
    	// Exporting and updating text fields
        FileManager.exportLogsToFile(filePath, this.actionLog);
        this.msgTextField.clear();
        this.actionLog.add("Exporting Farm Task Completed Successfully");
        this.msgTextField.setText("Task Completed Succesfully:");
        actionFlag = 0;
        return;
      } catch (Exception e) {
        this.msgTextField.clear();
        this.msgTextField.setText("Task Failed: " + e.getMessage());
        this.actionLog.add("Task Failed: " + e.getMessage());
        actionFlag = 0;
        return;
      }
    }

  }
  
  //==========================================================================================
  // Stats Functions
  //==========================================================================================
  
  /**
   * Show the maximum sales it made for a given month """ Out of X Months Max
   * Sales =
   */
  private void showMaxSales() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionLog.add("Program Prompts: Farm ID,Year");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        this.actionLog.add("User Farm Does Not Exist");
        return;
      }

      // Vbox for footer
      VBox vBox = new VBox();
      ObservableList list = vBox.getChildren();
      ArrayList<TextField> lines = new ArrayList<TextField>();
      List<Month> months = getMonths(targetFarm,targetYear);

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

      // Update text fields
      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Max Sales Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Shows the minimum sales a farm made for a given month out of x months
   */
  private void showMinSales() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionLog.add("Program Prompts: Farm ID,Year");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        this.actionLog.add("User Farm Does Not Exist");
        return;
      }

      // Vbox for footer
      VBox vBox = new VBox();
      ObservableList list = vBox.getChildren();
      ArrayList<TextField> lines = new ArrayList<TextField>();
      List<Month> months = getMonths(targetFarm,targetYear);

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

      // Updates text fields
      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Min Sales Task Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Shows the average sales a farm made monthly
   */
  private void showAvgSales() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionLog.add("System Prompted: Farm ID,Year");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        this.actionLog.add("User Farm Does Not Exist");
        return;
      }

      // Vbox for footer
      VBox vBox = new VBox();
      ObservableList list = vBox.getChildren();
      ArrayList<TextField> lines = new ArrayList<TextField>();
      List<Month> months = getMonths(targetFarm,targetYear);

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

      // Updating text fields
      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Average Task Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Shows the deviation in sales for a specified farm
   */
  private void showDevSales() {
    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionLog.add("Program Prompts: Farm ID,Year");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(",");
      String targetFarm = args[0];
      int targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        this.actionLog.add("User Farm Does Not Exist");
        return;
      }

      // Vbox for footer
      VBox vBox = new VBox();
      ObservableList list = vBox.getChildren();
      ArrayList<TextField> lines = new ArrayList<TextField>();
      List<Month> months = getMonths(targetFarm,targetYear);

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

      // Updates text fields
      this.borderPaneRoot.setBottom(vBox);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Standard Deviation Task Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  //==========================================================================================
  // Assignment Functions
  //==========================================================================================
  
  @SuppressWarnings("rawtypes")
  /**
   * Prompt user for a farm id and year (or use all available data) Then,
   * display the total milk weight and percent of the total of all farm for each
   * month. Sort, the list by month number 1-12, show total weight, then that
   * farm's percent of the total milk received for each month.
   */
  private void farmReport() {
    String targetFarm;
    int targetYear;

    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Farm ID,Year: ");
      this.actionFlag++;
      this.actionLog.add("Program Prompted: Farm ID,Year");
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
      this.actionLog.add("User Args: " + this.UTITextField.getText());

      String[] args = this.UTITextField.getText().split(",");
      targetFarm = args[0];
      targetYear = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      if (!farmMap.containsKey(targetFarm)) {
        this.msgTextField.setText("Farm Does Not Exist");
        this.actionLog.add("User chosen Target Does not Exist: " + targetFarm);
        return;
      }

      ArrayList<String> lines = new ArrayList<String>();

      // Get the list of months and sort them
      List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);
      Collections.sort(months, new Comparator<Month>() {

        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });

      // Get the total milk of the year
      float totalMilkOfYear = 0;
      for (Month m : months) {
        totalMilkOfYear += m.totalMilk();
      }

      lines.add("FarmID: " + targetFarm + " Year " + targetYear);

      // Get the percentage of milk per month
      for (Month m : months) {
        float milk = m.totalMilk();
        float percent = (milk * 100) / totalMilkOfYear;
        lines.add(m.getName() + ": " + milk + " (" + percent + "%)");
      }
      lines.add("Total: " + totalMilkOfYear);

      // Transform lines into GUI output
      VBox masterVbox = new VBox();
      for (int i = 0; i < lines.size(); i++) {
        Label tmpLabel = new Label();
        tmpLabel.setText(lines.get(i));
        masterVbox.getChildren().add(tmpLabel);
      }

      ScrollPane sp = new ScrollPane();
      sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      sp.prefHeightProperty().set(300);
      sp.setContent(masterVbox);

      this.borderPaneRoot.setBottom(sp);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Farm Report Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Ask for year. Then display list of total weight and percent of total weight
   * of all farms by farm for the year. Sort by Farm ID, or you can allow the
   * user to select display ascending or descending by weight.
   */
  private void annualReport() {
    int targetYear;

    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Year: ");
      this.actionLog.add("Program Prompts For Year");
      this.actionFlag++;
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again
 
      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String args = this.UTITextField.getText();
      targetYear = Integer.parseInt(args);
      this.msgTextField.clear();

      ArrayList<String> lines = new ArrayList<String>();

      // Since we know all farms we can parse and sortthrough farm names
      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      // Get the total milk of all the farms
      float totalMilkOfEveryFarm = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);
        int totalMilkOfFarmInYear = 0;
        for (Month m : months) {
          totalMilkOfFarmInYear += m.totalMilk();
        }
        totalMilkOfEveryFarm += totalMilkOfFarmInYear;
      }

      lines.add("Total: " + totalMilkOfEveryFarm);
      // Calculate the contribution of each farm to the total
      float maxPercent = 0;
      String maxFarm = tempFarmNames.get(0);
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);
        float totalMilkOfFarmInYear = 0;
        for (Month m : months) {
          totalMilkOfFarmInYear += m.totalMilk();
        }
        float percent = totalMilkOfFarmInYear / totalMilkOfEveryFarm;
        if (percent > maxPercent) {
          maxFarm = targetFarm;
        }
        lines.add(
            targetFarm + ": " + totalMilkOfFarmInYear + " (" + percent + "%)");
      }

      // Transform lines into GUI output
      VBox masterVbox = new VBox();
      for (int i = 0; i < lines.size(); i++) {
        Label tmpLabel = new Label();
        tmpLabel.setText(lines.get(i));
        masterVbox.getChildren().add(tmpLabel);
      }

      ScrollPane sp = new ScrollPane();
      sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      sp.prefHeightProperty().set(300);
      sp.setContent(masterVbox);

      this.borderPaneRoot.setBottom(sp);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Annual Report Task Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Ask for year and month. Then, display a list of totals and percent of total
   * by farm. The list must be sorted by Farm ID, or you can prompt for
   * ascending or descending by weight.
   */
  private void monthlyReport() {
    int targetYear = 0;
    int targetMonth = 0;

    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField.setText("Numerically: Year,Month: ");
      this.actionFlag++;
      this.actionLog.add("Program Prompts: Year,Month");
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again

      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(",");
      targetYear = Integer.parseInt(args[0]);
      targetMonth = Integer.parseInt(args[1]);
      this.msgTextField.clear();

      ArrayList<String> lines = new ArrayList<String>();
      lines.add("Year: " + targetYear + " Month: " + targetMonth);

      // Since we know all farms we can parse through farm names
      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      // Calculate total milk
      float totalMilkOfEveryFarmMonthly = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        float totalMilkOfFarmInMonth = 0;
        for (Month m : months) {
          if (m.month == targetMonth) {
            totalMilkOfFarmInMonth = m.totalMilk();
          }
        }
        totalMilkOfEveryFarmMonthly += totalMilkOfFarmInMonth;
      }

      lines.add("Total: " + totalMilkOfEveryFarmMonthly);

      // Calculate percentages
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        float totalMilkOfFarmInMonth = 0;
        for (Month m : months) {
          if (m.month == targetMonth) {
            totalMilkOfFarmInMonth = m.totalMilk();
          }
        }
        float percent = totalMilkOfFarmInMonth / totalMilkOfEveryFarmMonthly;
        lines.add(
            targetFarm + ": " + totalMilkOfFarmInMonth + " (" + percent + "%)");
      }

      // Vbox for footer
      VBox masterVbox = new VBox();
      for (int i = 0; i < lines.size(); i++) {
        Label tmpLabel = new Label();
        tmpLabel.setText(lines.get(i));
        masterVbox.getChildren().add(tmpLabel);
      }

      ScrollPane sp = new ScrollPane();
      sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      sp.prefHeightProperty().set(300);
      sp.setContent(masterVbox);

      this.borderPaneRoot.setBottom(sp);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Monthly Report Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  /**
   * Prompt user for start date (year-month-day) and end month-day, Then display
   * the total milk weight per farm and the percentage of the total for each
   * farm over that date range. The list must be sorted by Farm ID, or you can
   * prompt for ascending or descending order by weight or percentage
   */
  private void dateRangeReport() {
    int targetYear = 0;
    int startMonth = 0;
    int endMonth = 0;
    int startDay = 0;
    int endDay = 0;

    if (actionFlag == 0) { // Upon executing this command
      this.msgTextField.clear();
      this.msgTextField
          .setText("Numerically Start:End-> Year,Month,Day:Month,Day");
      this.actionFlag++;
      this.actionLog.add("Program Prompted Year,Month,Day:Month,Day");
      return;
    }
    if (actionFlag == 1) { // Upon executing this command again

      this.actionLog.add("User Args: " + this.UTITextField.getText());
      String[] args = this.UTITextField.getText().split(":");
      
      // Parsing date components
      targetYear = Integer.parseInt(args[0].split(",")[0]);
      startMonth = Integer.parseInt(args[0].split(",")[1]);
      startDay = Integer.parseInt(args[0].split(",")[2]);
      endMonth = Integer.parseInt(args[1].split(",")[0]);
      endDay = Integer.parseInt(args[1].split(",")[1]);
      this.msgTextField.clear();

      ArrayList<String> lines = new ArrayList<String>();
      lines.add("Year: " + targetYear + " Month: " + startMonth + " Day: "
          + startDay + " || EndMonth: " + endMonth + " End Day: " + endDay);

      // Since we know all farms we can parse through farm names
      List<String> tempFarmNames = farmNames;
      tempFarmNames.sort(Comparator.comparing(String::toString));

      // Get the total milk
      float totalMilkOfEveryFarmRange = 0;
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        float totalMilkOfFarmInRange = 0;
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

      lines.add("Total: " + totalMilkOfEveryFarmRange);

      // Compute the report
      for (String targetFarm : tempFarmNames) {
        List<Month> months =
            farmMap.get(targetFarm).getMonthsForYear(targetYear);

        float totalMilkOfFarmInRange = 0;
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
        float percent = totalMilkOfFarmInRange / totalMilkOfEveryFarmRange;
        lines.add(
            targetFarm + ": " + totalMilkOfFarmInRange + " (" + percent + "%)");
      }

      // Vbox for footer
      VBox masterVbox = new VBox();
      for (int i = 0; i < lines.size(); i++) {
        Label tmpLabel = new Label();
        tmpLabel.setText(lines.get(i));
        masterVbox.getChildren().add(tmpLabel);
      }

      ScrollPane sp = new ScrollPane();
      sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      sp.prefHeightProperty().set(300);
      sp.setContent(masterVbox);

      this.borderPaneRoot.setBottom(sp);
      this.msgTextField.setText("Task Completed Successfully");
      this.actionLog.add("Range Report Completed Successfully");
      this.UTITextField.clear();
      actionFlag = 0;
      return;
    }
  }

  //==========================================================================================
  // Helper/Misc
  //==========================================================================================
  
  /*
   * Helper for retrieving the months and sorting it
   */
  private List<Month> getMonths(String targetFarm, int targetYear) {
	  List<Month> months = farmMap.get(targetFarm).getMonthsForYear(targetYear);
	  // Sort so months are in order 1-12
      Collections.sort(months, new Comparator<Month>() {
        public int compare(Month m1, Month m2) {
          return m1.getMonthNum() - m2.getMonthNum();
        }
      });      
      return months;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
