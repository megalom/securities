package ru.megalomaniac.securities.exceptions;

public class SecuritiesNotFoundException extends RuntimeException{

    public SecuritiesNotFoundException() {}

    public SecuritiesNotFoundException(String message)
    {
        super(message);
    }
}
