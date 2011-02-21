/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Max Egan
 */
public class LogFunction extends OneArgFunction
{

	public final static String FUNCTION_NAME = "log";


    /**
     * @param functionName
     * @param parameters
     */
    public LogFunction(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.log(value.doubleValue());
    }
}
