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
 */
public class PerlinBWFunction extends TwoArgFunction
{
    public final static String FUNCTION_NAME = "perlinBW";


    /**
     * @param functionName
     * @param parameters
     */
    public PerlinBWFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number valueA, Number valueB)
    {
        throw new UnsupportedOperationException();
    }


    protected Vector<Number> evaluateVectors (Vector<Number> vectorA,
                                              Vector<Number> vectorB)
    {
        double value =
            PerlinNoise.noise(vectorA.get(0).doubleValue() +
                                      vectorB.get(0).doubleValue(),
                              vectorA.get(1).doubleValue() +
                                      vectorB.get(1).doubleValue(),
                              vectorA.get(2).doubleValue() +
                                      vectorB.get(2).doubleValue());
        return new Vector<Number>(value);
    }

}
