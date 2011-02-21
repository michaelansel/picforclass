package picassa.model.expression.function;

import java.util.List;

import picassa.model.expression.Expression;

/**
 * @author Max Egan
 */

public class Ceil extends OneArgFunction
{
	public final static String FUNCTION_NAME = "ceil";

	
	 public Ceil(String functionName, List<Expression> parameters) {
		super(functionName, parameters);
	}


	    @Override
	    protected Number evaluateValue (Number value)
	    {
	        return Math.ceil(value.doubleValue());
	    }
}
