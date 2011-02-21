/**
 * 
 */
package picassa.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 * @author Andrea Scripa
 * @author Max Egan
 */
public class SwingView extends AbstractView
{
    private JPanel myCanvas;
    private JFrame myFrame;
    private HistoryPanel myHistoryPanel;
    private InputPanel myInputPanel;
    private JTextField myDebugPanel;
    private ToolBar myToolBar;
    private ResourceBundle myResources;

    private Dimension myCanvasSize = new Dimension(500, 500);
    private Dimension myToolBarSize = new Dimension(myCanvasSize.width, 30);
    private Dimension myHistoryPanelSize =
        new Dimension(myCanvasSize.width, 140);
    private Dimension myInputPanelSize = new Dimension(myCanvasSize.width, 30);

    private MouseMotionListener myMouseMotionListener;
	private MouseListener myMouseListener;

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
        myFrame.getContentPane()
               .setLayout(new BoxLayout(myFrame.getContentPane(),
                                        BoxLayout.Y_AXIS));
        myFrame.getContentPane().add(myToolBar);
        myFrame.getContentPane().add(myCanvas);
        myFrame.getContentPane().add(myHistoryPanel);
        myFrame.getContentPane().add(myInputPanel);
        myFrame.getContentPane().add(createDebugPanel());
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
    	makeMouseListeners();
		// TODO Hardcoded values are bad!
		Canvas newCanvas = new Canvas(myFrame, myCanvasSize);
		newCanvas.addMouseMotionListener(myMouseMotionListener);
		newCanvas.addMouseListener(myMouseListener);
		
		return newCanvas;
    }

    private JScrollPane createDebugPanel ()
	{
		myDebugPanel = new JTextField("",40); // rows and columns
		myDebugPanel.setEditable(false);
        return new JScrollPane(myDebugPanel);
	}
	
	private void makeMouseListeners()
	{
		myMouseMotionListener = new MouseMotionListener()
		{

			@Override
			public void mouseDragged(MouseEvent e) {
				echo("drag", e);
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				echo("move", e);
				
			}
		};
		
		myMouseListener = new MouseListener()
		{
			public void mouseClicked (MouseEvent e)
            {
                echo("clicked", e);
            }
            public void mouseEntered (MouseEvent e)
            {
                echo("enter", e);
            }
            public void mouseExited (MouseEvent e)
            {
                echo("exit", e);
            }
            public void mousePressed (MouseEvent e)
            {
                echo("pressed", e);
            }
            public void mouseReleased (MouseEvent e)
            {
                echo("released", e);
            }
		};
	}
	
	private void echo (String s, MouseEvent e)
    {
		String message = "" + ((Canvas) myCanvas).getColorAtXY(e.getX(), e.getY());
		message = message.substring(14);
        showMessage(message + " X = " + e.getX() + " , Y = " + e.getY());
        //System.out.println(myCanvas.getColorAtXY(e.getX(), e.getY()));
    }
	
	private void showMessage(String message)
	{
		myDebugPanel.setText(message);
		myDebugPanel.setCaretPosition(myDebugPanel.getText().length());
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


    public String getUserExpression ()
    {
        return myInputPanel.getUserExpression();
    }
}
