/**
 * 
 */
package picassa.model.parser;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import picassa.model.parser.SimpleParser.ParserException;

/**
 * @author Michael
 *
 */
public class SimpleParserTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for {@link picassa.model.parser.SimpleParser#run(java.util.List)}.
     * @throws ParserException 
     */
    @Test
    public final void testRun () throws ParserException
    {
        String expected, actual, expression;
        
        expression = "1+2";
        actual = SimpleParser.run(SimpleLexer.tokenize(expression)).toString();
        System.out.println(actual);
        
        expression = "(1+2)+3";
        actual = SimpleParser.run(SimpleLexer.tokenize(expression)).toString();
        System.out.println(actual);

        expression = "(-1+2)+-3";
        actual = SimpleParser.run(SimpleLexer.tokenize(expression)).toString();
        System.out.println(actual);
        
        expression = "random(3+(-1+-2.0+7))";
        actual = SimpleParser.run(SimpleLexer.tokenize(expression)).toString();
        System.out.println(actual);
    }

}
