package com.practice.p1.Repo;

import com.practice.p1.Entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserMaserRepo extends JpaRepository<AuthUserEntity,Long> {
    AuthUserEntity findByEmail(String email);
    AuthUserEntity findByUserId(Long Id);
    AuthUserEntity findByAuthUserName(String username);
}
