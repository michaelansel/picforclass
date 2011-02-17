/**
 * 
 */
package picassa.model.parser;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael Ansel
 *
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
        expected = "[TokenMatch(FunctionName,\"random\"), TokenMatch(BeginGroup,\"(\"), TokenMatch(Constant,\"3\"), TokenMatch(BinaryOperator,\"+\"), TokenMatch(BeginGroup,\"(\"), TokenMatch(BinaryOperator,\"-\"), TokenMatch(Constant,\"1\"), TokenMatch(BinaryOperator,\"+\"), TokenMatch(BinaryOperator,\"-\"), TokenMatch(Constant,\"2.0\"), TokenMatch(BinaryOperator,\"-\"), TokenMatch(Constant,\"7\"), TokenMatch(EndGroup,\")\"), TokenMatch(EndGroup,\")\")]";
        actual = SimpleLexer.tokenize("random(3+(-1+-2.0-7))").toString();
        assertEquals(expected, actual);
    }

}
