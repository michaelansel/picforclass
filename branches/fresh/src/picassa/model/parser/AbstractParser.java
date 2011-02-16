/**
 * 
 */
package picassa.model.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public abstract class AbstractParser
{
    /**
     * All parsers must define a root production
     */
    public abstract Expression run () throws /* Parse */Exception;


    public static Expression runParser (Class<? extends AbstractParser> parserClass,
                                        String input)
        throws /* Parse */Exception
    {
        InputStream stream = new ByteArrayInputStream(input.getBytes());
        AbstractParser parser = newParser(parserClass, stream);
        return parser.run();
    }


    private static AbstractParser newParser (Class<? extends AbstractParser> parserClass,
                                             InputStream stream)
    {
        try
        {
            Constructor<? extends AbstractParser> konstructor =
                parserClass.getConstructor(InputStream.class);
            return konstructor.newInstance(stream);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Invalid parser: must have InputStream constructor");
        }
        catch (InstantiationException e)
        {
            initError(e);
        }
        catch (IllegalAccessException e)
        {
            initError(e);
        }
        catch (InvocationTargetException e)
        {
            initError(e);
        }
        return null;
    }


    /**
     * @param e
     */
    private static void initError (Exception e)
    {
        e.printStackTrace();
        throw new RuntimeException("Error initializing parser: " + e.toString());
    }
}
