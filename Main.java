package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also

  private static final int WINDOW_WIDTH = 600;
  private static final int WINDOW_HEIGHT = 400;
  private static final String APP_TITLE = "ATEAM 201 Milk Weights GUI";

  @Override
  public void start(Stage primaryStage) throws Exception {

    BorderPane root = new BorderPane();
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

  // Add the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);

    //Create Title
    Label title = new Label("Milk Weights Dashboard");
    //Load Combo box
    String[] loadOptions = {"Load File", "Load Dir"};
    ComboBox loadBox =
        new ComboBox(FXCollections.observableArrayList(loadOptions));
    //Edit Combo box
    String[] editOptions = {"Edit File", "Edit Farm"};
    ComboBox editBox =
        new ComboBox(FXCollections.observableArrayList(editOptions));
    //New Combo box
    String[] newOptions = {"Export File", "Export Stats", "New Farm"};
    ComboBox newBox =
        new ComboBox(FXCollections.observableArrayList(newOptions));
    //Stats Combo box
    String[] statsOptions = {"Max Stats", "Min Sales", "Avg Sales", "Dev. Sales"};
    ComboBox statsBox =
        new ComboBox(FXCollections.observableArrayList(statsOptions));

    
    
    
    
    

    primaryStage.show();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
