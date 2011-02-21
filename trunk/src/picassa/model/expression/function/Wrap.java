package picassa.model.expression.function;

import java.util.List;

import picassa.model.expression.Expression;

/**
 * @author Max Egan
 */

public class Wrap extends OneArgFunction
{
	public final static String FUNCTION_NAME = "wrap";

	
	 public Wrap(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


	    @Override
	    protected Number evaluateValue (Number value)
	    {

	        if(value.floatValue() < -1)
	        {
	        	float floatValue = value.floatValue();
	        	float coreValue = floatValue %1;
	        	return floatValue - coreValue;
	        }
	        if (value.floatValue() > 1)
	        {
	        	float floatValue = value.floatValue();
	        	float coreValue = floatValue %1;
	        	return floatValue - coreValue;
	        }
	        return value;
	    }
}
