/**
 * 
 */
package picassa.model.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.expression.Expression;
import picassa.model.parser.AbstractParser;
import picassa.model.parser.SimpleLexer;
import picassa.model.parser.SimpleParser;
import picassa.model.parser.SimpleParser.ParserException;


/**
 * @author Michael Ansel
 */
public class ParserTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for {@link picassa.model.parser.ExpressionParser#run()}.
     */
    @Test
    public final void testRun ()
    {
        try
        {
            Expression.printExpressionTree(SimpleParser.run(SimpleLexer.tokenize("(123 + 456)")));
        }
        catch (ParserException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail(e.toString());
        }
    }

}
