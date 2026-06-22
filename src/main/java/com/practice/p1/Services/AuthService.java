package com.practice.p1.Services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.practice.p1.Entity.AuthUserEntity;

public interface AuthService extends UserDetailsService {
    AuthUserEntity findByEmail(String email);
    void setUserdetailsConfiguration(AuthUserEntity userMaser);

    String changePassword(AuthUserEntity userMaser, String newPass);
}
