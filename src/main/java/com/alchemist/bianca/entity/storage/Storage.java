package com.alchemist.bianca.entity.storage;

import com.alchemist.bianca.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Storage {
    @Id
    @GeneratedValue
    private Long storage_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", nullable = false)
    private User email;

    @Column(length = 45, nullable = false)
    private String task;
}
