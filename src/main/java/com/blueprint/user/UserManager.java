package com.blueprint.user;

import com.blueprint.managers.Manager;
import com.blueprint.utils.Utilities;
import lombok.Getter;

@Getter
public class UserManager implements Manager {
    public <T> T create(T data){
        return null;
    }
    public <T> T update(T data){
        return null;
    }
    public <T> void delete (T data){
        return;
    }

    public User signIn(String identifier, String password, Boolean isEmail) {
//       if email -> email; otherwise -> login
        // for bdrypt need byte[] or char[]
        String passwordHash = Utilities.hashingPassword(password);

        return null;
    }


}
