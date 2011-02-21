package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import picassa.controller.AbstractController;


/**
 * @author Andrea Scripa
 */

public class InputPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    ResourceBundle myResources =
        ResourceBundle.getBundle("picassa.resources.view");
    private static String RENDER = "RENDER";
    private static int textFieldSize = 35;
    private JTextField userExpression;
    private SwingView myView;


    public InputPanel (JFrame container, Dimension size, SwingView swingView)
    {
        super(new BorderLayout());
        myView = swingView;
        userExpression = new JTextField("", textFieldSize);

        userExpression.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed (ActionEvent arg0)
            {
                myView.getController()
                      .evaluateExpression(userExpression.getText());
            }
        });
        this.add(userExpression, BorderLayout.WEST);

        addButton(this);
        setPreferredSize(size);
    }


    protected void addButton (JPanel inputPanel)
    {
        JButton button =
            makeInputButton(myResources.getString("RenderButton"), RENDER);
        inputPanel.add(button, BorderLayout.EAST);
    }


    protected JButton makeInputButton (String name, String actionCommand)
    {
        JButton button = new JButton(name);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);

        return button;
    }


    public String getUserExpression ()
    {
        return userExpression.getText();
    }


    public void actionPerformed (ActionEvent e)
    {
        if (RENDER.equals(e.getActionCommand()))
        {
            //System.out.println("Render was pressed.");
            myView.getController().evaluateExpression(userExpression.getText());
        }
        else
        {
            return;
        }
    }
}
