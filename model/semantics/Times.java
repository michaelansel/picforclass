package model.semantics;


/**
 * Combine two colors by multiplying their components.
 * 
 * @author Robert C. Duvall
 */
public class Times extends BinaryOperation
{
    @Override
    protected double evaluateValue (double left, double right)
    {
        return left * right; 
    }
}
