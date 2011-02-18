/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author Michael Ansel
 */
public abstract class UnaryExpression extends Expression
{
    public static UnaryExpression create (String token, Expression expression)
    {
        // TODO Create appropriate UnaryExpression subclass based on token
        return new NotExpression(expression);
    }

    private Expression mySubExpression;


    public UnaryExpression (Expression subExpression)
    {
        mySubExpression = subExpression;
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        return evaluateValueLists(mySubExpression.evaluate(variables));
    }


    protected abstract Number evaluateValue (Number value);


    @Override
    protected List<Number> evaluateValueLists (List<Number> ... valueLists)
    {
        if (valueLists.length != 1) throw new IllegalArgumentException("BinaryExpressions can only evaluate 1 set of values");
        return super.evaluateValueLists(valueLists);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 1) throw new IllegalArgumentException("BinaryExpressions can only evaluate 1 value");
        return evaluateValue(values[0]);
    }


    @Override
    public Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { mySubExpression });
    }


    @Override
    public String toString ()
    {
        return toString(mySubExpression);
    }


    protected String toString (Expression subExpression)
    {
        return String.format("(%s)", subExpression.toString());
    }
}
