/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
     * @see picassa.model.expression.Expression#evaluate(java.util.Map)
     */
    @Override
    public Vector<Number> evaluate (Map<String, Number> variables)
    {
        if (!variables.containsKey(myName)) throw new RuntimeException(String.format("Variable \"%s\" not found!",
                                                                                     myName));
        return new Vector<Number>(variables.get(myName));
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
    public String toString()
    {
        return myName;
    }

}
