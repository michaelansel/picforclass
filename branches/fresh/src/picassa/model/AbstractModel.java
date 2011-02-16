/**
 * 
 */
package picassa.model;

import java.util.List;
import picassa.controller.AbstractController;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;

/**
 * @author Michael Ansel
 *
 */
public abstract class AbstractModel
{

    protected AbstractController myController;

    public void setController (AbstractController controller)
    {
        myController = controller;
    }
    
    /**
     * TODO JavaDoc
     * @param expression
     * @return
     */
    public abstract Expression parseExpression(String expression);
    
    /**
     * TODO JavaDoc
     * @param expression expression tree to render
     * @param base image to use as start point for rendering
     * @return
     */
    public abstract Pixmap renderExpression(Expression expression, Pixmap image);
    
    /**
     * Short-cut helper to parse and render an expression in one step.
     * 
     * @param expression expression to parse and render
     * @param image base image to use as start point for rendering
     * @return rendered expression
     */
    public Pixmap renderExpression(String expression, Pixmap image)
    {
        return renderExpression(parseExpression(expression), image);
    }
    
    /**
     * TODO JavaDoc
     * @param variableName
     * @param variableValue
     */
    public abstract void setVariable(String variableName, Variable variableValue);
    
    /**
     * TODO JavaDoc
     * @return a list of all registered variables
     */
    public abstract List<Variable> getVariables();
    
    /**
     * Retrieves a variable by name
     * 
     * @param name name of variable to retrieve
     * @return named variable, if found; otherwise, null
     */
    public abstract Variable getVariable(String name);
}
