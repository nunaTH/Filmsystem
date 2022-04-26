package run;

import java.util.ArrayList;
import java.util.List;
import run.Film;

public class FilmService {

  private List<Film> filmList = new ArrayList<>();  // Database
  private int nextId = 1;

  public FilmService() { // Wird nur einmal ausgeführt
    initList();
  }

  private void initList() {
//    filmList.add(getSample1()); // no ID
    save(getSample1());
    save(getSample2());
    save(getSample3());
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
  public Film getSample(String buttonText) { // "1", "2" oder "3"
    if (buttonText.equals("1"))
      return getSample1();
    else if (buttonText.equals("2"))
      return getSample2();
    else if (buttonText.equals("3"))
      return getSample3();
    return null;
  }

  private Film getSample1() {
    return new Film("Aliens vs. Predator2", "Alien",  "2012", "Sci/Fantasy", "116", "SD","Amaton Video","");
  }

  private Film getSample2() {
    return new Film("The Lego Batman Movie", "Batman",  "2017", "Action", "104", "HD","Amaton Video","");
  }

  private Film getSample3() {
    return new Film("Supergirl", "DC Comics",  "1984", "Sci/Fantasy", "119", "HD","Amaton Video","");
  }
  // ============================================================================

  public void delete(Film film) {
    filmList.remove(film); // film.equals(...)
  }

  public void update(Film film) {
    int index = filmList.indexOf(film); // Film.equals() and it's id-based
    filmList.set(index, film);          // The film object is replaced
  }
}
