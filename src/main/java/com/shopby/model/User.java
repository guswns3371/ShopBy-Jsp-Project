package com.shopby.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "usr_id", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Delivery> deliveryList = new ArrayList<>();

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
}
