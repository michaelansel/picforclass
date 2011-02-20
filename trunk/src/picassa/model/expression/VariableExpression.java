/**
 * 
 */
package picassa.model.expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public class VariableExpression extends Expression
{
    private String myName;


    public VariableExpression (String name)
    {
        myName = name;
    }


    /**
     * @see picassa.model.expression.Expression#evaluate(Map)
     */
    @Override
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        if (!variables.containsKey(myName)) throw new RuntimeException(String.format("Variable \"%s\" not found!",
                                                                                     myName));
        Map<String, Expression> newVariables =
            new HashMap<String, Expression>(variables);
        // to prevent infinite recursion:
        newVariables.remove(myName);
        return variables.get(myName).evaluate(newVariables);
    }


    /**
     * @see picassa.model.expression.Expression#getExpressions()
     */
    @Override
    protected Collection<Expression> getExpressions ()
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public String toString ()
    {
        return myName;
    }

}
