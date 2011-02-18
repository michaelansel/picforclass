/**
 * 
 */
package picassa;

import javax.swing.JOptionPane;
import picassa.controller.AbstractController;
import picassa.controller.SimpleController;
import picassa.model.AbstractModel;
import picassa.model.SimpleModel;
import picassa.view.AbstractView;
import picassa.view.SwingView;


/**
 * @author Michael Ansel
 */
public class Picassa
{
    public static void main (String[] args)
    {
        AbstractView view = new SwingView();
        AbstractController controller = new SimpleController();
        AbstractModel model = new SimpleModel();

        view.setController(controller);
        controller.setView(view);
        controller.setModel(model);
        model.setController(controller);

        // Temporary code for testing the controller until the view is fully implemented
        String expression = "random()";
        while (Thread.activeCount() > 1)
        {
            System.out.println("computing");
            expression =
                JOptionPane.showInputDialog("Please enter an expression to render:",
                                            expression);
            if (expression == null) break;
            controller.evaluateExpression(expression.replaceAll("\\s", ""));
            try
            {
                System.out.println("sleeping");
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
