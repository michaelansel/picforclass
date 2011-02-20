/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public abstract class OneArgFunction extends FunctionExpression
{
    public final static Integer PARAMETER_COUNT = 1;


    /**
     * @param functionName
     * @param parameters
     */
    public OneArgFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    protected abstract Number evaluateValue (Number value);


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 1) throw new IllegalArgumentException("Function requires a single argument");
        return evaluateValue(values[0]);
    }

}
