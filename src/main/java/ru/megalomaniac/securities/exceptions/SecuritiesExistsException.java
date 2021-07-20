package ru.megalomaniac.securities.exceptions;

public class SecuritiesExistsException extends RuntimeException{

    public SecuritiesExistsException() {}

    public SecuritiesExistsException(String message)
    {
        super(message);
    }
}
