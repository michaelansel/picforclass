/**
 * 
 */
package picassa.model.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.expression.Expression;
import picassa.model.parser.AbstractParser;
import picassa.model.parser.ExpressionParser;


/**
 * @author Michael
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
            Expression.printExpressionTree(AbstractParser.runParser(ExpressionParser.class,
                                                                    "(123 + 456)"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.toString());
        }
    }

}
