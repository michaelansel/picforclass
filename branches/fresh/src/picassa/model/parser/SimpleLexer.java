/**
 * 
 */
package picassa.model.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.FunctionExpression;


/**
 * @author Michael Ansel
 */
public class SimpleLexer extends AbstractLexer
{
    public enum Token implements IToken
    {
        BinaryOperator("[+]"), NegativeOperator("[-]"), // this needs to be handled differently from other BinaryOperators
        BeginGroup("[(]"),
        EndGroup("[)]"),
        BeginVector("[\\[]"),
        EndVector("[\\]]"),
        Delimiter("[,]"),
        FunctionName(FunctionExpression.TOKEN_REGEX),
        Variable("[a-zA-Z]+"),
        Constant(ConstantExpression.TOKEN_REGEX);

        private final String myRegex;
        private Pattern myPattern;


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

    /**
     * Set of Tokens valid immediately prior to a binary operator
     */
    protected static final Set<Token> ValidBinaryOperatorPredecessorTokens =
        new HashSet<Token>();
    static
    {
        Token[] tokens =
            { Token.EndVector, Token.EndGroup, Token.Variable, Token.Constant };
        ValidBinaryOperatorPredecessorTokens.addAll(Arrays.asList(tokens));
    }


    public SimpleLexer (String input)
    {
        super(input);
        setTokens(Token.values());
    }
}
