package com.zentask.entity;

import com.zentask.enums.Priority;
import com.zentask.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks", indexes = {
        @Index(name = "idx_project_id", columnList = "project_id"),
        @Index(name = "idx_assignee_id", columnList = "assignee_id"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_due_date", columnList = "dueDate")  // For filters
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TO_DO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> subtasks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "task_labels", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "task_dependencies", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "dependent_task_id"))
    private List<Task> dependencies = new ArrayList<>();

    private int storyPoints = 0;
    private String aiPriorityScore;
    private LocalDateTime completedAt;
    private String blockchainHash;
    private boolean isOfflineSynced = false;
}