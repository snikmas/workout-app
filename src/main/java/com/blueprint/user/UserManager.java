package com.blueprint.user;

import com.blueprint.managers.Manager;
import com.blueprint.managers.Managers;
import com.blueprint.utils.Utilities;
import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class UserManager {

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

    public User signIn(String identifier, String password, Boolean isEmail) {

        String passwordHash = Utilities.hashingPassword(password);
        String statement = "";
        if(isEmail){
            statement = "SELECT * FROM users WHERE email = ? AND password_hash = ?;";
        } else {
            statement = "SELECT * FROM users WHERE login = ? AND password_hash = ?;";
        }

        ResultSet res;
        try {
            res = managers.getDbManager().signInQuery(statement, identifier, password);
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }





        return null;
    }


}
