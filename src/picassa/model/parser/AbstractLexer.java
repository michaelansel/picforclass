/**
 * 
 */
package picassa.model.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;


/**
 * @author Michael Ansel
 */
public abstract class AbstractLexer
{

    public static class TokenMatch
    {
        public IToken token;
        public String value;


        TokenMatch (IToken t, String v)
        {
            token = t;
            value = v;
        }


        @Override
        public String toString ()
        {
            return String.format("TokenMatch(%s,\"%s\")",
                                 token.toString(),
                                 value);
        }
    }

    private static Collection<IToken> myTokens;

    private String myInput;


    public AbstractLexer (String input)
    {
        myInput = input;
    }


    public Collection<IToken> getTokens ()
    {
        return myTokens;
    }


    protected void setTokens (IToken[] tokens)
    {
        myTokens = Arrays.asList(tokens);
    }


    public List<TokenMatch> tokenize ()
    {
        StringBuilder remainder = new StringBuilder(myInput);
        List<TokenMatch> results = new ArrayList<TokenMatch>();
        while (remainder.length() > 0)
        {
            List<TokenMatch> matches = new ArrayList<TokenMatch>();
            for (IToken t : myTokens)
            {
                Matcher matcher = t.getPattern().matcher(remainder.toString());
                //                System.out.println("Testing \""+remainder.toString()+"\" against pattern: "+matcher.pattern().pattern());
                if (!matcher.matches()) continue;
                matches.add(t.makeToken(matcher.group(1)));
            }

            if (matches.size() == 0) throw new RuntimeException("No matching tokens found!\nRemainder: " +
                                                                remainder.toString());

            TokenMatch bestMatch = matches.get(0);
            for (TokenMatch match : matches)
                if (match.value.length() > bestMatch.value.length()) bestMatch =
                    match;

            results.add(bestMatch);
            remainder.delete(0, bestMatch.value.length());
        }
        return results;
    }
}
