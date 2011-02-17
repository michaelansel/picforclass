/**
 * 
 */
package picassa.model.parser;

import picassa.model.parser.SimpleParser.ParserException;
import picassa.model.parser.SimpleParser.ParserResult;

/**
 * @author Michael Ansel
 *
 */
public interface IParserRule
{
    public ParserResult evaluate() throws ParserException;

    public void setRule (IParserRule rule);
}
