/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.PerlinNoise;
import picassa.util.Vector;


/**
 * Combine two colors by using their components to seed noise function. Perlin
 * noise algorithm described in detail at:
 * http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
 * 
 * @author Robert C. Duvall
 * @author Michael Ansel
 * @author Max Egan
 */
public class MinFunction extends ThreeArgFunction
{
    public final static String FUNCTION_NAME = "min";


    /**
     * @param functionName
     * @param parameters
     */
    public MinFunction (String functionName, List<Expression> parameters)
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
		 double min = Math.min(valueA.doubleValue(), valueB.doubleValue());
	        return Math.min(min, valueC.doubleValue());
	}

}
