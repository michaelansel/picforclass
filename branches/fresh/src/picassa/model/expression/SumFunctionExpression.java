/**
 * 
 */
package picassa.model.expression;

import java.util.List;


/**
 * @author Michael Ansel
 */
public class SumFunctionExpression extends FunctionExpression
{
    public final static Integer PARAMETER_COUNT = -1;

    public final static String FUNCTION_NAME = "sum";


    public SumFunctionExpression (String functionName,
                                  List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        double sum = 0;
        for (Number value : values)
            sum += value.doubleValue();
        return sum;
    }
}
