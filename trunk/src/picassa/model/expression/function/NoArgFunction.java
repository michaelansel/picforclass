/**
 * 
 */
package picassa.model.expression.function;

import java.util.List;
import java.util.Map;
import picassa.model.expression.Expression;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public abstract class NoArgFunction extends FunctionExpression
{
    public NoArgFunction (String functionName,
                                    List<Expression> parameters)
    {
        super(functionName, parameters);
    }


    @Override
    public abstract Vector<Number> evaluate (Map<String, Expression> variables);
}
