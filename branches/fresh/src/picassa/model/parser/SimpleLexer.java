/**
 * 
 */
package picassa.model.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael Ansel
 *
 */
public class SimpleLexer
{
    /**
     * Set of Tokens valid immediately prior to a binary operator
     */
    protected static final Set<Token> ValidBinaryOperatorPredecessorTokens = new HashSet<Token>();
    static{
        Token[] tokens = {Token.EndGroup, Token.Constant};
        ValidBinaryOperatorPredecessorTokens.addAll(Arrays.asList(tokens));
    }
    enum Token
    {
        BinaryOperator("[+]"),
        NegativeOperator("[-]"),  // this needs to be handled differently from other BinaryOperators
        BeginGroup("[(]"),
        EndGroup("[)]"),
        BeginVector("[\\[]"),
        EndVector("[\\]]"),
        Delimiter("[,]"),
        FunctionName("(random)|(floor)"),
        Variable("[a-zA-Z]+"),
        Constant("[0-9]+([.][0-9]+)?")
        ;

        private final String myRegex;
        private Pattern myPattern;
        
        Token(final String regex)
        {
            myRegex = regex;
            myPattern = Pattern.compile(String.format("\\A(%s).*\\z", myRegex));
        }
        
        Pattern getPattern()
        {
            return myPattern;
        }
        
        TokenMatch makeToken(String value)
        {
            return new TokenMatch(this, value);
        }
    }
    public static class TokenMatch
    {
        public Token token;
        public String value;
        TokenMatch(Token t, String v)
        {
            token = t;
            value = v;
        }
        
        @Override
        public String toString()
        {
            return String.format("TokenMatch(%s,\"%s\")", token.toString(), value);
        }
    }
    
    public static List<TokenMatch> tokenize(String input)
    {
        StringBuilder remainder = new StringBuilder(input);
        List<TokenMatch> results = new ArrayList<TokenMatch>();
        while(remainder.length()>0)
        {
            List<TokenMatch> matches = new ArrayList<TokenMatch>();
            for(Token t : Token.values())
            {
                Matcher matcher = t.getPattern().matcher(remainder.toString());
//                System.out.println("Testing \""+remainder.toString()+"\" against pattern: "+matcher.pattern().pattern());
                if(!matcher.matches())
                    continue;
                matches.add(t.makeToken(matcher.group(1)));
            }
            
            if(matches.size() == 0)
                throw new RuntimeException("No matching tokens found!\nRemainder: "+remainder.toString());
            
            TokenMatch bestMatch = matches.get(0);
            for(TokenMatch match : matches)
                if(match.value.length() > bestMatch.value.length())
                    bestMatch = match;
            
            results.add(bestMatch);
            remainder.delete(0, bestMatch.value.length());
        }
        return results;
    }
}
