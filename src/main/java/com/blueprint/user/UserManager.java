package com.blueprint.user;

import com.blueprint.exceptions.NullResultSet;
import com.blueprint.managers.Manager;
import com.blueprint.managers.Managers;
import com.blueprint.utils.Utilities;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Getter
public class UserManager {
    Logger log = LoggerFactory.getLogger(UserManager.class.getName());

    Managers managers;
    public UserManager(Managers managers){
        this.managers = managers;
    }

    public User create(User data){return null;}
    public User update(User data){
        return null;
    }
    public void delete (User data){
        return;
    }

    public User signIn(String identifier, String password, Boolean isEmail) throws SQLException {

        String passwordHash = Utilities.hashingPassword(password);
        String statement = "";
        if(isEmail){
            statement = "SELECT * FROM users WHERE email = ? AND password_hash = ?;";
        } else {
            statement = "SELECT * FROM users WHERE login = ? AND password_hash = ?;";
        }
        return managers.getDbManager().signInQuery(statement, identifier, password);
    }

    public User signUp(String nickname, String login, String email, String password, LocalDate birthday){
        String passwordHash = Utilities.hashingPassword(password);
        // INSERT INTO A (A, B) VALUES (?, ?);
        // but firsly, check if such a user exist
        if(managers.getDbManager().userExists(login, email)){
            System.out.println("The user already exists!");
            return null;
        }

        User user = new User(nickname, login, email, passwordHash, birthday, LocalDate.now());
        if(managers.getDbManager().createUser(user)){
            return user; // success;
        }
        return null;
    }


}
