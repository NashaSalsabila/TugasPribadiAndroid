package id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Model;


public class Movie {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie(String id, String title, String year, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
    }

    public Movie(String id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    private String id,title,year,image;

}
