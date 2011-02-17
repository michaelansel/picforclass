/**
 * 
 */
package picassa.model;

import picassa.model.expression.Expression;
import picassa.model.parser.ParserException;
import picassa.model.parser.SimpleParser;
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
            return SimpleParser.parse(expression);
        }
        catch (ParserException e)
        {
            // TODO Auto-generated catch block
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
