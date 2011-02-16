/**
 * 
 */
package picassa.model;

import picassa.model.expression.Expression;
import picassa.model.parser.AbstractParser;
import picassa.model.parser.ExpressionParser;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 */
public class SimpleModel extends AbstractModel
{

    /**
     * @see picassa.model.AbstractModel#parseExpression(java.lang.String)
     */
    @Override
    public Expression parseExpression (String expression)
    {
        try
        {
            return AbstractParser.runParser(ExpressionParser.class, expression);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @see picassa.model.AbstractModel#renderExpression(picassa.model.expression.Expression,
     *      picassa.util.Pixmap)
     */
    @Override
    public Pixmap renderExpression (Expression expression, Pixmap image)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
