/**
 * 
 */
package picassa.model.expression;

/**
 * @author Michael Ansel
 */
public class AddExpression extends BinaryExpression
{

    public static final char TOKEN = '+';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public AddExpression (Expression subExpressionA, Expression subExpressionB)
    {
        super(subExpressionA, subExpressionB);
    }


    /**
     * @see picassa.model.expression.BinaryExpression#mergeValues(java.lang.Number,
     *      java.lang.Number)
     */
    @Override
    protected Number mergeValues (Number valueA, Number valueB)
    {
        return valueA.intValue() + valueB.intValue();
    }


    /**
     * @see picassa.model.expression.BinaryExpression#toString()
     */
    @Override
    public String toString ()
    {
        return toString(TOKEN);
    }

}