package view.commands;

import java.io.IOException;
import javax.swing.JFileChooser;
import util.commands.FileCommand;
import model.Pixmap;


/**
 * Save the current image to a chosen file.
 * 
 * @author Robert C Duvall
 */
public class Writer extends FileCommand<Pixmap>
{
    public Writer ()
    {
        super(JFileChooser.SAVE_DIALOG);
    }


    /**
     * Save the current image to a chosen file.
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
				target.write(fileName);
			}
        }
        catch (IOException e)
        {
            // BUGBUG: do something, file could not be opened
        	throw ViewCommandsException.BAD_WRITE_FILE;
        }
    }
}
