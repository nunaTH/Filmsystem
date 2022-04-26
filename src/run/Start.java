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
  public void start(Stage stage) throws Exception {    // Stage = Fenster
    // View laden und zugeordnete Controller-Klasse instanziieren
    Parent root = FXMLLoader.load(getClass().getResource("FilmCRUD.fxml"));
    Scene scene = new Scene(root);  // Scene = Inhalt eines Fensters
    stage.setScene(scene);          // Inhalt ins Fenster packen
    stage.setTitle("Welcome");
    stage.show();                   // Fenster anzeigen
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
