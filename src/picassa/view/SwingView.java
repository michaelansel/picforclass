/**
 * 
 */
package picassa.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel and Andrea Scripa
 */
public class SwingView extends AbstractView
{
    private JPanel myCanvas;
    private JFrame myFrame;
    private HistoryPanel myHistoryPanel;
    private InputPanel myInputPanel;
    private ToolBar myToolBar;
    private ResourceBundle myResources;

    private Dimension myCanvasSize = new Dimension(500, 500);
    private Dimension myToolBarSize = new Dimension(myCanvasSize.width, 30);
    private Dimension myHistoryPanelSize = new Dimension(myCanvasSize.width, 140);
    private Dimension myInputPanelSize = new Dimension(myCanvasSize.width, 30);
    
    public SwingView ()
    {
        // set properties
        myResources = ResourceBundle.getBundle("picassa.resources.view");

        // create Frame
        myFrame = new JFrame();
        myFrame.setTitle(myResources.getString("FrameTitle"));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create GUI components
        myToolBar = createToolBar();
        myCanvas = createCanvas();
        myHistoryPanel = createHistoryPanel();
        myInputPanel = createInputPanel();

        // register GUI components
        myFrame.getContentPane().setLayout(new BoxLayout(myFrame.getContentPane(), 
                                                         BoxLayout.Y_AXIS));
        myFrame.getContentPane().add(myToolBar);
        myFrame.getContentPane().add(myCanvas);
        myFrame.getContentPane().add(myHistoryPanel);
        myFrame.getContentPane().add(myInputPanel);
        myFrame.pack();

        // show it!
        myFrame.setVisible(true);
    }


    @Override
    public void addExpressionToHistory (Expression expression)
    {
        // TODO Implement SwingView.addExpressionToHistory
        throw new UnsupportedOperationException("unimplemented functionality");
    }


    private JPanel createCanvas ()
    {
        return new Canvas(myFrame, myCanvasSize);
    }


    private HistoryPanel createHistoryPanel ()
    {
        // TODO create history panel for showing past expressions
        // how about a list of HistoryPanelEntries?
        // move to separate class if necessary
        
        return new HistoryPanel(myFrame, myHistoryPanelSize);
        //return null;
    }


    private InputPanel createInputPanel ()
    {
        // TODO create panel with expression input box and "Evaluate" button
        // "Evaluate" button calls getController().evaluateExpression(inputBox.getValue())
        // move to separate class if necessary
        InputPanel panel = new InputPanel(myFrame, myInputPanelSize, this);
        return panel;
    }


    private ToolBar createToolBar ()
    {
        return new ToolBar(myFrame, myToolBarSize, this);
    }
    
    @Override
    public Pixmap getDisplay ()
    {
        return ((Canvas) myCanvas).getPixmap();
    }


    @Override
    public void updateDisplay (Pixmap image)
    {
        ((Canvas) myCanvas).setPixmap(image);
    }
    
    public String getUserExpression()
    {
        return myInputPanel.getUserExpression();
    }
}
