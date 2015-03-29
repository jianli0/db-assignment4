package dbd;

import java.util.Date;

public class Comment {
	// attributes
	
	// primary key
    Integer id;
    
    // no argument constructor
    String comment;
    Date date;
    
    // foreign key reference to user
    String username;
    // foreign key reference to movie
    Integer movieId;
    
    // no argument constructor
    public Comment() {}
    
    //all argument constructor 
    public Comment(Integer id, String comment, Date date,
                   String username, Integer movieId) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.username = username;
        this.movieId = movieId;
    }
    
    // all setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMovieId() { return movieId; }

    public void setMovieId(Integer movieId) { this.movieId = movieId; }
}
