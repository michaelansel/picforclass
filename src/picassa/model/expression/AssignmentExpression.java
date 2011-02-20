/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import picassa.util.Vector;


/**
 * @author Michael
 */
public class AssignmentExpression extends Expression
{
    private String myName;
    private Expression myValue;


    public AssignmentExpression (String name, Expression value)
    {
        myName = name;
        myValue = value;
    }


    @Override
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        return myValue.evaluate(variables);
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { myValue });
    }


    public String getName ()
    {
        return myName;
    }


    @Override
    public String toString ()
    {
        return String.format("%s=%s", myName, myValue);
    }

}
