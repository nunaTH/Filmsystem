package run;

/**
 * 
 * @author Surattana Bopp <info@surattana.de>
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
  // ← →
  @Override
  public void start(Stage stage) throws Exception {    // Stage = window
    // Load view and instantiate associated controller class
    Parent root = FXMLLoader.load(getClass().getResource("FilmCRUD.fxml"));
    Scene scene = new Scene(root);  // Scene = content of a window
    stage.setScene(scene);          // Pack content into window
    stage.setTitle("Welcome");
    stage.show();                   // show window
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
