package com.zentask.entity;

import com.zentask.enums.SprintStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sprints", indexes = {
        @Index(name = "idx_project_id", columnList = "project_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sprint extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "sprint_tasks",
            joinColumns = @JoinColumn(name = "sprint_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks = new ArrayList<>();

    private int capacity = 0;
    @Enumerated(EnumType.STRING)
    private SprintStatus status = SprintStatus.PLANNED;
}