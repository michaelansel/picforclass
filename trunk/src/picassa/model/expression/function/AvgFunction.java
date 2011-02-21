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
public class AvgFunction extends ThreeArgFunction
{
    public final static String FUNCTION_NAME = "avg";


    /**
     * @param functionName
     * @param parameters
     */
    public AvgFunction (String functionName, List<Expression> parameters)
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
	protected Number evaluateValues(Number valueA, Number valueB, Number valueC) {
		 double sum = valueA.doubleValue() + valueB.doubleValue() + valueC.doubleValue();
		 return sum / 3.0;
	}

}
