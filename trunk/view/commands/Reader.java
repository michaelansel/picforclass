package view.commands;

import java.io.IOException;

import javax.swing.JFileChooser;
import util.commands.FileCommand;
import model.Pixmap;


/**
 * Open a file for display as the current pixmap.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap>
{
    public Reader ()
    {
        super(JFileChooser.OPEN_DIALOG);
    }


    /**
     * Open a file for display as the current pixmap.
     * @throws ViewCommandsException 
     */ 
    @Override
    public void execute (Pixmap target) throws ViewCommandsException
    {
        try
        {
            String fileName = getFileName();
            if (fileName != null)
            {
                target.read(fileName);
            }
        }
        catch (IOException e)
        {
            // BUGBUG: do something, file could not be opened
        	throw ViewCommandsException.BAD_READ_FILE;
        }
    }
}
