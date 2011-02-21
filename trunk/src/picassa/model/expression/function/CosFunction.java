/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Max Egan
 */
public class CosFunction extends OneArgFunction
{

	public final static String FUNCTION_NAME = "cos";


    /**
     * @param functionName
     * @param parameters
     */
    public CosFunction(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


    @Override
    protected Number evaluateValue (Number value)
    {
        return Math.cos(value.doubleValue());
    }
}
