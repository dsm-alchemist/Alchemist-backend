package com.alchemist.bianca.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String userName;

    private LocalTime timer;

    @OneToMany(mappedBy = "following", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> follower = new ArrayList<>();

}
