/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.Vector;


/**
 * @author Max Egan
 */
public abstract class ThreeArgFunction extends FunctionExpression
{

    public final static Integer PARAMETER_COUNT = 3;


    /**
     * @param functionName
     * @param parameters
     */
    public ThreeArgFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 3) throw new IllegalArgumentException("Function requires 3 arguments");
        return evaluateValues(values[0], values[1], values[2]);
    }


    protected abstract Number evaluateValues (Number valueA, Number valueB, Number valueC);


    @Override
    protected Vector<Number> evaluateVectors (Vector<Number> ... vectors)
    {
        if (vectors.length != 3) throw new IllegalArgumentException("Function requires 3 arguments");
        return evaluateVectors(vectors[0], vectors[1], vectors[2]);
    }


    protected Vector<Number> evaluateVectors (Vector<Number> vectorA,
                                              Vector<Number> vectorB,
                                              Vector<Number> vectorC)
    {
        return super.evaluateVectors(vectorA, vectorB, vectorC);
    }
}
