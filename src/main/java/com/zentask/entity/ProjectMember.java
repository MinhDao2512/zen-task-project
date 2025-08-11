package com.zentask.entity;

import com.zentask.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_members")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ProjectRole role = ProjectRole.MEMBER;

    private LocalDateTime joinedAt = LocalDateTime.now();
    private boolean isActive = true;
}