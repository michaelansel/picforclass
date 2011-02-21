/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Max Egan
 */
public class AtanFunction extends OneArgFunction
{

	public final static String FUNCTION_NAME = "atan";


    /**
     * @param functionName
     * @param parameters
     */
    public AtanFunction(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.cos(value.doubleValue());
    }
}
