/**
 * 
 */
package picassa.model.expression.function;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import picassa.model.expression.Expression;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public class RandomFunction extends NoArgFunction
{
    public final static Integer PARAMETER_COUNT = 0;

    public final static String FUNCTION_NAME = "random";


    public RandomFunction (String functionName,
                                     List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        return new Vector<Number>(Math.random());
    }

}
