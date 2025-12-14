package com.blueprint.managers;

import com.blueprint.exceptions.PropertiesError;
import com.blueprint.user.User;
import com.blueprint.utils.Utilities;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbManager {
    Properties properties;
    Managers managers;
    Connection con;
    String url;
    String password;
    String username;


    public DbManager (Managers managers){
        this.managers = managers;

        // loading properties
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")){
            if(input == null){
                throw new RuntimeException("db properties not found in resources!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new PropertiesError("Error during loading properties");
        }

        url = properties.getProperty("db.url");
        password = properties.getProperty("db.password");
        username = properties.getProperty("db.username");
    }

    public User signInQuery(String statement, String identifier, String userPassword) throws SQLException {
        // connect tot he db
        User user;
        con = getConnection();
        PreparedStatement stat = con.prepareStatement(statement);
        stat.setString(1, identifier);
        stat.setString(2, password);

        // never returns null
        ResultSet res = stat.executeQuery();
        user = Utilities.mapUser(res);
        return user;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public boolean userExists(String login, String email){
        try {
            con = getConnection();
            String statement = "SELECT * FROM users WHERE login = ? OR email = ?;";

            PreparedStatement stat = con.prepareStatement(statement);
            stat.setString(1, login);
            stat.setString(2, email);

            ResultSet res = stat.executeQuery();
            return res.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
