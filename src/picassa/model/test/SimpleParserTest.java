/**
 * 
 */
package picassa.model.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.expression.Expression;
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
        testParse("1+2", "(1.0+2.0)");

        // serial binary expressions
        testParse("(1+2)+3", "((1.0+2.0)+3.0)");

        // negatives
        testParse("(-1+2)+-3", "((-1.0+2.0)+-3.0)");

        // vectors
        testParse("([-1,2,3.0]+[2,-1,3])+[3,-2.0,1]",
                  "(([-1.0,2.0,3.0]+[2.0,-1.0,3.0])+[3.0,-2.0,1.0])");

        // functions
        testParse("abs(3+(-1+-2.0+7))", "abs((3.0+(-1.0+(-2.0+7.0))))");

        // nested functions
        testParse("3+abs(-10+-2.0+7)+9", "(3.0+(abs((-10.0+(-2.0+7.0)))+9.0))");

        // no-arg functions
        testParse("[1,2,3]+random()+[7,8,9]",
                  "([1.0,2.0,3.0]+(random()+[7.0,8.0,9.0]))");

        // var-arg functions
        testParse("[1,2,3]+sum([1,2,3],random(),sum([1,2,3],[1,2,3],[1,2,3]))+[7,8,9]",
                  "([1.0,2.0,3.0]+(sum([1.0,2.0,3.0], random(), sum([1.0,2.0,3.0], [1.0,2.0,3.0], [1.0,2.0,3.0]))+[7.0,8.0,9.0]))");
    }


    private void testParse (String expression, String expected)
        throws ParserException
    {
        Expression parsedExpression = SimpleParser.parse(expression);
        assertEquals(expected, parsedExpression.toString());
        System.out.println(parsedExpression.toString() + " => " +
                           parsedExpression.evaluate(null));
    }

}