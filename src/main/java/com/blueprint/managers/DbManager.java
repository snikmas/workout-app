package com.blueprint.managers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.blueprint.exceptions.PropertiesError;
import com.blueprint.user.User;
import com.blueprint.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DbManager {

    Logger log = LoggerFactory.getLogger(DbManager.class.getName());

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
        log.info("singInQuery runs...");
        User user;
        con = getConnection();
        PreparedStatement stat = con.prepareStatement(statement);
        stat.setString(1, identifier);
        log.info("singInQuery (dbManager): stat: " + stat);

        // never returns null
        ResultSet res = stat.executeQuery();
        log.info("singInQuery (dbManager): res: " + res);
        user = Utilities.mapUser(res);

        if(user == null) return null;
        BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), userPassword);
        if(result.verified){
            log.info("usr verified!");
            return user;
        }
        log.info("usr is not verified! problem with a password");
        return null;
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

    public boolean createUser(User user){
        try {
            con = getConnection();
            String statement = "INSERT INTO users (nickname, login, email, birthday, password_hash, created_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stat = con
                    .prepareStatement(statement);
            stat.setString(1, user.getNickname());
            stat.setString(2, user.getLogin());
            stat.setString(3, user.getEmail());
            stat.setDate(4, Date.valueOf(user.getBirthday()));
            stat.setString(5, user.getPassword());
            stat.setDate(6, Date.valueOf(user.getCreated_at()));

            int res = stat.executeUpdate();
            return res == 1; // success
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
