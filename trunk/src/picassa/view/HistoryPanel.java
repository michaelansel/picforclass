package picassa.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Andrea Scripa
 */

public class HistoryPanel extends JComponent
{
    private Dimension thumbnailSize;
    public HistoryPanel (JFrame container, Dimension size)
    {
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));
        JScrollPane scrollpane =
            new JScrollPane(historyPanel,
                            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        setPreferredSize(size);
        thumbnailSize = new Dimension((size.height-40), (size.height-40));
    }

}
