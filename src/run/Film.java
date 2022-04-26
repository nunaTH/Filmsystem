package run;

/**
 *
 * @author Surattana Bopp <info@surattana.de>
 */
import run.*;

public class Film {

  // ############### Attributes ###############
  private int filmId;
  private String filmName;
  private String description;
  private String releaseYear;
  private String genre;
  private String runTime;
  private String quality;
  private String provider;
  private String url;

  // ############### Constructors ###############
//  public Film_1() {
//  }

  public Film(int filmId, String filmName, String description, String releaseYear, String genre, String runTime, String quality, String provider, String url) {
    this.filmId = filmId;
    this.filmName = filmName;
    this.description = description;
    this.releaseYear = releaseYear;
    this.genre = genre;
    this.runTime = runTime;
    this.quality = quality;
    this.provider = provider;
    this.url = url;
  }

  public Film(String filmName, String description, String releaseYear, String genre, String runTime, String quality, String provider, String url) {
    this.filmName = filmName;
    this.description = description;
    this.releaseYear = releaseYear;
    this.genre = genre;
    this.runTime = runTime;
    this.quality = quality;
    this.provider = provider;
    this.url = url; }

  // ############### Getter and Setter ###############
  public int getFilmId() {
    return filmId;
  }

  public void setFilmId(int filmId) {
    this.filmId = filmId;
  }

  public String getFilmName() {
    return filmName;
  }

  public void setFilmName(String filmName) {
    this.filmName = filmName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getReleaseYear() {
    return releaseYear;
  }

  public void setOutYear(String releaseYear) {
    this.releaseYear = releaseYear;
  }

  public String getRunTime() {
    return runTime;
  }

  public void setRunTime(String runTime) {
    this.runTime = runTime;
  }

  public String getQuality() {
    return quality;
  }

  public void setQuality(String quality) {
    this.quality = quality;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // ############### equals(), hashCode() and toString() ###############
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Film other = (Film) obj;
    return this.filmId == other.filmId; // equals() basiert auf Gleichheit der ids
  }

  @Override
  public int hashCode() {
    return filmId;
  }
 
  @Override
  public String toString() {
    return String.format("(%d) : %s - %s - %s - %s - %s - %s  ", filmId,filmName, description, releaseYear,genre,runTime,quality,provider,url);
  }
}
