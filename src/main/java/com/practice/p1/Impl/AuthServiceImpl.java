package com.practice.p1.Impl;

import com.practice.p1.Entity.AuthUserEntity;
import com.practice.p1.Repo.AuthUserMaserRepo;
import com.practice.p1.Services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl  implements AuthService {

    AuthUserMaserRepo userMaserRepo;


    PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthUserMaserRepo userMaserRepo,
                           PasswordEncoder passwordEncoder) {
        this.userMaserRepo = userMaserRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserEntity userMaster = userMaserRepo.findByAuthUserName(username);

        if (userMaster == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        if (userMaster.getRoles().isEmpty()) {
            throw new UsernameNotFoundException("Role not define for User: " + username);
        }
       // setUserContexDTOData(userMaster);

        UserDetails userDetails = User.withUsername(userMaster.getAuthUserName())
                .password(userMaster.getPassword())
                .authorities(userMaster.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName())) // Ensure roles are stored correctly
                        .collect(Collectors.toSet()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!userMaster.isEnabled())
                .build();

        // Print the UserDetails object
        System.out.println("UserDetails: " + userDetails);

        return userDetails;
    }
    @Override
    public AuthUserEntity findByEmail(String email) {
        AuthUserEntity user = userMaserRepo.findByEmail(email);
        return user;
    }
    @Override
    public void setUserdetailsConfiguration(AuthUserEntity userMaser) {
        // TODO Auto-generated method stub

    }

    @Override
    public String changePassword(AuthUserEntity userMaser, String newPass) {
        userMaser.setPassword(passwordEncoder.encode(newPass));
        userMaserRepo.save(userMaser);
        return passwordEncoder.encode(newPass);
    }

    
}
