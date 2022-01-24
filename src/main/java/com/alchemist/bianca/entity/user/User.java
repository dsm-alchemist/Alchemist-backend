package com.alchemist.bianca.entity.user;

import com.alchemist.bianca.entity.follow.Follow;
import com.alchemist.bianca.entity.image.Image;
import com.alchemist.bianca.entity.storage.Storage;
import com.alchemist.bianca.entity.task.Task;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@DynamicInsert
@Builder
public class User implements Serializable, UserDetails {
    @Id
    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 70, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long timer;

    private Long second;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean is_stop;

    @OneToMany(mappedBy = "following", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "email", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Storage> storages = new ArrayList<>();

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

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
