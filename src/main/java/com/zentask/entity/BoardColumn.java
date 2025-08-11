package com.zentask.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "board_columns")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumn extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private String color = "#FFFFFF";
    private boolean isDefault = false;
}
