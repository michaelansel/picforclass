/**
 * 
 */
package picassa.model.expression;

import java.util.List;


/**
 * @author Michael Ansel
 */
public class SinFunctionExpression extends FunctionExpression
{
    public final static Integer PARAMETER_COUNT = 1;

    public final static String FUNCTION_NAME = "sin";


    /**
     * @param functionName
     * @param parameters
     */
    public SinFunctionExpression (String functionName,
                                  List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        return Math.sin(values[0].doubleValue());
    }
}
