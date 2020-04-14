package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 600;
  private static final String APP_TITLE = "ATEAM 201 Milk Weights GUI";


  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane borderPaneRoot = new BorderPane();
    
    Scene mainScene = new Scene(borderPaneRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

    // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);


    // TOP PANEL
    // Create Title
    Label title = new Label("Milk Weights Dashboard");
    title.setStyle("-fx-font-weight: bold");
    title.setFont(new Font("Arial", 30));
    // Spacer
    Label spacerTop = new Label("        ");
    // Load Combo box
    String[] loadOptions = {"Load File", "Load Dir", ""};
    ComboBox loadBox =
        new ComboBox(FXCollections.observableArrayList(loadOptions));
    // Edit Combo box
    String[] editOptions = {"Edit File", "Edit Farm", ""};
    ComboBox editBox =
        new ComboBox(FXCollections.observableArrayList(editOptions));
    // New Combo box
    String[] newOptions = {"Export File", "Export Stats", "New Farm", ""};
    ComboBox newBox =
        new ComboBox(FXCollections.observableArrayList(newOptions));
    // Stats Combo box
    String[] statsOptions =
        {"Max Stats", "Min Sales", "Avg Sales", "Dev. Sales", ""};
    ComboBox statsBox =
        new ComboBox(FXCollections.observableArrayList(statsOptions));
    // Packing
    HBox topHBox = new HBox();
    topHBox.getChildren().addAll(title, spacerTop, loadBox, editBox, newBox,
        statsBox);
    borderPaneRoot.setTop(topHBox);

    // LEFT PANEL
    // Message Box Label
    Label msgBoxLabel = new Label("Message Output");
    msgBoxLabel.setStyle("-fx-font-weight: bold");
    msgBoxLabel.setFont(new Font("Arial", 18));
    // Text Field
    TextField msgTextField = new TextField();
    msgTextField.setPrefWidth(100);
    msgTextField.setPrefHeight(300);
    
    msgTextField.setPrefHeight(300);
    msgTextField.setMaxWidth(200);
    // Spacer
    Label spacerLeft = new Label("        ");
    // Packing
    VBox leftVBox = new VBox();
    leftVBox.getChildren().addAll(spacerLeft, msgBoxLabel, msgTextField);
    
    borderPaneRoot.setLeft(leftVBox);

    // CENTER PANEL
    // User Text Interface Label
    Label UTILabel = new Label("User Text Interface");
    UTILabel.setStyle("-fx-font-weight: bold");
    UTILabel.setFont(new Font("Arial", 18));
    // Text Field
    TextField UTITextField = new TextField();
    UTITextField.setPrefWidth(100);
    UTITextField.setPrefHeight(300);
    
    UTITextField.setPrefHeight(300);
    UTITextField.setMaxWidth(200);
    
    // Spacer
    Label spacerCenter = new Label("        ");
    // Packing
    VBox centerVBox = new VBox();
    centerVBox.getChildren().addAll(spacerCenter, UTILabel, UTITextField);
    centerVBox.setAlignment(Pos.TOP_CENTER);
    borderPaneRoot.setCenter(centerVBox);

    // RIGHT PANEL
    // Execute Label
    Label executeLabel = new Label("EXECUTE");
    executeLabel.setStyle("-fx-font-weight: bold");
    executeLabel.setFont(new Font("Arial", 24));
    // Text Field
    Button execButton = new Button();
    // Spacer
    Label spacerRight = new Label("        ");
    // Packing
    VBox rightVBox = new VBox();
    rightVBox.getChildren().addAll(spacerRight, executeLabel, execButton);
    rightVBox.setAlignment(Pos.TOP_CENTER);
    borderPaneRoot.setRight(rightVBox);


    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
