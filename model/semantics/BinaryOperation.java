package model.semantics;

import model.RGBColor;


/**
 * Defines a general method for combining two colors together.
 * 
 * This is primarily a convenience class that provides additional 
 * methods for subclasses to reduce the amount of new code needed
 * to implement an operation.
 * 
 * @author Robert C Duvall
 */
public class BinaryOperation extends Operation
{
    /**
     * Create a default instance.
     * 
     * Provides default constructor so subclasses do not need to define one.
     */
    protected BinaryOperation ()
    {
        super(2);
    }


    /**
     * Given any number of colors, combine them to create a single color.
     *
     * Extracts array arguments so subclasses do not need to.
     */
    @Override
    protected RGBColor evaluateSafely (RGBColor[] colors)
    {
        return evaluateColor(colors[0], colors[1]);
    }


    /**
     * Given two colors, combine them to create a single color.
     * 
     * By default, combines each color component in the same manner.  Override
     * this method to operate directly on the colors, rather than their components. 
     */
    protected RGBColor evaluateColor (RGBColor left, RGBColor right)
    {
        return new RGBColor(evaluateValue(left.getRed(), right.getRed()), 
                            evaluateValue(left.getGreen(), right.getGreen()),
                            evaluateValue(left.getBlue(), right.getBlue()));
    }


    /**
     * Given two values, combine them to create a single value.
     * 
     * There is no reasonable default value to return and this method should not
     * be abstract so throw an exception if neither evaluate method is overridden.
     */
    protected double evaluateValue (double left, double right)
    {
        throw new UnsupportedOperationException();
    }
}
