package com.alchemist.bianca.entity.task;

import com.alchemist.bianca.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long task_id;

    @Column(length = 45, nullable = false)
    private String task;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", nullable = false)
    private User email;

    @ColumnDefault(value = "false")
    private boolean isDone;

    @Builder
    public Task(String task, LocalDate date, User email) {
        this.task = task;
        this.date = date;
        this.email = email;
    }
}
