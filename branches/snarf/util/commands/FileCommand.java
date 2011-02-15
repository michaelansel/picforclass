package util.commands;

import javax.swing.JFileChooser;


/**
 * An abstract command that allows access to the file system,
 * sub-classes still determine what to do with the file once 
 * it is chosen.
 * 
 * @author Robert C Duvall
 */
public abstract class FileCommand<T> implements Command<T>
{
    // only one dialog box needed for an application
    private static final JFileChooser ourChooser = 
        new JFileChooser(System.getProperties().getProperty("user.dir"));
    // what kind of dialog to open, see JFileChooser constants
    private int myDialogType;


    /**
     * Create command with that will pop-up the given type of FileChooser
     */
    public FileCommand (int dialogType)
    {
        myDialogType = dialogType;
    }

    
    /**
     * Returns the file chosen by the user.
     */
    protected String getFileName ()
    {
        ourChooser.setDialogType(myDialogType);
        int response = ourChooser.showDialog(null, null);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            return ourChooser.getSelectedFile().getPath();
        }
        return null;
    }
}
