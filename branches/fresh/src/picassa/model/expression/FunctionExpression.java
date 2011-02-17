/**
 * 
 */
package picassa.model.expression;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import picassa.model.parser.SimpleLexer.TokenMatch;

/**
 * @author Michael
 *
 */
public class FunctionExpression extends Expression
{

    private String myFunctionName;
    private List<Expression> myParameters;


    public FunctionExpression (String functionName, List<Expression> parameters)
    {
        myFunctionName = functionName;
        myParameters = parameters;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s(%s)", myFunctionName, myParameters.toString());
    }


    /* (non-Javadoc)
     * @see picassa.model.expression.Expression#evaluate(java.util.Map)
     */
    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see picassa.model.expression.Expression#evaluateValues(java.lang.Number[])
     */
    @Override
    protected Number evaluateValues (Number ... values)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see picassa.model.expression.Expression#getExpressions()
     */
    @Override
    protected Collection<Expression> getExpressions ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    public static Expression create (String functionName,
                                     List<Expression> parameters)
    {
        // TODO Auto-generated method stub
        return new FunctionExpression(functionName, parameters);
    }

}
