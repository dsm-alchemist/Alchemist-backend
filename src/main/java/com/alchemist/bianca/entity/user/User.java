package com.alchemist.bianca.entity.user;

import com.alchemist.bianca.entity.follow.Follow;
import com.alchemist.bianca.entity.storage.Storage;
import com.alchemist.bianca.entity.task.Task;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class User implements Serializable, UserDetails {
    @Id
    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 70, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    private LocalTime timer;

    @OneToMany(mappedBy = "following", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Storage> storages = new ArrayList<>();

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
