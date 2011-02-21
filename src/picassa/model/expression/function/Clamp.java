package picassa.model.expression.function;

import java.util.List;

import picassa.model.expression.Expression;

/**
 * @author Max Egan
 */

public class Clamp extends OneArgFunction
{
	public final static String FUNCTION_NAME = "clamp";

	
	 public Clamp(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


	    @Override
	    protected Number evaluateValue (Number value)
	    {

	        if(value.floatValue() < -1)
	        {
	        	return -1;
	        }
	        if (value.floatValue() > 1)
	        {
	        	return 1;
	        }
	        return value;
	    }
}
