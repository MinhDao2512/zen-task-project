package com.zentask.entity;

import com.zentask.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "username", unique = true),
                @Index(name = "idx_email", columnList = "email", unique = true),
                @Index(name = "idx_points", columnList = "points")
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private boolean emailVerified = false;
    private String otpCode;
    private LocalDateTime otpExpiry;
    private String avatarUrl;

    @Lob
    private String preferencesJson;

    private int points = 0;
    private LocalDateTime lastLogin;
}