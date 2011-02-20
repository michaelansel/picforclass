/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class SinFunction extends OneArgFunction
{
    public final static String FUNCTION_NAME = "sin";


    /**
     * @param functionName
     * @param parameters
     */
    public SinFunction (String functionName,
                                  List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.sin(value.doubleValue());
    }
}
