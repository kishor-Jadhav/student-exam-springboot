package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthUserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private int rolePriority;
    @Column(unique = true, nullable = false)
    private String roleName; // e.g., ROLE_ADMIN, ROLE_USER
}
