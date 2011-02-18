/**
 * 
 */
package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 */
public class SwingView extends AbstractView
{
    private JFrame myFrame;
    private JMenuBar myMenuBar;
    private JPanel myCanvas;
    private JPanel myHistoryPanel;
    private JPanel myInputPanel;
    private ResourceBundle myResources;


    public SwingView ()
    {
        // set properties
        // TODO Load ResourceBundle 
        myResources = ResourceBundle.getBundle("picassa.resources.view");

        // create Frame
        myFrame = new JFrame();
        myFrame.setTitle(myResources.getString("FrameTitle"));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create GUI components
        myMenuBar = createMenuBar();
        myCanvas = createCanvas();
        myHistoryPanel = createHistoryPanel();
        myInputPanel = createInputPanel();

        // register GUI components
        //TODO myFrame.setJMenuBar(myMenuBar);
        myFrame.getContentPane().add(myCanvas, BorderLayout.NORTH);
        //TODO myFrame.getContentPane().add(myHistoryPanel, BorderLayout.CENTER);
        //TODO myFrame.getContentPane().add(myInputPanel, BorderLayout.SOUTH);
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
        // TODO Hardcoded values are bad!
        return new Canvas(myFrame, new Dimension(300, 300));
    }


    private JPanel createHistoryPanel ()
    {
        // TODO create history panel for showing past expressions
        // how about a list of HistoryPanelEntries?
        // move to separate class if necessary
        return null;
    }


    private JPanel createInputPanel ()
    {
        // TODO create panel with expression input box and "Evaluate" button
        // "Evaluate" button calls getController().evaluateExpression(inputBox.getValue())
        // move to separate class if necessary
        return null;
    }


    private JMenuBar createMenuBar ()
    {
        // TODO create menu bars for loading/saving expressions, generating new expressions, modifying parameters, etc.
        // move to separate class if necessary
        return null;
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
}
