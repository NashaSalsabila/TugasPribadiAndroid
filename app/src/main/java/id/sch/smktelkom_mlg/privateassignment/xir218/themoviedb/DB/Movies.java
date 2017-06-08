package id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.DB;

import com.orm.SugarRecord;

/**
 * Created by nerdywoffy on 5/14/17.
 */

public class Movies extends SugarRecord {

    public Movies(){

    }
    public Movies(String movieId, String image, String title, String originalTitle, String overview, String status, String creator, String releaseDate, String runtime, String voteAverage, String year) {
        this.movieId = movieId;
        this.image = image;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.status = status;
        this.creator = creator;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.year = year;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    String movieId;
    String image;
    String title;
    String originalTitle;
    String overview;
    String status;
    String creator;
    String releaseDate;
    String runtime;
    String voteAverage;
    String year;

}
