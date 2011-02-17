/**
 * 
 */
package picassa.model.parser;

import java.util.regex.Pattern;
import picassa.model.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public interface IToken
{
    Pattern getPattern ();


    TokenMatch makeToken (String value);
}
