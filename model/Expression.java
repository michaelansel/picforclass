package model;

import model.util.ColorCombinations;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public class Expression
{
    private RGBColor myValue;
    private String myOp;
    private Expression myLeft;
    private Expression myRight;


    /**
     * Create expression representing the given constant value
     */
    public Expression (RGBColor value)
    {
        myValue = value;
        myOp = null;
        myLeft = null;
        myRight = null;
    }


    /**
     * Create expression representing the given operation between the
     * two given sub-expressions.
     */
    public Expression (String op, Expression left, Expression right)
    {
        myOp = op;
        myLeft = left;
        myRight = right;
        myValue = new RGBColor();
    }


    /**
     * @return value of expression
     */
    public RGBColor evaluate ()
    {
        if (myOp == null)
        {
            return myValue;
        }
        else
        {
            if (myOp.equals("+"))
                return ColorCombinations.add(myLeft.evaluate(), myRight.evaluate());
            else if (myOp.equals("-"))
                return ColorCombinations.subtract(myLeft.evaluate(), myRight.evaluate());
            else if (myOp.equals("*"))
                return ColorCombinations.multiply(myLeft.evaluate(), myRight.evaluate());
            else if (myOp.equals("/"))
                return ColorCombinations.divide(myLeft.evaluate(), myRight.evaluate());
            else
                throw ParserException.BAD_TOKEN;
        }
    }


    /**
     * @return string representation of expression
     */
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        if (myOp == null)
        {
            result.append(myValue); 
        }
        else
        {
            result.append("(");
            result.append(myLeft.toString()); 
            result.append(" " + myOp + " "); 
            result.append(myRight.toString());
            result.append(")");
        }
        return result.toString();
    }
}
