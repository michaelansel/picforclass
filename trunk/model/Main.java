package model;
import view.Frame;


/**
 * @author Robert Duvall (rcd@cs.duke.edu)
 */
public class Main
{
    public static void main (String[] args)
    {
        Frame view = new Frame(new Model());
        view.setVisible(true);
    }
}
