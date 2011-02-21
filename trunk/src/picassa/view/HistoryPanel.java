package picassa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;


/**
 * @author Andrea Scripa
 */

public class HistoryPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private Dimension thumbnailSize;
    private int historyLength = 10;
    private JFrame myContainer;


    public HistoryPanel (JFrame container, Dimension size)
    {
        super(new BorderLayout());
        myContainer = container;
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));
        JScrollPane scrollpane =
            new JScrollPane(historyPanel,
                            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollpane, BorderLayout.SOUTH);
        setPreferredSize(size);
        thumbnailSize = new Dimension((size.height - 40), (size.height - 40));

        addThumbnails(this);
    }


    private void addThumbnails (HistoryPanel historyPanel)
    {
        ResourceBundle myResources =
            ResourceBundle.getBundle("picassa.resources.view");

        historyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        for (int k = 0; k < historyLength; k++)
        {
            String historyNumber = "History" + k;
            Thumbnail thumbnail = createThumbnail();
            historyPanel.add(thumbnail);
            historyPanel.add(Box.createRigidArea(new Dimension(5, 0)));

            TitledBorder title =
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                                                 myResources.getString(historyNumber));
            title.setTitleJustification(TitledBorder.CENTER);
            title.setTitlePosition(TitledBorder.BOTTOM);
            thumbnail.setBorder(title);
        }

    }


    private Thumbnail createThumbnail ()
    {
        Thumbnail currentThumbnail = new Thumbnail(myContainer, thumbnailSize);
        return currentThumbnail;
    }

}
