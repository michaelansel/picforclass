/**
 * 
 */
package util.parser;

import java.util.regex.Pattern;
import util.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public interface IToken
{
    Pattern getPattern ();


    TokenMatch makeToken (String value);
}
