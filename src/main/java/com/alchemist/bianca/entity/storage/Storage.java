package com.alchemist.bianca.entity.storage;

import com.alchemist.bianca.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue
    private Long storage_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User email;

    @Column(length = 45)
    private String task;
}
