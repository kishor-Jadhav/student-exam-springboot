package com.practice.p1.ExceptionHandling;

public class JWTTokenException extends RuntimeException{
     public JWTTokenException(String message){
          super(message);
     }
}
