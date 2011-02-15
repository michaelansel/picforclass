package model.semantics;


/**
 * Combine two colors by adding their components.
 * 
 * @author Robert C. Duvall
 */
public class Plus extends BinaryOperation
{
    @Override
    protected double evaluateValue (double left, double right)
    {
        return left + right; 
    }
}
