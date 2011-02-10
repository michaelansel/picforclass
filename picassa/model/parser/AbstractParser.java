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
 *
 */
public abstract class AbstractParser
{
    /**
     * All parsers must define a root production
     */
    public abstract Expression Root() throws /*Parse*/Exception;

    public static Expression runParser(Class<? extends AbstractParser> parserClass, String query) throws /*Parse*/Exception
    {
      InputStream stream = new ByteArrayInputStream(query.getBytes());
      AbstractParser parser = newParser(parserClass,stream);
      return parser.Root();
    }

    private static AbstractParser newParser (Class<? extends AbstractParser> parserClass, InputStream stream)
    {
        try
        {
            Constructor<? extends AbstractParser> konstructor = parserClass.getConstructor(InputStream.class);
            return konstructor.newInstance(stream);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
            throw new RuntimeException(Messages.getString("AbstractParser.ErrorNeedInputStream")); //$NON-NLS-1$
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
        throw new RuntimeException(Messages.getString("AbstractParser.ErrorInitializing")+e.toString()); //$NON-NLS-1$
    }
}
