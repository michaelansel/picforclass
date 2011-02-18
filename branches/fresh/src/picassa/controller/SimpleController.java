/**
 * 
 */
package picassa.controller;

import java.io.File;
import picassa.model.expression.Expression;
import picassa.model.parser.ParserException;
import picassa.util.Pixmap;
import picassa.view.AbstractView;


/**
 * @author Michael Ansel
 */
public class SimpleController extends AbstractController
{

    /**
     * @see picassa.controller.AbstractController#evaluateExpression(java.lang.String)
     */
    @Override
    public void evaluateExpression (String expression)
    {
        try
        {
            Expression parsedExpression =
                getModel().parseExpression(expression);
            Pixmap renderedExpression =
                getModel().renderExpression(parsedExpression,
                                            getView().getDisplay());
            getView().updateDisplay(renderedExpression);
            // TODO addExpressionToHistory()
        }
        catch (ParserException e)
        {
            e.printStackTrace();
            getView().updateDisplay(AbstractView.RENDER_FAILED_IMAGE);
        }
    }


    /**
     * @see picassa.controller.AbstractController#loadStateFromFile(java.io.File)
     */
    @Override
    public void loadStateFromFile (File file)
    {
        // TODO Implement SimpleController.loadStateFromFile

    }


    /**
     * @see picassa.controller.AbstractController#saveStateToFile(java.io.File)
     */
    @Override
    public void saveStateToFile (File file)
    {
        // TODO Implement SimpleController.saveStateToFile

    }

}
