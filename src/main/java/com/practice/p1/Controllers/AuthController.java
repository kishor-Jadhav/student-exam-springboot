package com.practice.p1.Controllers;

import com.practice.p1.DTO.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.p1.Configurations.Security.JwtHelper;
import com.practice.p1.DTO.AuthUserDTO;
import com.practice.p1.Entity.AuthUserEntity;
import com.practice.p1.ExceptionHandling.InvalidPasswordException;
import com.practice.p1.ExceptionHandling.UserNotFoundException;
import com.practice.p1.Services.AuthService;

 
@ResponseBody
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper helper;
    @Autowired
    AuthService authService;

     public AuthController(PasswordEncoder passwordEncoder){
             this.passwordEncoder = passwordEncoder;
     }
    @PostMapping("changepass")
    public String ChangePassword(@RequestBody LoginUserDTO userMaserModel) {
        AuthUserEntity user = authService.findByEmail(userMaserModel.getEmail());
        String passKey =authService.changePassword(user,userMaserModel.getPassword());
        String str ="Pass- "+ userMaserModel.getPassword() +" Auth key-" +passKey;
        return str;
    }
    @PostMapping("loginuser") //admin123
    public AuthUserDTO LoginUser(@RequestBody LoginUserDTO userMaserModel) {
        AuthUserEntity user = authService.findByEmail(userMaserModel.getEmail());
        AuthUserDTO authDTO = new AuthUserDTO();
        boolean isValidateUser= false;
        if (user != null) {
            boolean isPassMatch = passwordEncoder.matches(userMaserModel.getPassword(),user.getPassword());
            if(isPassMatch){
                isValidateUser = true;


                String token = this.helper.generateToken(user);
                authDTO.setValidateUser(isValidateUser);
                authDTO.setAuthToken(token);
                authService.setUserdetailsConfiguration(user);
                return authDTO;
            } else {
                throw new InvalidPasswordException("Password does not match");
            }

        } else {
            throw new UserNotFoundException("Email ID not found");
        }

    }

    

}
