package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 45)
    private String task;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User userId;

    private boolean isDone;
}
