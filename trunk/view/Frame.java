package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import model.*;
import util.commands.*;
import util.resources.ResourceManager;
import view.commands.*;


/**
 * A simple frame that sets the organization of the view.
 * 
 * @author Robert C Duvall
 */
public class Frame extends JFrame
{
    // used for serialization
    private static final long serialVersionUID = 1L;
    private Model myModel;


	/**
	 * Create frame with the given title and size for the interior canvas.
	 */
    public Frame (Model model)
    {
        setModel(model);
        // set properties
        ResourceManager resources = ResourceManager.getInstance();
        resources.addResourcesFromFile("view");
        setTitle(resources.getString("title"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // create GUI components
        int[] size = resources.getIntegerArray("size", "x"); 
        Canvas display = new Canvas(this, new Dimension(size[0], size[1]));
        Command<Pixmap> evaluator = new ThreadedCommand<Pixmap>(display, new Evaluater());
        JTextField input = makeInput(display, evaluator);
        // add commands here
        CommandPanel commands = new CommandPanel(display);
        commands.add(resources.getString("open"), new Reader());
        commands.add(resources.getString("evaluate"), evaluator);
        commands.add(resources.getString("save"), new Writer());
        // add our container to Frame and show it
        getContentPane().add(display, BorderLayout.CENTER);
        getContentPane().add(commands, BorderLayout.NORTH);
        getContentPane().add(input, BorderLayout.SOUTH);
        pack();
    }


    /**
     * Updates current model for this view.
     */
    public void setModel (Model model)
    {
        if (model != null)
        {
            myModel = model;
        }
    }
    

    // Return input area where ENTER evaluates expression.
    protected JTextField makeInput (final Canvas display, final Command<Pixmap> action)
    {
        final JTextField result = new JTextField();
        result.setBorder(BorderFactory.createLoweredBevelBorder());
        result.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent evt)
                {
                    try
                    {
                        display.execute(action);
                        //Dimension size = display.getSize();
                        //myModel.evaluate(result.getText(), size.width, size.height);
                    }
                    catch (ParserException e)
                    {
                        JOptionPane.showMessageDialog(Frame.this,
                                                      e.getMessage(),
                                                      "Input Error",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        return result;
    }
}
