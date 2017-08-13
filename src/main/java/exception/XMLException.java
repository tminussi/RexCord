package exception;

/**
 * Thrown when there's an error reading a RexCord xml file
 */
public class XMLException extends RuntimeException {

    /**
     * Constructor with a given message
     * @param message given message
     */
    public XMLException(String message) {
        super(message);
    }

    /**
     * Default empty constructor
     */
    public XMLException() {
        super();
    }
}
