package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import picassa.util.Pixmap;


/**
 * @author Andrea Scripa
 */

public class HistoryPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Dimension thumbnailSize;
    private int historyLength = 10;


    public HistoryPanel (JFrame container, Dimension size)
    {
        super(new BorderLayout());
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));
        JScrollPane scrollpane =
            new JScrollPane(historyPanel,
                            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollpane);
        setPreferredSize(size);
        thumbnailSize = new Dimension((size.height - 40), (size.height - 40));
        
        //addThumbnails(this);
    }
    
    private void addThumbnails (HistoryPanel historyPanel)
    {
        ResourceBundle myResources =
            ResourceBundle.getBundle("picassa.resources.view");
        
        for (int k=0; k<historyLength; k++)
        {
            String historyNumber = "History" + (k+1);
            Pixmap thumbnail = createThumbnail(myResources.getString(historyNumber));
            //historyPanel.add(thumbnail);
        }
        
    }

    private Pixmap createThumbnail (String historyNumber)
    {
        Pixmap currentThumbnail = new Pixmap(thumbnailSize);
        return currentThumbnail;
    }

}
