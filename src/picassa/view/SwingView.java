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
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import picassa.model.expression.Expression;
import picassa.util.Pixmap;


/**
 * @author Michael Ansel
 * @author Max Egan
 */
public class SwingView extends AbstractView
{
	private Canvas myCanvas;
	private JFrame myFrame;
	private JPanel myHistoryPanel;
	private JPanel myInputPanel;
	private JTextArea myDebugPanel;
	private JMenuBar myMenuBar;
	private ResourceBundle myResources;

	private MouseMotionListener myMouseMotionListener;
	private MouseListener myMouseListener;


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
		myFrame.getContentPane().add(createDebugPanel(), BorderLayout.WEST);
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


	private Canvas createCanvas ()
	{
		makeMouseListeners();
		// TODO Hardcoded values are bad!
		Canvas newCanvas = new Canvas(myFrame, new Dimension(300, 300));
		newCanvas.addMouseMotionListener(myMouseMotionListener);
		newCanvas.addMouseListener(myMouseListener);
		
		return newCanvas;
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

	private JScrollPane createDebugPanel ()
	{
		myDebugPanel = new JTextArea(30, 40); // rows and columns
        return new JScrollPane(myDebugPanel);
	}
	
	private void makeMouseListeners()
	{
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
		String message = "" + myCanvas.getColorAtXY(e.getX(), e.getY());
		message = message.substring(14);
        showMessage(message + " X = " + e.getX() + " , Y = " + e.getY());
        //System.out.println(myCanvas.getColorAtXY(e.getX(), e.getY()));
    }
	
	private void showMessage(String message)
	{
		myDebugPanel.append(message + "\n");
		myDebugPanel.setCaretPosition(myDebugPanel.getText().length());
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
