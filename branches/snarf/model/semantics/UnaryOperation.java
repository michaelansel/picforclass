package model.semantics;

import model.RGBColor;


/**
 * Defines a general method for transforming a single color.
 * 
 * This is primarily a convenience class that provides additional 
 * methods for subclasses to reduce the amount of new code needed
 * to implement an operation.
 * 
 * @author Robert C Duvall
 */
public class UnaryOperation extends Operation
{
    /**
     * Create a default instance.
     * 
     * Provides default constructor so subclasses do not need to define one.
     */
    protected UnaryOperation ()
    {
        super(1);
    }


    /**
     * Given any number of colors, transform the first into a new color.
     *
     * Extracts array arguments so subclasses do not need to.
     */
    @Override
    protected RGBColor evaluateSafely (RGBColor[] colors)
    {
        return evaluateColor(colors[0]);
    }


    /**
     * Given a color, transform it into a new color.
     * 
     * By default, transforms each color component in the same manner.  Override
     * this method to operate directly on the color, rather than its components. 
     */
    protected RGBColor evaluateColor (RGBColor c)
    {
        return new RGBColor(evaluateValue(c.getRed()), 
                            evaluateValue(c.getGreen()),
                            evaluateValue(c.getBlue()));
    }


    /**
     * Given a value, transform it into a new value.
     * 
     * There is no reasonable default value to return and this method should not
     * be abstract so throw an exception if neither evaluate method is overridden.
     */
    protected double evaluateValue (double value)
    {
        throw new UnsupportedOperationException();
    }
}
