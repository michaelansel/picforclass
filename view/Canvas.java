package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import util.commands.Command;
import model.Pixmap;


/**
 * A component that displays itself as a pixmap.
 * 
 * @author Robert C Duvall
 */
public class Canvas extends JPanel
{
    // used for serialization
    private static final long serialVersionUID = 1L;
    private JFrame myContainer;
    private Pixmap myPixmap;


    /**
     * Create canvas within the given Frame and with the given size.
     */
    public Canvas (JFrame container, Dimension size)
    {
        setBorder(BorderFactory.createLoweredBevelBorder());
        myContainer = container;
        myPixmap = new Pixmap(size);
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized (ComponentEvent e)
            {
                myPixmap.setSize(getSize());
            }
        });
        refresh();
    }


    /**
     * Apply the given command to this canvas.
     */
    public void execute (Command<Pixmap> command)
    {
        command.execute(myPixmap);
        refresh();
    }


    /**
     * Render the contents of this canvas to the screen.
     */
    public void paintComponent (Graphics pen)
    {
        super.paintComponent(pen);
        myPixmap.paint(pen);
    }


    /**
     * Change the size of this component so that it cannot be smaller than the given size.
     */
    public void setSize (Dimension size)
    {
        setPreferredSize(size);
        setMinimumSize(size);
        super.setSize(size);
    }


    /**
     * Refresh the contents of the canvas by updating its size and pixmap.
     */
    private void refresh ()
    {
        if (! myPixmap.getSize().equals(getSize()))
        {
            setSize(myPixmap.getSize());
            myContainer.pack();
        }
        repaint();
    }
    
    /**
     * returns a color from coordinates in the canvas
     */
    public Color getColorAtXY(int x, int y)
    {
    	return myPixmap.getColor(x, y);
    }
}