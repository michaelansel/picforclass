/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Max Egan
 */
public class TanFunction extends OneArgFunction
{

	public final static String FUNCTION_NAME = "tan";


    /**
     * @param functionName
     * @param parameters
     */
    public TanFunction(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.tan(value.doubleValue());
    }
}
