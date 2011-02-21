package picassa.view;

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
    private static String LOAD;
    private static String RESET;
    private static String FRACTALIZE;
    private static String QUIT;


    public ToolBar (JFrame container, Dimension size)
    {
        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        setPreferredSize(size);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
    }


    protected void addButtons (JToolBar toolBar)
    {
        ResourceBundle myResources =
            ResourceBundle.getBundle("picassa.resources.view");

        JButton button = makeToolBarButton(myResources.getString("Button1"), LOAD);
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
            System.out.println("Load was pressed.");
        }
        else if (RESET.equals(e.getActionCommand()))
        {
           System.out.println("Reset was pressed");
        }
        else if (FRACTALIZE.equals(e.getActionCommand()))
        {
            System.out.println("Fractalize was pressed");
        }
        else if (QUIT.equals(e.getActionCommand()))
        {
            System.exit(0);
        }
        else
        {
            return;
        }
    }

}
