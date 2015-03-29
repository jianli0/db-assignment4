package dbd;

import java.util.Date;

public class Actor {
	// attributes
	
	// change id type to int
    Integer id;
    
    String firstName;
    String lastName;
    Date dateOfBirth;

    // no argument constructor
    public Actor() {}

    // all argument constructor
    public Actor(Integer id, String firstName, String lastName,
                 Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    

    // all setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
