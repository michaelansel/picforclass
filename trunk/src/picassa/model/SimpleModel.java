/**
 * 
 */
package picassa.model;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import picassa.model.expression.Expression;
import picassa.model.parser.SimpleParser;
import picassa.util.RGBColor;
import util.parser.ParserException;


/**
 * @author Michael Ansel
 */
public class SimpleModel extends AbstractModel
{

    /**
     * @throws ParserException
     * @see picassa.model.AbstractModel#parseExpression(java.lang.String)
     */
    @Override
    public Expression parseExpression (String expression)
        throws ParserException
    {
        return SimpleParser.parse(expression);
    }


    protected Color renderExpression (Expression expression,
                                      Map<String, Number> variables)
    {
        List<Number> values = expression.evaluate(variables);
        Color color;
        if (values.size() == 1) color =
            new RGBColor(values.get(0).doubleValue()).toJavaColor();
        else if (values.size() == 3) color =
            new RGBColor(values.get(0).doubleValue(),
                         values.get(1).doubleValue(),
                         values.get(2).doubleValue()).toJavaColor();
        else throw new RuntimeException("Expression did not return a valid color value");
        return color;
    }
}
