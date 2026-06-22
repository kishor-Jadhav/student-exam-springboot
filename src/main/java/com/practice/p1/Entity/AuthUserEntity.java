package com.practice.p1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authUsermaster")
@Entity
public class AuthUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String userName;
    String authUserName;
    @Column(length = 60)
    String password;
    String email;
    String language;
    String userAdminKeys;
    String accountName;
    boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_access_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude // Prevents circular reference iss
    @EqualsAndHashCode.Exclude
    Set<AuthUserRoles> roles = new HashSet<>()  ;

}
