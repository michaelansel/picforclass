/**
 * 
 */
package picassa.controller;

import java.io.File;
import picassa.model.FractalModel;
import picassa.model.SimpleModel;
import picassa.util.Pixmap;
import picassa.view.AbstractView;
import util.parser.ParserException;


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
            Pixmap renderedExpression = null;
            String[] ary = expression.split(":", 2);
            if (ary.length > 1 && ary[0].equals("fractalize"))
            {
                System.out.println(ary);
                expression = ary[1];
                renderedExpression =
                    new FractalModel(((SimpleModel) getModel()).getVariables()).renderExpression(getModel().parseExpression(expression),
                                                                                                 getView().getDisplay());
            }
            else
            {
                renderedExpression =
                    getModel().renderExpression(getModel().parseExpression(expression),
                                                getView().getDisplay());
            }
            getView().updateDisplay(renderedExpression);
            // TODO addExpressionToHistory()
        }
        catch (ParserException e)
        {
            e.printStackTrace();
            getView().updateDisplay(AbstractView.RENDER_FAILED_IMAGE);
        }
        catch (RuntimeException e)
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
