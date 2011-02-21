package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * @author Andrea Scripa
 */

public class InputPanel extends JComponent implements ActionListener
{
    ResourceBundle myResources =
        ResourceBundle.getBundle("picassa.resources.view");
    private static String RENDER;
    private static int textFieldSize = 50;
    private TextHandler handler = null;


    public InputPanel (JFrame container, Dimension size)
    {
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField userExpression =
            new JTextField(myResources.getString("TextFieldMessage"), textFieldSize);
        //userExpression.setFont(new Font("Timesroman", font.PLAIN, 12));
        
        handler = new TextHandler();
        userExpression.addActionListener(handler);
        inputPanel.add(userExpression, BorderLayout.WEST);
        
        addButton(inputPanel);
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


    public void actionPerformed (ActionEvent e)
    {
        if (RENDER.equals(e.getActionCommand()))
        {
            System.out.println("Render was pressed.");
        }
        else
        {
            return;
        }
    }
    
    private class TextHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           String currentInput = e.getActionCommand();
        }
    }
}
