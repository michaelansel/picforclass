/**
 * 
 */
package picassa.model.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import picassa.model.parser.SimpleLexer;


/**
 * @author Michael Ansel
 */
public class SimpleLexerTest extends TestCase
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp () throws Exception
    {}


    /**
     * Test method for {@link grammar.SimpleLexer#tokenize(java.lang.String)}.
     */
    @Test
    public final void testTokenize ()
    {
        String expected, actual;
        expected =
            "[TokenMatch(FunctionName,\"random\"), TokenMatch(BeginGroup,\"(\"), TokenMatch(Constant,\"3\"), TokenMatch(BinaryOperator,\"+\"), TokenMatch(BeginGroup,\"(\"), TokenMatch(NegativeOperator,\"-\"), TokenMatch(Constant,\"1\"), TokenMatch(BinaryOperator,\"+\"), TokenMatch(NegativeOperator,\"-\"), TokenMatch(Constant,\"2.0\"), TokenMatch(NegativeOperator,\"-\"), TokenMatch(Constant,\"7\"), TokenMatch(EndGroup,\")\"), TokenMatch(EndGroup,\")\")]";
        actual = new SimpleLexer("random(3+(-1+-2.0-7))").tokenize().toString();
        assertEquals(expected, actual);
    }

}
