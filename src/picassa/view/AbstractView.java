/**
 * 
 */
package picassa.view;

import picassa.controller.AbstractController;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 */
public abstract class AbstractView
{
    final public static Pixmap RENDER_FAILED_IMAGE =
        new Pixmap(300, 300, new java.awt.Color(255, 0, 0)); // TODO RENDER_FAILED_IMAGE

    private AbstractController myController;


    public abstract void addExpressionToHistory (Expression expression);


    public AbstractController getController ()
    {
        return myController;
    }


    /**
     * @return the currently displayed image
     */
    public abstract Pixmap getDisplay ();


    public void setController (AbstractController controller)
    {
        myController = controller;
    }


    public abstract void updateDisplay (Pixmap image);


    public abstract String getUserExpression ();
}
