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
     */ 
    @Override
    public void execute (Pixmap target)
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
        }
    }
}
