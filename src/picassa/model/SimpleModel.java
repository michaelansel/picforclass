/**
 * 
 */
package picassa.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import picassa.model.expression.AssignmentExpression;
import picassa.model.expression.Expression;
import picassa.model.parser.SimpleParser;
import util.parser.ParserException;


/**
 * @author Michael Ansel
 */
public class SimpleModel extends AbstractModel
{

    protected Map<String, Expression> myVariables;


    public SimpleModel ()
    {
        myVariables = new HashMap<String, Expression>();
    }


    public SimpleModel (Map<String, Expression> variables)
    {
        myVariables = new HashMap<String, Expression>(variables);
    }


    public Map<String, Expression> getVariables ()
    {
        return myVariables;
    }


    /**
     * @throws ParserException
     * @see picassa.model.AbstractModel#parseExpression(java.lang.String)
     */
    @Override
    public Expression parseExpression (String expression)
        throws ParserException
    {
        Expression parsedExpression = SimpleParser.parse(expression);
        if (parsedExpression instanceof AssignmentExpression)
        {
            System.out.println("===== " +
                               ((AssignmentExpression) parsedExpression).toString());
            myVariables.put(((AssignmentExpression) parsedExpression).getName(),
                            parsedExpression);
        }
        return parsedExpression;
    }


    protected Color renderExpression (Expression expression,
                                      Map<String, Expression> variables)
    {
        // merge in stored variables
        Map<String, Expression> newVariables =
            new HashMap<String, Expression>();
        newVariables.putAll(myVariables);
        newVariables.putAll(variables);
        return expression.evaluate(newVariables).toRGBColor().toJavaColor();
    }
}
