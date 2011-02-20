/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.PerlinNoise;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public class PerlinColorFunction extends TwoArgFunction
{
    public final static String FUNCTION_NAME = "perlinColor";


    /**
     * @param functionName
     * @param parameters
     */
    public PerlinColorFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number valueA, Number valueB)
    {
        throw new UnsupportedOperationException();
    }


    @Override
    protected Vector<Number> evaluateVectors (Vector<Number> vectorA,
                                              Vector<Number> vectorB)
    {
        return new Vector<Number>(PerlinNoise.noise(vectorA.toRGBColor()
                                                           .getRed() + 0.3,
                                                    vectorB.toRGBColor()
                                                           .getRed() + 0.3,
                                                    0),
                                  PerlinNoise.noise(vectorA.toRGBColor()
                                                           .getGreen() - 0.8,
                                                    vectorB.toRGBColor()
                                                           .getGreen() - 0.8,
                                                    0),
                                  PerlinNoise.noise(vectorA.toRGBColor()
                                                           .getBlue() + 0.1,
                                                    vectorB.toRGBColor()
                                                           .getBlue() + 0.1,
                                                    0));
    }

}
