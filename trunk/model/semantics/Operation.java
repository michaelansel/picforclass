package model.semantics;

import model.ParserException;
import model.RGBColor;


/**
 * Defines a general method for combining colors together.
 * 
 * @author Robert C. Duvall
 */
public abstract class Operation
{
    private int myNumArguments;


    /**
     * Create an operation that expects only the given number of arguments.
     */
    protected Operation (int numArguments)
    {
        myNumArguments = numArguments;
    }


    /**
     * Given any number of colors, combine them to create a single color.
     */
    public RGBColor evaluate (RGBColor ... colors)
    {
        if (colors.length >= myNumArguments)
        {
            return evaluateSafely(colors);
        }
        else
        {
            throw ParserException.BAD_ARGUMENTS;
        }
    }


    /**
     * Provide subclasses a safe version of evaluate.
     * 
     * This version is guaranteed to have the correct number of arguments. 
     */
    protected abstract RGBColor evaluateSafely (RGBColor[] colors);
}
