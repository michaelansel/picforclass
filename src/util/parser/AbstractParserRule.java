/**
 * 
 */
package util.parser;

/**
 * @author Michael Ansel
 */
public abstract class AbstractParserRule
{
    protected AbstractParserRule myRule = null;


    public ParserResult evaluate () throws ParserException
    {
        return myRule.evaluate();
    }


    public void initializeRule ()
    {
        throw new UnsupportedOperationException("Abstract implementation not overridden");
    }


    public void setRule (AbstractParserRule rule)
    {
        myRule = rule;
    }
}
