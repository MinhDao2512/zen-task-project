package com.zentask.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "projects", indexes = {
        @Index(name = "idx_lead_user_id", columnList = "lead_user_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private String key;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_user_id", nullable = false)
    private User leadUser;

    private String templateType;
    private boolean isPublic = false;

    @Lob
    private String integrationConfigJson;
}

