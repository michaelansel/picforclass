package view.commands;
import java.io.FileNotFoundException;

/**
 * Represents an exceptional situation specific to this project.
 */
@SuppressWarnings("serial")
public class ViewCommandsException extends FileNotFoundException
{
	public static ViewCommandsException BAD_WRITE_FILE = 
		new ViewCommandsException("The requested write file could not be opened.");
	
	public static ViewCommandsException BAD_READ_FILE = 
		new ViewCommandsException("The requested read file could not be opened.");
	
	/**
	* Create exception with given message
	*/
	public ViewCommandsException (String message)
	{
	    super(message);
	}
}

