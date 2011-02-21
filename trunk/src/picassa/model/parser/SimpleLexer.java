/**
 * 
 */
package picassa.model.parser;

import java.util.regex.Pattern;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.function.FunctionExpression;
import util.parser.AbstractLexer;
import util.parser.IToken;


/**
 * @author Michael Ansel
 */
public class SimpleLexer extends AbstractLexer
{
    public enum Token implements IToken
    {
        AssignmentOperator("[=]"), BeginGroup("[(]"),
        BeginVector("[\\[]"),
        BinaryOperator("[+*/%^]"),
        Constant(ConstantExpression.TOKEN_REGEX),
        Delimiter("[,]"),
        EndGroup("[)]"),
        EndVector("[\\]]"),
        FunctionName(FunctionExpression.TOKEN_REGEX),
        NegativeOperator("[-]"), // this needs to be handled differently from other BinaryOperators
        UnaryOperator("[!]"),
        Variable("([$][0-9]+)|([a-zA-Z]+)");

        private Pattern myPattern;
        private final String myRegex;


        Token (final String regex)
        {
            myRegex = regex;
            myPattern = Pattern.compile(String.format("\\A(%s).*\\z", myRegex));
        }


        @Override
        public Pattern getPattern ()
        {
            return myPattern;
        }


        @Override
        public TokenMatch makeToken (String value)
        {
            return new TokenMatch(this, value);
        }
    }


    public SimpleLexer (String input)
    {
        super(input);
        setTokens(Token.values());
    }
}
