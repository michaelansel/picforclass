package picassa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import picassa.util.Pixmap;


/**
 * A component that displays itself as a Pixmap.
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
     * returns a color from coordinates in the canvas
     */
    public Color getColorAtXY (int x, int y)
    {
        return myPixmap.getColor(x, y);
    }


    public Pixmap getPixmap ()
    {
        return new Pixmap(myPixmap);
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
     * Refresh the contents of the canvas by updating its size and pixmap.
     */
    private void refresh ()
    {
        if (!myPixmap.getSize().equals(getSize()))
        {
            setSize(myPixmap.getSize());
            myContainer.pack();
        }
        repaint();
    }


    public void setPixmap (Pixmap newPixmap)
    {
        myPixmap = newPixmap;
        setSize(myPixmap.getSize());
        myContainer.pack();
        repaint();
    }


    /**
     * Change the size of this component so that it cannot be smaller than the
     * given size.
     */
    public void setSize (Dimension size)
    {
        setPreferredSize(size);
        setMinimumSize(size);
        super.setSize(size);
    }
}
