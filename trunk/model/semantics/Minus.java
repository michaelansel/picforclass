package model.semantics;


/**
 * Combine two colors by subtracting their components.
 * 
 * @author Robert C. Duvall
 */
public class Minus extends BinaryOperation
{
    @Override
    protected double evaluateValue (double left, double right)
    {
        return left - right; 
    }
}
