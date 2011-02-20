/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class AbsFunction extends OneArgFunction
{
    public final static String FUNCTION_NAME = "abs";


    public AbsFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.abs(value.doubleValue());
    }
}
