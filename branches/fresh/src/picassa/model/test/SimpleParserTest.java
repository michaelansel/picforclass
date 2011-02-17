/**
 * 
 */
package picassa.model.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.parser.ParserException;
import picassa.model.parser.SimpleParser;


/**
 * @author Michael Ansel
 */
public class SimpleParserTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for
     * {@link picassa.model.parser.SimpleParser#parse(java.lang.String)}.
     * 
     * @throws ParserException
     */
    @Test
    public final void testParse () throws ParserException
    {
        // simple
        testParse("1+2",
                  "(1.0+2.0)");
        
        // serial binary expressions
        testParse("(1+2)+3",
                  "((1.0+2.0)+3.0)");
        
        // negatives
        testParse("(-1+2)+-3",
                  "((-1.0+2.0)+-3.0)");
        
        // functions
        testParse("random(3+(-1+-2.0+7))",
                  "random((3.0+(-1.0+(-2.0+7.0))))");
        
        // nested functions
        testParse("random(3+abs(-10+-2.0+7)+9)",
                  "random((3.0+(abs((-10.0+(-2.0+7.0)))+9.0)))");
    }


    private void testParse (String expression, String expected)
        throws ParserException
    {
        assertEquals(expected, SimpleParser.parse(expression).toString());
    }

}
