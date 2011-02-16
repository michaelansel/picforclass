/**
 * 
 */
package picassa;

import picassa.controller.AbstractController;
import picassa.controller.SimpleController;
import picassa.model.AbstractModel;
import picassa.model.SimpleModel;
import picassa.view.AbstractView;
import picassa.view.SwingView;

/**
 * @author Michael Ansel
 *
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
    }
}
