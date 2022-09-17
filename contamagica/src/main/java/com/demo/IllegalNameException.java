package com.demo;

public class IllegalNameException extends RuntimeException{
    public IllegalNameException(){
        super("Nome invalido!");
    }
}
