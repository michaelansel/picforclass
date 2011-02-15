package model.semantics;


/**
 * Combine two colors by dividing their components.
 * 
 * @author Robert C. Duvall
 */
public class Divide extends BinaryOperation
{
    @Override
    protected double evaluateValue (double left, double right)
    {
        if (right == 0) return 0;
        else            return left / right; 
    }
}
