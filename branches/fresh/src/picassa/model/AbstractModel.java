/**
 * 
 */
package picassa.model;

import picassa.controller.AbstractController;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 */
public abstract class AbstractModel
{

    protected AbstractController myController;


    /**
     * TODO JavaDoc
     * 
     * @param expression
     * @return
     */
    public abstract Expression parseExpression (String expression);


    /**
     * TODO JavaDoc
     * 
     * @param expression expression tree to render
     * @param base image to use as start point for rendering
     * @return
     */
    public abstract Pixmap renderExpression (Expression expression, Pixmap image);


    /**
     * Short-cut helper to parse and render an expression in one step.
     * 
     * @param expression expression to parse and render
     * @param image base image to use as start point for rendering
     * @return rendered expression
     */
    public Pixmap renderExpression (String expression, Pixmap image)
    {
        return renderExpression(parseExpression(expression), image);
    }


    public void setController (AbstractController controller)
    {
        myController = controller;
    }

}
