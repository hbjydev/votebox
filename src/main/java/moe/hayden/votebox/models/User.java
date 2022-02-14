package moe.hayden.votebox.models;

import moe.hayden.votebox.enums.UserRole;

public class User {
    public int id;

    public String username;
    public String password;

    public UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(String username, String password, String role) throws IllegalArgumentException {
        this.username = username;
        this.password = password;
        this.role = UserRole.valueOf(role);
    }
}
