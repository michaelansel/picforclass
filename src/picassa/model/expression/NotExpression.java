/**
 * 
 */
package picassa.model.expression;

/**
 * @author Michael Ansel
 */
public class NotExpression extends UnaryExpression
{
    public NotExpression (Expression subExpression)
    {
        super(subExpression);
    }


    /**
     * @see picassa.model.expression.UnaryExpression#evaluateValue(java.lang.Number)
     */
    @Override
    protected Number evaluateValue (Number value)
    {
        return value.doubleValue() * -1;
    }


    @Override
    public String toString ()
    {
        return "!" + super.toString();
    }
}
