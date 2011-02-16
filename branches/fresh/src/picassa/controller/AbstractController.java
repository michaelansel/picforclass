/**
 * 
 */
package picassa.controller;

import java.io.File;
import picassa.model.AbstractModel;
import picassa.view.AbstractView;

/**
 * @author Michael Ansel
 *
 */
public abstract class AbstractController
{
    private AbstractView myView;
    private AbstractModel myModel;

    public void setView (AbstractView view)
    {
        myView = view;
    }
    
    public AbstractView getView()
    {
        return myView;
    }

    public void setModel (AbstractModel model)
    {
        myModel = model;
    }
    
    public AbstractModel getModel()
    {
        return myModel;
    }
    
    /**
     * Requests evaluation of the provided expression by the model and updates the model accordingly.
     * Model calls:
     *  * parseExpression() - parse the input expression string
     *  * renderExpression() - render the parsed expression string
     * 
     * View calls:
     *  * getDisplay() - base image for rendering expression
     *  * updateDisplay() - display the newly rendered image
     *  * addExpressionToHistory() - add rendered expression to history of rendered expression
     * 
     * @param expression Expression to be evaluated
     */
    public abstract void evaluateExpression(String expression);
    
    public abstract void loadStateFromFile(File file);
    public abstract void saveStateToFile(File file);
}
