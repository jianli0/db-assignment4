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

public class UserManager {
    
	// initial for Connection,PreparedStatement,DataSource,results
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet results = null;
    DataSource ds;

    public UserManager() {
        try {
            Context jndi = new InitialContext();
            ds = (DataSource) jndi.lookup("java:comp/env/jdbc/dbd");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User newUser) {
    	String createUser = "INSERT INTO User" +
                "(username, password, firstName, lastName, email, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(createUser);
            statement.setString(1, newUser.getUsername());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getFirstName());
            statement.setString(4, newUser.getLastName());
            statement.setString(5, newUser.getEmail());
            statement.setDate(6, new Date(newUser.getDateOfBirth().getTime()));
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

    public List<User> readAllUsers() {
    	String readAllUsers = "SELECT * FROM User";
        List<User> users = new ArrayList<User>();

        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readAllUsers);
            results = statement.executeQuery();
            while (results.next()) {
                User user = new User(
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("firstName"),
                        results.getString("lastName"),
                        results.getString("email"),
                        results.getDate("dateOfBirth")
                );
                users.add(user);
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

        return users;
    }

    public User readUser(String username) {
        String readUser = "SELECT * FROM User WHERE username=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(readUser);
            statement.setString(1, username);
            results = statement.executeQuery();
            if (results.next()) {
                return new User(
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("firstName"),
                        results.getString("lastName"),
                        results.getString("email"),
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
    public void updateUser(String username, User user) {
    	String updateUser = "UPDATE User SET username=?, password=?, firstName=?, lastName=?, email=?, dateOfBirth=? WHERE username=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateUser);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setDate(6, new Date(user.getDateOfBirth().getTime()));
            statement.setString(7, username);
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

    public void deleteUser(String username) {
    	String deleteUser = "DELETE FROM User WHERE username=?";
        try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteUser);
            statement.setString(1, username);
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
