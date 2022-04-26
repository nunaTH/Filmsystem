package run;

/**
 *
 * @author Surattana Bopp <info@surattana.de>
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.*;

public class FilmController { // In dieser App eine Instanz vom FilmController

  // =================== Formular =====================
  @FXML
  private TextField filmId_tf;

  @FXML
  private TextField filmName_tf;

  @FXML
  private TextField description_tf;

  @FXML
  private TextField releaseYear_tf;

  @FXML
  private TextField genre_tf;

  @FXML
  private TextField runTime_tf;

  @FXML
  private TextField quality_tf;

  @FXML
  private TextField provider_tf;

  @FXML
  private TextField url_tf;
  // ===========================================================

  @FXML
  private ListView<Film> viewList_listview;

  @FXML
  public Label status_label;

  private FilmService filmService;

  // ============================================================
  // Beim laden der fxml-Datei, wird der FilmController erstellt:
  // 1. Constructor
  // 2. Dependency Injection: @FXML
  // 3. Initialization
  // ============================================================
  public FilmController() {
    System.out.println("FilmController() - Constructor");
  }

  /**
   * Standard-Mechanismus für Initialisierungen
   */
  public void initialize() {
    System.out.println("MainController.initialize() ...");
    filmService = new FilmService();
    updateGUIFromBookList();
    clearStatusText();
  }

  private void clearStatusText() {
    setStatusInfo("");
  }

  private void updateGUIFromBookList() {
    List<Film> filmList = filmService.getList();
    viewList_listview.getItems().setAll(filmList);
  }
// ============================================================================
  //               MVC: Modell, View, Controller
  // ============================================================================
  // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
  // 2. Model aufrufen
  // 3. View aufrufen (aktualisieren)

  // ===============================================
  // =            Event Handler                    =
  // ===============================================
  @FXML
  void onCreate(ActionEvent event) {
    System.out.println("MainController.onCreate()");
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
    Film film = getFromGUI();
    if (film.getFilmName().trim().isEmpty()) {
      setStatusError("A insert is required!");
    } else {
      // 2. Model aufrufen
      //    FilmService filmService= new FilmService(); // Programmierfehler, jedes mal eine neue Liste
      filmService.save(film);
      // 3. View aufrufen (aktualisieren)
      setStatusInfo("Film [" + film.getFilmName() + "] was created.");
      updateGUIFromBookList(); // BookList von FilmService abholen und in die GUI setzen
    }
  }

  private void setStatusInfo(String text) {
    status_label.setTextFill(Color.GREEN);
    status_label.setText(text);
  }

  private void setStatusError(String text) {
    status_label.setTextFill(Color.RED);
    status_label.setText(text);
  }

  @FXML
  void fillData(ActionEvent event) {
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
    // Welche Schaltfläche wurde geklickt? 1, 2 oder 3
    Button button = (Button) event.getSource(); // Die Schaltfläche, die geklickt wurde
    String buttonText = button.getText();       // Beschriftung der Schaltfläche
    // 2. Model aufrufen
    Film film = filmService.getSampleBook(buttonText);
    // 3. View aufrufen (aktualisieren)
    setFromGUI(film);
  }

  @FXML
  void onUpdate(ActionEvent event) {
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
    Film selected = getSelected();
    if (selected != null) { // Ein Film ist ausgewählt
      // 2. Model aufrufen
      Film updatedBook = getFromGUI();     // Alle Daten außer id
      updatedBook.setFilmId(selected.getFilmId()); // Damit hat updatedBook die aktuelle id
      filmService.update(updatedBook);
      // 3. View aufrufen (aktualisieren)
      setStatusInfo("Film [" + selected.getFilmName() + "] was updated.");
      updateGUIFromBookList();
    } else {
      setStatusError("Please select a film!");
    }
  }

  @FXML
  void onDelete(ActionEvent event) {
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
    Film selected = getSelected();
    // 2. Model aufrufen
    if (selected != null) { // Ein Film ausgewählt
      if (getConfirmation("The film is irrevocably removed from the list:", "Do you agree?")) {
        filmService.delete(selected);
        // 3. View aufrufen (aktualisieren)
        setStatusInfo("Film [" + selected.getFilmName() + "] was deleted.");
        updateGUIFromBookList();
      }
    } else { // Kein Film ausgewählt
      setStatusError("Please select a film!");
    }
  }

  private boolean getConfirmation(String headerText, String contentText) {
    Alert alert = new Alert(CONFIRMATION); // OK und Abbrechen
    alert.setTitle("confirmation");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    Optional<ButtonType> userAction = alert.showAndWait();
    return userAction.get() == OK; // Benutzer hat "OK" ausgewählt (bestätigt)
  }

  @FXML
  void onReset(ActionEvent event) {
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden

    //  public Film(int filmId, String filmName, String description, String releaseYear, String genre, String period, String quality, String provider, String url) {
    Film film = new Film(null, "", "", "", "", "", "", "");
    // 2. Model aufrufen
    // 3. View aufrufen (aktualisieren)
    setFromGUI(film);
    viewList_listview.getSelectionModel().clearSelection();
  }

  @FXML
  void onSelectionChanged(MouseEvent event) {
    System.out.println("MainController.onSelectionChanged() ...");
    // Ausgewähltes Film abfragen und in Formular setzen
    // 1. Controller-Aufgaben: Benutzereingabe auswerten, konvertieren, validieren und passende Objekte bilden
    Film selected = getSelected(); // ALT + SHIFT + M => Extract Method
    // 2. Model aufrufen
    // 3. View aufrufen (aktualisieren)
    if (selected != null) {
      setFromGUI(selected);
    } else {
      onReset(null);
    }
  }

  private Film getSelected() {
    return viewList_listview.getSelectionModel().getSelectedItem();
  }

  // ===============================================
  // ============= Helper Methods =================
  // ===============================================
  private Film getFromGUI() {
    //  int filmId = filmId_tf.getText();
 
    String filmName = filmName_tf.getText();
    String description = description_tf.getText();
    String releaseYear = releaseYear_tf.getText();
    String genre = genre_tf.getText();
    String runTime = runTime_tf.getText();
    String quality = quality_tf.getText();
    String provider = provider_tf.getText();
    String url = url_tf.getText();

    //  public Film(int filmId, String filmName, String description, String releaseYear, String genre, String period, String quality, String provider, String url) {
    Film film = new Film(filmName, description, releaseYear, genre, runTime, quality, provider, url);
    return film;
  }

  private void setFromGUI(Film film) {
    filmName_tf.setText(film.getFilmName());
    description_tf.setText(film.getDescription());
    releaseYear_tf.setText(film.getReleaseYear());
    genre_tf.setText(film.getGenre());
    runTime_tf.setText(film.getRunTime());
    quality_tf.setText(film.getQuality());
    provider_tf.setText(film.getProvider());
    url_tf.setText(film.getUrl());
  }
}
