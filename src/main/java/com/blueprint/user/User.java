package com.blueprint.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class User {
    // no need for id here. yep
    private String nickname;
    private String login;
    private String email;
    private LocalDate birthday;
    private LocalDate created_at;
    private String password; // later change ?

    User(String nickname, String login, String email, String password,
         LocalDate birthday) {
        this.nickname = nickname;
        this.login = login;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    User(String nickname, String login, String email, String password,
         LocalDate birthday, LocalDate created_at) {
        this.nickname = nickname;
        this.login = login;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.created_at = created_at;
    }

    public User(){}
}
