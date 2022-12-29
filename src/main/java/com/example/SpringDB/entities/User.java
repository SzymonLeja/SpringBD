package com.example.SpringDB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    private String type;
    private String name;
    private String user_name;
    private String password;

    public void updateUser(User user) {
        this.type = user.getType() == null ? this.type : user.getType();
        this.name = user.getName() == null ? this.name : user.getName();
        this.user_name = user.getUser_name() == null ? this.user_name : user.getUser_name();
        this.password = user.getPassword() == null ? this.password : user.getPassword();
    }
}
