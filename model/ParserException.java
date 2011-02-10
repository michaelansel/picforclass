package model;


/**
 * Represents an exceptional situation specific to this project.
 * 
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public class ParserException extends RuntimeException
{
    // BUGBUG: should be extendible, i.e., get message text from file 
    public static ParserException BAD_TOKEN =
        new ParserException("unrecognized input");

    public static ParserException BAD_ARGUMENTS =
        new ParserException("not enough arguments");

    public static ParserException BAD_SYNTAX =
        new ParserException("ill-formatted expression");


    /**
     * Create exception with given meesage
     *  
     * @param message explaination of problem
     */
    public ParserException (String message)
    {
        super(message);
    }
}
