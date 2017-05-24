package exceptions;

/**
 * Exception is thrown when disk check fails
 */
public class DiskTypeException extends Exception {

    public DiskTypeException(String message){
        super(message);
    }
}
