package dbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CastManager {
	
	// initial for Connection,PreparedStatement,DataSource,results
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;
    DataSource ds;

    public CastManager() {
        try {
            Context jndi = new InitialContext();
            ds = (DataSource) jndi.lookup("java:comp/env/jdbc/dbd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void createCast(Cast newCast) {
    	String createCast = "INSERT INTO `Cast` (id, characterName, movieId, actorId) VALUES (?,?,?,?)";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(createCast);
            statement.setObject(1, newCast.getId());
            statement.setString(2, newCast.getCharacterName());
            statement.setInt(3, newCast.getMovieId());
            statement.setInt(4, newCast.getActorId());
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

    public List readAllCast() {
    	String readAllCast = "SELECT * FROM Cast";
        List<Cast> casts = new ArrayList<Cast>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllCast);
            results = statement.executeQuery();
            while (results.next()) {
                Cast cast = new Cast(
                        results.getInt("id"),
                        results.getString("characterName"),
                        results.getInt("movieId"),
                        results.getInt("actorId")
                );
                casts.add(cast);
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

        return casts;
    }

    public List readAllCastForActor(Integer actorId) {
    	String readAllCastForActor = "SELECT * FROM Cast WHERE actorId=?";
        List<Cast> casts = new ArrayList<Cast>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllCastForActor);
            statement.setInt(1, actorId);
            results = statement.executeQuery();
            while (results.next()) {
                Cast cast = new Cast(
                        results.getInt("id"),
                        results.getString("characterName"),
                        results.getInt("movieId"),
                        results.getInt("actorId")
                );
                casts.add(cast);
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

        return casts;
    }

    public List readAllCastForMovie(Integer movieId) {
    	String readAllCastForMovie = "SELECT * FROM Cast WHERE movieId=?";
        List<Cast> casts = new ArrayList<Cast>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllCastForMovie);
            statement.setInt(1, movieId);
            results = statement.executeQuery();
            while (results.next()) {
                Cast cast = new Cast(
                        results.getInt("id"),
                        results.getString("characterName"),
                        results.getInt("movieId"),
                        results.getInt("actorId")
                );
                casts.add(cast);
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

        return casts;
    }

    public Cast readCastForId(Integer castId) {
    	String readCastForId = "SELECT * FROM Cast WHERE Id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readCastForId);
            statement.setInt(1, castId);
            results = statement.executeQuery();
            if (results.next()) {
                return new Cast(
                        results.getInt("id"),
                        results.getString("characterName"),
                        results.getInt("movieId"),
                        results.getInt("actorId")
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

    public void updateCast(Integer castId, Cast newCast) {
    	String updateCast = "UPDATE Cast SET id=?, characterName=?, actorId=?, movieId=? WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateCast);
            statement.setInt(1, newCast.getId());
            statement.setString(2, newCast.getCharacterName());
            statement.setInt(3, newCast.getActorId());
            statement.setInt(4, newCast.getMovieId());
            statement.setInt(5, castId);
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

    public void deleteCast(Integer castId) {
    	String deleteCast = "DELETE FROM Cast WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteCast);
            statement.setInt(1, castId);
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
