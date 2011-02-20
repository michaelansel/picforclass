/**
 * 
 */
package picassa.model.test;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.expression.AssignmentExpression;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.Expression;
import picassa.model.parser.SimpleParser;
import util.parser.ParserException;


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
        
        // unary expressions
        testParse("!(!!1+2)+!3", "(!((!(!(1.0))+2.0))+!(3.0))");

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
        
        // variables
        testParse("1+x+y+2", "(1.0+(x+(y+2.0)))");
        
        // assignment
        testParse("a=1+2", "a=(1.0+2.0)");
        
        // recursive assignment
        testParse("z=z+1", "z=(z+1.0)");
    }

    private void testParse (String expression, String expected)
        throws ParserException
    {
        Expression parsedExpression = SimpleParser.parse(expression);
        assertEquals(expected, parsedExpression.toString());
        System.out.println("===Successfully parsed "+expression+" to "+parsedExpression.toString());
        assertEquals(expected, SimpleParser.parse(parsedExpression.toString().replaceAll("\\s", "")).toString());
        
        Map<String, Expression> variables = new HashMap<String, Expression>();
        variables.put("x", new ConstantExpression(1));
        variables.put("y", new ConstantExpression(7));
        variables.put("z", new AssignmentExpression("z", new ConstantExpression(10)));
        System.out.println(parsedExpression.toString() + " => " +
                           parsedExpression.evaluate(variables));
    }

}
