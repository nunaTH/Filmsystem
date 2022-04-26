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

public class FilmController { // In this app, an instance of FilmController
  // =================== Form =====================
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
  // When loading the fxml file, the FilmController is created:
  // 1. Constructor
  // 2. Dependency Injection: @FXML
  // 3. Initialization
  // ============================================================
  public FilmController() {
    System.out.println("FilmController() - Constructor");
  }

  /**
   * Standard mechanism for initializations
   */
  public void initialize() {
    System.out.println("MainController.initialize() ...");
    filmService = new FilmService();
    updateGUIFromList();
    clearStatusText();
  }

  private void clearStatusText() {
    setStatusInfo("");
  }

  private void updateGUIFromList() {
    List<Film> filmList = filmService.getList();
    viewList_listview.getItems().setAll(filmList);
  }
// ============================================================================
  //               MVC: Modell, View, Controller
  // ============================================================================
  // 1. Controller: Evaluate user input, convert, validate and form matching objects
  // 2. Model call
  // 3. View call (To update)

  // ===============================================
  // =            Event Handler                    =
  // ===============================================
  @FXML
  void onCreate(ActionEvent event) {
    System.out.println("MainController.onCreate()");
    Film film = getFromGUI();
    if (film.getFilmName().trim().isEmpty()) {
      setStatusError("A insert is required!");
    } else {
    filmService.save(film);
      setStatusInfo("Film [" + film.getFilmName() + "] was created.");
      updateGUIFromList();
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
    Button button = (Button) event.getSource();
    String buttonText = button.getText();
    Film film = filmService.getSample(buttonText);
    setFromGUI(film);
  }

  @FXML
  void onUpdate(ActionEvent event) {
   Film selected = getSelected();
    if (selected != null) {
      Film updatedBook = getFromGUI();
      updatedBook.setFilmId(selected.getFilmId());
      filmService.update(updatedBook);
      setStatusInfo("Film [" + selected.getFilmName() + "] was updated.");
      updateGUIFromList();
    } else {
      setStatusError("Please select a film!");
    }
  }

  @FXML
  void onDelete(ActionEvent event) {
    Film selected = getSelected();
        if (selected != null) {
      if (getConfirmation("The film is irrevocably removed from the list:", "Do you agree?")) {
        filmService.delete(selected);
        setStatusInfo("Film [" + selected.getFilmName() + "] was deleted.");
        updateGUIFromList();
      }
    } else {
      setStatusError("Please select a film!");
    }
  }

  private boolean getConfirmation(String headerText, String contentText) {
    Alert alert = new Alert(CONFIRMATION);
    alert.setTitle("confirmation");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    Optional<ButtonType> userAction = alert.showAndWait();
    return userAction.get() == OK;
  }

  @FXML
  void onReset(ActionEvent event) {
    Film film = new Film("", "", "", "", "", "", "", "");
    setFromGUI(film);
    viewList_listview.getSelectionModel().clearSelection();
  }

  @FXML
  void onSelectionChanged(MouseEvent event) {
    System.out.println("MainController.onSelectionChanged() ...");
    Film selected = getSelected(); // ALT + SHIFT + M => Extract Method
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
    String filmName = filmName_tf.getText();
    String description = description_tf.getText();
    String releaseYear = releaseYear_tf.getText();
    String genre = genre_tf.getText();
    String runTime = runTime_tf.getText();
    String quality = quality_tf.getText();
    String provider = provider_tf.getText();
    String url = url_tf.getText();

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
