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
    public List<Number> evaluate (Map<String, Number> variables)
    {
        if (!variables.containsKey(myName)) throw new RuntimeException(String.format("Variable \"%s\" not found!",
                                                                                     myName));
        return Arrays.asList(new Number[] { variables.get(myName) });
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
