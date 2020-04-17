package application;

import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
  private String commandFlag;
  private int actionFlag;

  // Init Fields
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
  Label spacerRight;
  VBox rightVBox;

  /**
   * Initialization Function
   */
  @SuppressWarnings("unchecked")
  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane borderPaneRoot = new BorderPane();

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
    String[] editOptions = {"Edit File", "Edit Farm", ""};
    editBox =
        new ComboBox<String>(FXCollections.observableArrayList(editOptions));
    editBox.setOnAction(e -> handleEditSelection());
    // New Combo box
    String[] newOptions = {"Export File", "Export Stats", "New Farm", ""};
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
    msgTextField.setPrefWidth(200);
    msgTextField.setPrefHeight(100);

    msgTextField.setPrefHeight(150);
    msgTextField.setMaxWidth(200);
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
    // Spacer
    spacerRight = new Label("        ");
    // Packing
    rightVBox = new VBox();
    rightVBox.getChildren().addAll(spacerRight, executeLabel, execButton);
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
      
    } else if (commandFlag.equals("Export File")) {
      this.newExportFile();
      
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

  private void farmReport() {
    String targetFarm;
    String targetyear;
    // Prompt User

    // Get Info

    // Process

    // Response

  }

  private void annualReport() {

  }

  private void monthlyReport() {

  }

  private void dateRangeReport() {

  }

  // Commands Based on GUI Selection
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
      System.out.println(Main.farmMap.size());
      actionFlag = 0;
      return;
    }


  }

  private void loadDataFromDir() {

  }

  private void editFile() {

  }

  private void editFarm() {

  }

  private void newExportFile() {

  }

  private void newExportStats() {

  }

  private void newCustomFarm() {

  }

  /**
   * Show the maximum sales it made for a given month """ Out of X Months Max
   * Sales =
   * 
   */
  private void showMaxSales() {

  }

  private void showMinSales() {

  }

  private void showAvgSales() {

  }

  private void showDevSales() {

  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
