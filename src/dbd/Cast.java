package dbd;

public class Cast {
	//attributes
	
	//change id type to int
    Integer id;
    
    String characterName;
    
    // foreign key references movies
    Integer movieId;
    
    // foreign key references actors
    Integer actorId;

    // no argument constructor 
    public Cast() {}

    // all argument constructor
    public Cast(Integer id, String characterName,
                Integer movieId, Integer actorId) {
        this.id = id;
        this.characterName = characterName;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    // all setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}
