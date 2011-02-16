/**
 * 
 */
package picassa.model;

import java.util.List;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;

/**
 * @author Michael Ansel
 *
 */
public class SimpleModel extends AbstractModel
{

    /**
     * @see picassa.model.AbstractModel#parseExpression(java.lang.String)
     */
    @Override
    public Expression parseExpression (String expression)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @see picassa.model.AbstractModel#renderExpression(picassa.model.expression.Expression, picassa.util.Pixmap)
     */
    @Override
    public Pixmap renderExpression (Expression expression, Pixmap image)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @see picassa.model.AbstractModel#setVariable(java.lang.String, picassa.model.Variable)
     */
    @Override
    public void setVariable (String variableName, Variable variableValue)
    {
        // TODO Auto-generated method stub

    }


    /**
     * @see picassa.model.AbstractModel#getVariables()
     */
    @Override
    public List<Variable> getVariables ()
    {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @see picassa.model.AbstractModel#getVariable(java.lang.String)
     */
    @Override
    public Variable getVariable (String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
