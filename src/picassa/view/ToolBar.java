package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;


/**
 * @author Andrea Scripa
 */

public class ToolBar extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static String LOAD = "LOAD";
    private static String RESET = "RESET";
    private static String FRACTALIZE = "FRACTALIZE";
    private static String QUIT = "QUIT";
    private AbstractView myView;


    public ToolBar (JFrame container, Dimension size, AbstractView view)
    {
        super(new BorderLayout());
        myView = view;
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        setPreferredSize(size);

        addButtons(toolBar);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        add(toolBar, BorderLayout.CENTER);
    }


    protected void addButtons (JToolBar toolBar)
    {
        ResourceBundle myResources =
            ResourceBundle.getBundle("picassa.resources.view");

        JButton button =
            makeToolBarButton(myResources.getString("Button1"), LOAD);
        toolBar.add(button);

        button = makeToolBarButton(myResources.getString("Button2"), RESET);
        toolBar.add(button);

        button =
            makeToolBarButton(myResources.getString("Button3"), FRACTALIZE);
        toolBar.add(button);

        button = makeToolBarButton(myResources.getString("Button4"), QUIT);
        toolBar.add(button);
    }


    protected JButton makeToolBarButton (String name, String actionCommand)
    {
        JButton button = new JButton(name);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        return button;
    }


    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (LOAD.equals(e.getActionCommand()))
        {
            //TODO:  Enable a user to load an expression from a file.
            System.out.println("Load was pressed.");
        }
        else if (RESET.equals(e.getActionCommand()))
        {
            //System.out.println("Reset was pressed.");
            myView.getController().reset();

        }
        else if (FRACTALIZE.equals(e.getActionCommand()))
        {
            //System.out.println("Fractalize was pressed.");
            String userExpression = myView.getUserExpression();
            myView.getController().evaluateExpression("fractalize:" +
                                                      userExpression);

        }
        else if (QUIT.equals(e.getActionCommand()))
        {
            //TODO:  "Do you want to save the current picture??"
            System.exit(0);
        }
        else
        {
            return;
        }
    }

}
