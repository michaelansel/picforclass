package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import model.Pixmap;
import util.commands.*;


/**
 * The collection of commands represented as buttons that apply to the active image.
 * 
 * @author Robert C Duvall
 */
public class CommandPanel extends JPanel
{
    // used for serialization
    private static final long serialVersionUID = 1L;
    private Canvas myView;


    /**
     * Create panel that will update the given view.
     */
    public CommandPanel (Canvas view)
    {
        myView = view;
    }


    /**
     * Add the given Command as a button with the given name.
     */
    public void add (String name, final Command<Pixmap> command)
    {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener()
            {
                public void actionPerformed (ActionEvent e)
                {
                	myView.execute(command);
                }
            });
        add(button);
    }


    /**
     * Add the given Command as a button.
     */
    public void add (NamedCommand<Pixmap> action)
    {
        add(action.getName(), action);
    }
}
