package com.demo;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(){
        super("Numero de conta invalido");
    }
}
