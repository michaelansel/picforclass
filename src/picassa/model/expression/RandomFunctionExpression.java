/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author Michael Ansel
 */
public class RandomFunctionExpression extends FunctionExpression
{
    public final static Integer PARAMETER_COUNT = 0;

    public final static String FUNCTION_NAME = "random";


    public RandomFunctionExpression (String functionName,
                                     List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        Number[] results = { Math.random(), Math.random(), Math.random() };
        return Arrays.asList(results);
    }

}
