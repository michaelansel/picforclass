package util.commands;

import view.commands.ViewCommandsException;


/**
 * An abstract command class that operates on some object.
 * 
 * @author Robert C Duvall
 */
public interface Command<T>
{
    /**
     * Subclasses determine how to update the given object
     * @throws ViewCommandsException 
     */
    public void execute (T target) throws ViewCommandsException;
}
