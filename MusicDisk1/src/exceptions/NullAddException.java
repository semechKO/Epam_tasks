package exceptions;

/**
 * Exception is thrown in addTrack method when null is given as parameter instead of Track object
 */
public class NullAddException extends NullPointerException{
    public NullAddException(String message){
        super(message);
    }
}
