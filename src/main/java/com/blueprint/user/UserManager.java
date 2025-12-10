package com.blueprint.user;

import com.blueprint.managers.Manager;
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
}
