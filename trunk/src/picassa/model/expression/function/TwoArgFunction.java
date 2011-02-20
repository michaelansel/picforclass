/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public abstract class TwoArgFunction extends FunctionExpression
{

    public final static Integer PARAMETER_COUNT = 2;


    /**
     * @param functionName
     * @param parameters
     */
    public TwoArgFunction (String functionName, List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 2) throw new IllegalArgumentException("Function requires 2 arguments");
        return evaluateValues(values[0], values[1]);
    }


    protected abstract Number evaluateValues (Number valueA, Number valueB);


    @Override
    protected Vector<Number> evaluateVectors (Vector<Number> ... vectors)
    {
        if (vectors.length != 2) throw new IllegalArgumentException("Function requires 2 arguments");
        return evaluateVectors(vectors[0], vectors[1]);
    }


    protected Vector<Number> evaluateVectors (Vector<Number> vectorA,
                                              Vector<Number> vectorB)
    {
        return super.evaluateVectors(vectorA, vectorB);
    }
}
