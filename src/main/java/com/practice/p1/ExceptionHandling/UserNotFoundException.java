package com.practice.p1.ExceptionHandling;

public class UserNotFoundException extends RuntimeException{
     public UserNotFoundException(String message){
          super(message);
     }
}
