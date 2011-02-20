/**
 * 
 */
package util.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public abstract class AbstractParser
{
    protected class ParserCheckpoint
    {
        public List<TokenMatch> myTokens;


        public ParserCheckpoint (List<TokenMatch> tokens)
        {
            myTokens = new ArrayList<TokenMatch>(tokens);
        }
    }

    private AbstractLexer myLexer;
    List<TokenMatch> myTokens;


    public AbstractParser (AbstractLexer lexer)
    {
        myLexer = lexer;
        myTokens = new ArrayList<TokenMatch>(lexer.tokenize());
    }


    protected TokenMatch consumeNextToken ()
    {
        return myTokens.remove(0);
    }


    protected AbstractParserRule ExactlyOne (final Object object)
    {
        if (object instanceof AbstractParserRule) return (AbstractParserRule) object;
        return new AbstractParserRule()
        {
            private Object myObject = object;


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserResult result = new ParserResult();
                if (isToken(myObject) && hasNextToken() &&
                    peekNextToken().token == myObject)
                {
                    result.add(consumeNextToken());
                    System.out.println("ExactlyOne returning: " +
                                       result.toString());
                    return result;
                }
                parseError();
                return null;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "ExactlyOneRule";
            }
        };
    }


    protected AbstractParserRule FirstOf (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            private List<AbstractParserRule> myRules =
                new ArrayList<AbstractParserRule>();
            {
                for (Object o : objects)
                    myRules.add(ExactlyOne(o));
            }


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                for (AbstractParserRule rule : myRules)
                {
                    checkpoint = storeCheckpoint();
                    System.out.println("--Testing: " + rule.toString());
                    try
                    {
                        ParserResult result = rule.evaluate();
                        System.out.println("FirstOf returning: " +
                                           result.toString());
                        return result;
                    }
                    catch (ParserException e)
                    {
                        System.out.println("--Caught! Restoring from checkpoint...");
                        restoreCheckpoint(checkpoint);
                        System.out.println("Tokens: " + myTokens.toString());
                    }
                }
                parseError();
                return null;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "FirstOfRule";
            }
        };
    }


    protected boolean hasNextToken ()
    {
        return (myTokens.size() > 0);
    }


    protected boolean isToken (Object myObject)
    {
        return (myObject instanceof IToken) &&
               myLexer.getTokens().contains((IToken) myObject);
    }


    protected AbstractParserRule OneOrMore (final Object ... objects)
    {
        return Sequence(objects, ZeroOrMore(objects));
    }


    protected AbstractParserRule Optional (final Object object)
    {
        return new AbstractParserRule()
        {
            private AbstractParserRule myRule = ExactlyOne(object);


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                checkpoint = storeCheckpoint();
                try
                {
                    result.addResult(myRule.evaluate());
                }
                catch (ParserException e)
                {
                    restoreCheckpoint(checkpoint);
                }
                System.out.println("Optional returning: " + result.toString());
                return result;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "OptionalRule";
            }
        };
    }


    protected AbstractParserRule Optional (Object ... objects)
    {
        return Optional(Sequence(objects));
    }


    protected void parseError () throws ParserException
    {
        // TODO Externalize strings, ParserException, print debug info
        throw new ParserException("Remaining Tokens: " + myTokens.toString());
    }


    protected TokenMatch peekNextToken ()
    {
        return myTokens.get(0);
    }


    protected void restoreCheckpoint (ParserCheckpoint checkpoint)
    {
        myTokens = checkpoint.myTokens;
    }


    protected AbstractParserRule Sequence (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            final List<Object> myObjects = Arrays.asList(objects);


            public ParserResult evaluate () throws ParserException
            {
                ParserResult result = new ParserResult();
                System.out.println(myObjects);
                for (Object object : myObjects)
                {
                    if (isToken(object) && hasNextToken() &&
                        peekNextToken().token == object) result.add(consumeNextToken());
                    else if (object instanceof AbstractParserRule) result.addResult(((AbstractParserRule) object).evaluate());
                    else if (object instanceof ParserResult) result.addResult((ParserResult) object); // results from a lower level, pass through untouched
                    else parseError();
                }
                System.out.println("Sequence returning: " + result.toString());
                return result;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "SequenceRule";
            }
        };
    }


    protected ParserCheckpoint storeCheckpoint ()
    {
        return new ParserCheckpoint(myTokens);
    }


    protected AbstractParserRule ZeroOrMore (final Object ... objects)
    {
        return new AbstractParserRule()
        {
            private AbstractParserRule mySequence = Sequence(objects);


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                while (true)
                {
                    checkpoint = storeCheckpoint();
                    try
                    {
                        result.addResult(mySequence.evaluate());
                    }
                    catch (ParserException e)
                    {
                        restoreCheckpoint(checkpoint);
                        break;
                    }
                }
                System.out.println("ZeroOrMore returning: " + result.toString());
                return result;
            }


            @Override
            public void setRule (AbstractParserRule rule)
            {
                throw new UnsupportedOperationException();
            }


            @Override
            public String toString ()
            {
                return "ZeroOrMoreRule";
            }
        };
    }
}
