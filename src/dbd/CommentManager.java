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

public class CommentManager {
	
	// initial for Connection,PreparedStatement,DataSource,results
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;
    DataSource ds;

    public CommentManager() {
        try {
            Context jndi = new InitialContext();
            ds = (DataSource) jndi.lookup("java:comp/env/jdbc/dbd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void createComment(Comment newComment) {
    	String createComment = "INSERT INTO Comment (id, comment, date, username, movieId) VALUES(?, ?, ?, ?, ?)";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(createComment);
            statement.setObject(1, newComment.getId());
            statement.setString(2, newComment.getComment());
            statement.setDate(3, new Date(newComment.getDate().getTime()));
            statement.setString(4, newComment.getUsername());
            statement.setInt(5, newComment.getMovieId());
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

    public List<Comment> readAllComments() {
    	String readAllComments = "SELECT * FROM Comment";
        List<Comment> comments = new ArrayList<Comment>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllComments);
            results = statement.executeQuery();
            while (results.next()) {
                Comment comment = new Comment(
                        results.getInt("id"),
                        results.getString("comment"),
                        results.getDate("date"),
                        results.getString("username"),
                        results.getInt("movieId")
                );
                comments.add(comment);
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

        return comments;
    }

    public List readAllCommentsForUsername(String username) {
    	String readAllCommentsForUsername = "SELECT * FROM Comment WHERE username=?";
        List<Comment> comments = new ArrayList<Comment>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllCommentsForUsername);
            statement.setString(1, username);
            results = statement.executeQuery();
            while (results.next()) {
                Comment comment = new Comment(
                        results.getInt("id"),
                        results.getString("comment"),
                        results.getDate("date"),
                        results.getString("username"),
                        results.getInt("movieId")
                );
                comments.add(comment);
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

        return comments;
    }
    public List readAllCommentsForMovie(Integer movieId) {
    	String readAllCommentsForMovie = "SELECT * FROM Comment WHERE movieId=?";
        List<Comment> comments = new ArrayList<Comment>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllCommentsForMovie);
            statement.setInt(1, movieId);
            results = statement.executeQuery();
            while (results.next()) {
                Comment comment = new Comment(
                        results.getInt("id"),
                        results.getString("comment"),
                        results.getDate("date"),
                        results.getString("username"),
                        results.getInt("movieId")
                );
                comments.add(comment);
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

        return comments;
    }

    public Comment readCommentForId(Integer commentId) {
    	String readComment = "SELECT * FROM Comment WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readComment);
            statement.setInt(1, commentId);
            results = statement.executeQuery();
            if (results.next()) {
                return new Comment(
                        results.getInt("id"),
                        results.getString("comment"),
                        results.getDate("date"),
                        results.getString("username"),
                        results.getInt("movieId")
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

    public void updateComment(Integer commentId, Comment newComment) {
    	String updateComment = "UPDATE Comment SET id=?, comment=?, date=?, username=?, movieId=?, WHERE id=?"; 
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateComment);
            statement.setInt(1, newComment.getId());
            statement.setString(2, newComment.getComment());
            statement.setDate(3, new Date(newComment.getDate().getTime()));
            statement.setString(4, newComment.getUsername());
            statement.setInt(5, newComment.getMovieId());
            statement.setInt(6, commentId);
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

    public void deleteComment(Integer commentId) {
    	String deleteComment = "DELETE FROM Comment WHERE id=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteComment);
            statement.setInt(1, commentId);
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
