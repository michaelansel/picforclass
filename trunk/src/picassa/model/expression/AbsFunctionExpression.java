/**
 * 
 */
package picassa.model.expression;

import java.util.List;


/**
 * @author Michael Ansel
 */
public class AbsFunctionExpression extends FunctionExpression
{

    public final static Integer PARAMETER_COUNT = 1;

    public final static String FUNCTION_NAME = "abs";


    public AbsFunctionExpression (String functionName,
                                  List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        return Math.abs(values[0].doubleValue());
    }

}
