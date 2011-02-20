/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import picassa.util.Vector;


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
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        return evaluateVectors(mySubExpression.evaluate(variables));
    }


    protected abstract Number evaluateValue (Number value);


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 1) throw new IllegalArgumentException("UnaryExpressions can only evaluate 1 value");
        return evaluateValue(values[0]);
    }


    @Override
    protected Vector<Number> evaluateVectors (Vector<Number> ... vectors)
    {
        if (vectors.length != 1) throw new IllegalArgumentException("UnaryExpressions can only evaluate 1 set of values");
        return super.evaluateVectors(vectors);
    }


    @Override
    protected Collection<Expression> getExpressions ()
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
