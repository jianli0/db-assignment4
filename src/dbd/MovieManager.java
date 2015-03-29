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

public class MovieManager {
    
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;
    DataSource ds;
    // initial for Connection,PreparedStatement,DataSource,results

    public MovieManager() {
        try {
            Context jndi = new InitialContext();
            ds = (DataSource) jndi.lookup("java:comp/env/jdbc/dbd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void createMovie(Movie newMovie) {
    	String createMovie = "INSERT INTO Movie (id, title, posterImage, releaseDate) VALUES (?, ?, ?, ?)";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(createMovie);
            statement.setObject(1, newMovie.getId());
            statement.setString(2, newMovie.getTitle());
            statement.setString(3, newMovie.getPosterImage());
            statement.setDate(4, new Date(newMovie.getReleaseDate().getTime()));
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

    public List<Movie> readAllMovies() {
    	String readAllMovies = "SELECT * FROM Movie";
        List<Movie> movies = new ArrayList<Movie>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllMovies);
            results = statement.executeQuery();
            while (results.next()) {
                Movie movie = new Movie(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("posterImage"),
                        results.getDate("releaseDate"),
                        movies.add(movie);
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

        return movies;
    }

    public Movie readMovie(Integer movieId) {
    	String readMovie = "SELECT * FROM Movie WHERE id = ?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readMovie);
            statement.setInt(1, movieId);
            results = statement.executeQuery();
            if (results.next()) {
                return new Movie(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("posterImage"),
                        results.getDate("releaseDate")
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
    public void updateMovie(Integer movieId, Movie movie) {
    	String updateMovie = "UPDATE Movie SET id=?, title=?, posterImage=?, releaseDate=? WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateMovie);
            statement.setInt(1, movie.getId());
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getPosterImage());
            statement.setDate(4, new Date(movie.getReleaseDate().getTime()));
            statement.setInt(5, movieId);
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

    public void deleteMovie(Integer movieId) {
    	String deleteMovie = "DELETE FROM Movie WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteMovie);
            statement.setInt(1, movieId);
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
