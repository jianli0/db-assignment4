package dbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActorManager {
	
	// initial for Connection,PreparedStatement,DataSource,results
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;
    DataSource ds;

    public ActorManager() {
        try {
            Context jndi = new InitialContext();
            ds = (DataSource) jndi.lookup("java:comp/env/jdbc/dbd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void createActor(Actor newActor) {
    	String createActor = "INSERT INTO Actor" +
                "(id, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?)";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(createActor);
            statement.setObject(1, newActor.getId());
            statement.setString(2, newActor.getFirstName());
            statement.setString(3, newActor.getLastName());
            statement.setDate(4, new Date(newActor.getDateOfBirth().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Actor> readAllActors() {
    	String readAllActors = "SELECT * FROM Actor";
        List<Actor> actors = new ArrayList<Actor>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllActors);
            results = statement.executeQuery();
            while (results.next()) {
                Actor actor = new Actor(
                        results.getInt("id"),
                        results.getString("firstName"),
                        results.getString("lastName"),
                        results.getDate("dateOfBirth")
                );
                actors.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return actors;
    }

    public Actor readActor(Integer actorId) {
    	String readActor = "SELECT * FROM Actor WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readActor);
            statement.setInt(1, actorId);
            results = statement.executeQuery();
            if (results.next()) {
                return new Actor(
                        results.getInt("id"),
                        results.getString("firstName"),
                        results.getString("lastName"),
                        results.getDate("dateOfBirth")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void updateActor(Integer actorId, Actor actor) {
    	String updateActor = "UPDATE Actor SET id=?, firstName=?, lastName=?, dateOfBirth=? WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateActor);
            statement.setInt(1, actor.getId());
            statement.setString(2, actor.getFirstName());
            statement.setString(3, actor.getLastName());
            statement.setDate(4, new Date(actor.getDateOfBirth().getTime()));
            statement.setInt(5, actorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteActor(Integer actorId) {
    	String deleteActor = "DELETE FROM Actor WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteActor);
            statement.setInt(1, actorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
