package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long task_id;

    @Column(length = 45)
    private String task;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User email;

    private boolean isDone;
}
