package util.commands;


/**
 * An abstract command class that operates on some object.
 * 
 * @author Robert C Duvall
 */
public interface Command<T>
{
    /**
     * Subclasses determine how to update the given object
     */
    public void execute (T target);
}
