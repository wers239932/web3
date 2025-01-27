package Exceptions;

public class IncorrectJwtException extends Exception{
    public IncorrectJwtException(String errorMessage){
        super(errorMessage);
    }
}
