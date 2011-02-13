import java.awt.Dimension;
import java.util.ResourceBundle;

import model.Model;
import view.Frame;


/**
 * 
 * @author Robert Duvall (rcd@cs.duke.edu)
 */
public class Main
{
    public static final Dimension SIZE = new Dimension(400, 400);
    public static final Dimension DEBUG = new Dimension(100,400);
    public static final String language = "english";
    private static ResourceBundle myResources; 


    public static void main (String[] args)
    {
    	myResources = ResourceBundle.getBundle("resources." + language);
        Model model = new Model();
        Frame view = new Frame(myResources.getString("Title"), SIZE);
        Frame debug = new Frame(myResources.getString("Debug"), DEBUG);
        view.setModel(model);
        view.setVisible(true);
        
        
    }
}
