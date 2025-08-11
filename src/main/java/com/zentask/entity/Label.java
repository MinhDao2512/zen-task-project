package com.zentask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "labels")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Label extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private String color;

    private boolean isGlobal = false;
}
