package com.practice.p1.ExceptionHandling;

public class InvalidPasswordException extends RuntimeException{
     public InvalidPasswordException(String message){
          super(message);
     }
}
