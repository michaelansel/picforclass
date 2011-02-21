/**
 * 
 */
package picassa.model.expression;

/**
 * @author Michael Ansel
 */
public class DivideExpression extends BinaryExpression
{

    public static final char TOKEN = '/';
    public static final String TOKEN_REGEX = String.format("[%s]", TOKEN);


    public DivideExpression (Expression subExpressionA, Expression subExpressionB)
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
        return valueA.doubleValue() / valueB.doubleValue();
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
