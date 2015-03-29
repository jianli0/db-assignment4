package dbd;

import java.util.Date;

public class Movie {
	
	//attributes
	
	//change id type to int 
    Integer id;
    String title;
    String posterImage;
    Date releaseDate;
    
    // no argument constructor
    public Movie() {}
    
    //all argument constructor 
    public Movie(Integer id, String title,
                 String posterImage, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.posterImage = posterImage;
        this.releaseDate = releaseDate;
    }
    
    //all argument constructor 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
