package com.main.exception;

//custom exception class called here
public class EmpException extends Exception{
    public EmpException() {
        super();
    }
    public EmpException(String messsage){
        super(messsage);

    }
}
