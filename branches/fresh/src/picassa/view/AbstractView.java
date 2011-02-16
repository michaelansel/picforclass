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
    private AbstractController myController;


    public void setController (AbstractController controller)
    {
        myController = controller;
    }


    public AbstractController getController ()
    {
        return myController;
    }


    public abstract void addExpressionToHistory (Expression expression,
                                                 Pixmap image,
                                                 String name);


    public abstract void updateDisplay (Pixmap image);


    /**
     * @return the currently displayed image
     */
    public abstract Pixmap getDisplay ();
}
