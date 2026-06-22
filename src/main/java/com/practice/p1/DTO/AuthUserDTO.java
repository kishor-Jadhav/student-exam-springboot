package com.practice.p1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUserDTO {
     Long userId;
     String userName;
     String authUserName;
     @JsonProperty("password")
     String password;
     @JsonProperty("email")
     String email;

     String userRole;
     boolean isActive;
     boolean isValidateUser;
     String authToken;
     boolean generateNewPassword;
     String userAdminKeys;
     String accountName;
     String language;
}
