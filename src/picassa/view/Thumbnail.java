package picassa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import picassa.util.Pixmap;


/**
 * @author Andrea Scripa
 */

public class Thumbnail extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JFrame myContainer;
    private Pixmap myPixmap;


    public Thumbnail (JFrame container, Dimension size)
    {
        myContainer = container;
        myPixmap = new Pixmap(size);
        
        /**
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized (ComponentEvent e)
            {
                myPixmap.setSize(getSize());
            }
        });
        */
        
        refresh();
    }


    public Color getColorAtXY (int x, int y)
    {
        return myPixmap.getColor(x, y);
    }


    public Pixmap getPixmap ()
    {
        return new Pixmap(myPixmap);
    }


    public void paintComponent (Graphics pen)
    {
        super.paintComponent(pen);
        myPixmap.paint(pen);
    }


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


    public void setSize (Dimension size)
    {
        setPreferredSize(size);
        setMinimumSize(size);
        super.setSize(size);
    }
}
