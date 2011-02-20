/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class SumFunction extends FunctionExpression
{
    public final static String FUNCTION_NAME = "sum";


    public SumFunction (String functionName, List<Expression> parameters)
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
