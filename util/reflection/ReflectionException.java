package util.reflection;


/**
 * A general exception that represents all possible Java Reflection exceptions. 
 * 
 * @author Robert C. Duvall
 */
@SuppressWarnings("serial")
public final class ReflectionException extends RuntimeException
{
    /**
     * Create exception with given message
     */
	public static ReflectionException NO_CLASS = 
		new ReflectionException("There is no class, {0}");
	
	public static ReflectionException NO_DEFAULT_CONSTRUCTOR = 
		new ReflectionException("No default constructor exists for the class {0}");
	
	public static ReflectionException NO_MATCHING_CONSTRUCTOR = 
		new ReflectionException("There is no matching constructor for the class {0}");
	
	public static ReflectionException NO_MATCHING_METHOD = 
		new ReflectionException("No matching method for {0} in {1}");
	
	public static ReflectionException NO_MATCHING_FIELD = 
		new ReflectionException("Missing a field in {0}");
	
    public ReflectionException (String message, Object ... values)
    {
        super(String.format(message, values));
    }
}
