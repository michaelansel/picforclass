package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Model;
import model.ParserException;
import model.Pixmap;


/**
 * A simple frame to organize the view.
 * 
 * @author Robert C Duvall
 */
public class Frame extends JFrame
{
    // default version number
    private static final long serialVersionUID = 1L;
    // my state
    private Model myModel;
    private JLabel myDisplay;
    private MouseMotionListener myMouseMotionListener;
    private JTextField myInput;
    private Dimension mySize;
    
   


    /**
     * Create frame with the given title and size for the interior canvas.
     */
    public Frame (String title, Dimension size)
    {
    	makeMouseListener();
    	
        mySize = size;
        // create GUI components
        myDisplay = makeDisplay(size);
        myDisplay.addMouseMotionListener(myMouseMotionListener);
        myInput = makeInput();
        
        // add containers to Frame and show it
        getContentPane().add(myDisplay, BorderLayout.CENTER);
        getContentPane().add(new JScrollPane(myInput), BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        pack();
    }


    /**
     * Updates current model for this view.
     */
    
    public void showMessage (String s)
    {
        System.out.println(s);
    }
    
    private void echo (String s, MouseEvent e)
    {
        showMessage(s + " x = " + e.getX() + " y = " + e.getY() + " mod: "+
                    MouseEvent.getMouseModifiersText(e.getModifiers()) +
                    " button: "+e.getButton() + " clicks " + e.getClickCount());
    }
    
    public void makeMouseListener()
    {
    	//System.out.println("Mouse listener");
    	 myMouseMotionListener = new MouseMotionListener()
         {
             public void mouseDragged (MouseEvent e)
             {
                 echo("drag", e);
             }
             public void mouseMoved (MouseEvent e)
             {
                 echo("move", e);
             }
         };
    }
    
    public void setModel (Model model)
    {
        if (model != null)
        {
            myModel = model;
        }
    }


    // Return input area where ENTER evaluates expression.
    protected JTextField makeInput ()
    {
        JTextField result = new JTextField();
        result.setBorder(BorderFactory.createLoweredBevelBorder());
        result.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent evt)
                {
                    try
                    {
                        myDisplay.setIcon(myModel.evaluate(myInput.getText(), mySize).toIcon());
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

    // Return display area for results of expression
    private JLabel makeDisplay (Dimension size)
    {
        JLabel result = new JLabel(new Pixmap(size).toIcon());
        result.setBorder(BorderFactory.createLoweredBevelBorder());
        return result;
    }
}
