package run;

import java.util.ArrayList;
import java.util.List;
import run.Film;

public class FilmService {

  private List<Film> filmList = new ArrayList<>();  // Unsere Datenbank
  // Beispielbücherliste für die schnelle Erfassung von Product
  private int nextId = 1;

  public FilmService() { // Wird nur einmal ausgeführt
    initBookList();
  }

  private void initBookList() {
//    filmList.add(getSampleBook1()); // keine ID
    save(getSampleBook1());
    save(getSampleBook2());
    save(getSampleBook3());
  }

  public void save(Film film) {
    film.setFilmId(nextId);
    filmList.add(film);   // List.add(element)
    nextId++;
  }

  public List<Film> getList() {
    return filmList;
  }

  // ============================================================================
  public Film getSampleBook(String buttonText) { // "1", "2" oder "3"
    if (buttonText.equals("1"))
      return getSampleBook1();
    else if (buttonText.equals("2"))
      return getSampleBook2();
    else if (buttonText.equals("3"))
      return getSampleBook3();
    return null;
  }
 //  public Film(int filmId, String filmName, String description, String outYear, String genre, String period, String quality, String provider, String url) {
 
  private Film getSampleBook1() {
    return new Film("Aliens vs. Predator2", "Alien",  "2012", "Sci/Fantasy", "116", "SD","Amaton Video","");
  }

  private Film getSampleBook2() {
    return new Film("The Lego Batman Movie", "Batman",  "2017", "Action", "104", "HD","Amaton Video","");
  }

  private Film getSampleBook3() {
    return new Film("Supergirl", "DC Comics",  "1984", "Sci/Fantasy", "119", "HD","Amaton Video","");
  }
  // ============================================================================

  public void delete(Film film) {
    filmList.remove(film); // film.equals(...)
  }

  public void update(Film film) {
    int index = filmList.indexOf(film); // Film.equals() und die ist id-basiert
    filmList.set(index, film);          // Das Buch-Objekt wird ersetzt
  }
}
