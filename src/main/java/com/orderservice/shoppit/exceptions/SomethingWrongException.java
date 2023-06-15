package com.orderservice.shoppit.exceptions;

public class SomethingWrongException extends RuntimeException{
    public SomethingWrongException(String msg){
        super(msg);
    }
}
