/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.PerlinNoise;
import picassa.util.Vector;


/**
 * 
 * @author Robert C. Duvall
 * @author Michael Ansel
 * @author Max Egan
 */
public class MaxFunction extends FunctionExpression
{
    public final static String FUNCTION_NAME = "max";


    /**
     * @param functionName
     * @param parameters
     */
    public MaxFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    protected Vector<Number> evaluateVectors (Vector<Number> vectorA,
                                              Vector<Number> vectorB,
                                              Vector<Number> vectorC)
    {
        throw new UnsupportedOperationException();
    }


	@Override
	protected Number evaluateValues(Number...values) {
	    double max = values[0].doubleValue();
        for(Number value : values)
            max = Math.max(max, value.doubleValue());
        return max;
	}

}
