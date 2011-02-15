package tests;

import java.awt.Color;
import model.Parser;
import model.RGBColor;
import static org.junit.Assert.*;
import org.junit.*;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(Color.GRAY);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);
    
    // state
    Parser myParser;

    
    @Before
    public void setup ()
    {
        myParser = new Parser();
    }
    
    @Test
    public void testConstant ()
    {
        RGBColor actual = myParser.makeExpression("-1").evaluate();
        assertTrue(BLACK.equals(actual));
        actual = myParser.makeExpression("1").evaluate();
        assertTrue(WHITE.equals(actual));
    }
}
