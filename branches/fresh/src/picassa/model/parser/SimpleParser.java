/**
 * 
 */
package picassa.model.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.Expression;
import picassa.model.expression.FunctionExpression;
import picassa.model.parser.SimpleLexer.Token;
import picassa.model.parser.SimpleLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public class SimpleParser
{
    private class ParserCheckpoint
    {
        public List<TokenMatch> myTokens;


        public ParserCheckpoint (List<TokenMatch> tokens)
        {
            myTokens = tokens;
        }
    }

    public class ParserException extends Exception
    {

        public ParserException (String string)
        {
            super(string);
        }

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

    }

    protected class ParserResult
    {
        private List<Object> myObjects;
        private Expression myExpression;


        public ParserResult ()
        {
            myObjects = new ArrayList<Object>();
            myExpression = null;
        }


        public ParserResult (List<Object> objects)
        {
            myObjects = objects;
            myExpression = null;
        }


        public void add (TokenMatch token)
        {
            myObjects.add(token);
        }


        public void append (ParserResult result)
        {
            if (result.getExpression() != null) myObjects.add(result.getExpression());
            else myObjects.addAll(result.getList());
        }


        public Expression getExpression ()
        {
            return myExpression;
        }


        public List<Object> getList ()
        {
            return new ArrayList<Object>(myObjects);
        }


        public void setExpression (Expression expression)
        {
            myExpression = expression;
        }


        public String toString ()
        {
            if (myExpression != null) return String.format("ParserResult(%s)",
                                                           myExpression.toString());
            else return String.format("ParserResult(%s)", myObjects.toString());
        }
    }


    public static Expression run (List<TokenMatch> matches)
        throws ParserException
    {
        SimpleParser parser = new SimpleParser(matches);
        Expression result = parser.BinaryExpression.evaluate().getExpression();
        if (parser.hasNextToken()) parser.parseError();
        return result;
    }

    private List<TokenMatch> myTokens;

    private IParserRule BinaryExpression = new IParserRule()
    {
        IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult result = myRule.evaluate();
            if (result.getExpression() != null) return result;
            if (result.getList().size() == 1 &&
                result.getList().get(0) instanceof Expression)
            {
                result.setExpression((Expression) result.getList().get(0));
            }
            else
            {
                result.setExpression(picassa.model.expression.BinaryExpression.create(result.getList()));
            }
            return result;
        }


        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }
    };

    private IParserRule SimpleExpression = new IParserRule()
    {
        IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            return myRule.evaluate();
        }


        @Override
        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }
    };

    private IParserRule Group = new IParserRule()
    {
        IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = myRule.evaluate();
            ParserResult result = new ParserResult();
            for (Object o : subResult.getList())
                if (o instanceof Expression) result.setExpression((Expression) o);
            return result;
        }


        @Override
        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }
    };

    private IParserRule Constant = new IParserRule()
    {
        IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult result = myRule.evaluate();
            List<Object> results = result.getList();
            if (results.size() == 2 && results.get(0) instanceof TokenMatch &&
                ((TokenMatch) results.get(0)).token == Token.NegativeOperator)
            {
                TokenMatch a, b;
                a = (TokenMatch) results.remove(0);
                b = (TokenMatch) results.get(0);
                b.value = a.value + b.value;
            }
            if (results.size() != 1) parseError();
            result.setExpression(ConstantExpression.create(results));
            return result;
        }


        @Override
        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }
    };

    private IParserRule Root = new IParserRule()
    {
        private IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            return myRule.evaluate();
        }


        @Override
        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }

    };

    private IParserRule Function = new IParserRule()
    {
        private IParserRule myRule = null;


        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult result = myRule.evaluate();
            List<Object> results = result.getList();
            TokenMatch functionName = (TokenMatch) results.remove(0);
            results.remove(0); // BeginGroup
            results.remove(results.size() - 1); // EndGroup
            List<Expression> parameters = new ArrayList<Expression>();
            for (Object o : results)
            {
                if (o instanceof TokenMatch &&
                    ((TokenMatch) o).token == Token.Delimiter) continue;
                else if (o instanceof Expression) parameters.add((Expression) o);
                else parseError();
            }
            result.setExpression(FunctionExpression.create(functionName.value,
                                                           parameters));
            return result;
        }


        @Override
        public void setRule (IParserRule rule)
        {
            myRule = rule;
        }

    };


    public SimpleParser (List<TokenMatch> matches)
    {
        myTokens = new ArrayList<TokenMatch>(matches);

        Root.setRule(BinaryExpression);
        BinaryExpression.setRule(Sequence(SimpleExpression,
                                          ZeroOrMore(FirstOf(SimpleLexer.Token.BinaryOperator,
                                                             SimpleLexer.Token.NegativeOperator),
                                                     SimpleExpression)));
        SimpleExpression.setRule(FirstOf(Function, Group, Constant));
        Function.setRule(Sequence(SimpleLexer.Token.FunctionName,
                                  SimpleLexer.Token.BeginGroup,
                                  Root,
                                  ZeroOrMore(SimpleLexer.Token.Delimiter, Root),
                                  SimpleLexer.Token.EndGroup));
        Group.setRule(Sequence(SimpleLexer.Token.BeginGroup,
                               BinaryExpression,
                               SimpleLexer.Token.EndGroup));
        Constant.setRule(Sequence(Optional(SimpleLexer.Token.NegativeOperator),
                                  SimpleLexer.Token.Constant));
//        Constant.setRule( FirstOf( Sequence(SimpleLexer.Token.NegativeOperator, SimpleLexer.Token.Constant), SimpleLexer.Token.Constant));
    }


    private TokenMatch consumeNextToken ()
    {
        return myTokens.remove(0);
    }


    protected IParserRule ExactlyOne (final Object object)
    {
        if (object instanceof IParserRule) return (IParserRule) object;
        return new IParserRule()
        {
            private Object myObject = object;


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserResult result = new ParserResult();
                if (myObject instanceof SimpleLexer.Token && hasNextToken() &&
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
            public void setRule (IParserRule rule)
            {
                throw new UnsupportedOperationException();
            }
        };
    }


    protected IParserRule FirstOf (final Object ... objects)
    {
        return new IParserRule()
        {
            private List<IParserRule> myRules = new ArrayList<IParserRule>();
            {
                for (Object o : objects)
                    myRules.add(ExactlyOne(o));
            }


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                for (IParserRule rule : myRules)
                {
                    checkpoint = storeCheckpoint();
                    try
                    {
                        result.append(rule.evaluate());
                        System.out.println("FirstOf returning: " +
                                           result.toString());
                        return result;
                    }
                    catch (ParserException e)
                    {
                        restoreCheckpoint(checkpoint);
                    }
                }
                parseError();
                return null;
            }


            @Override
            public void setRule (IParserRule rule)
            {
                throw new UnsupportedOperationException();
            }
        };
    }


    private boolean hasNextToken ()
    {
        return (myTokens.size() > 0);
    }


    protected IParserRule OneOrMore (final Object ... objects)
    {
        return Sequence(objects, ZeroOrMore(objects));
    }


    protected Object Optional (final Object object)
    {
        return new IParserRule()
        {
            private IParserRule myRule = ExactlyOne(object);


            @Override
            public ParserResult evaluate () throws ParserException
            {
                ParserCheckpoint checkpoint = null;
                ParserResult result = new ParserResult();
                checkpoint = storeCheckpoint();
                try
                {
                    result.append(myRule.evaluate());
                }
                catch (ParserException e)
                {
                    restoreCheckpoint(checkpoint);
                }
                System.out.println("Optional returning: " + result.toString());
                return result;
            }


            @Override
            public void setRule (IParserRule rule)
            {
                throw new UnsupportedOperationException();
            }
        };
    }


    private void parseError () throws ParserException
    {
        // TODO Externalize strings, ParserException, print debug info
        throw new ParserException("Remaining Tokens: " + myTokens.toString());
    }


    private TokenMatch peekNextToken ()
    {
        return myTokens.get(0);
    }


    protected void restoreCheckpoint (ParserCheckpoint checkpoint)
    {
        myTokens = checkpoint.myTokens;
    }


    protected IParserRule Sequence (final Object ... objects)
    {
        return new IParserRule()
        {
            final List<Object> myObjects = Arrays.asList(objects);


            public ParserResult evaluate () throws ParserException
            {
                ParserResult result = new ParserResult();
                System.out.println(myObjects);
                for (Object object : myObjects)
                {
                    if (object instanceof SimpleLexer.Token && hasNextToken() &&
                        peekNextToken().token == object) result.add(consumeNextToken());
                    else if (object instanceof IParserRule) result.append(((IParserRule) object).evaluate());
                    else if (object instanceof ParserResult) result.append((ParserResult) object); // results from a lower level, pass through untouched
                    else parseError();
                }
                System.out.println("Sequence returning: " + result.toString());
                return result;
            }


            @Override
            public void setRule (IParserRule rule)
            {
                throw new UnsupportedOperationException();
            }
        };
    }


    protected ParserCheckpoint storeCheckpoint ()
    {
        return new ParserCheckpoint(myTokens);
    }


    protected IParserRule ZeroOrMore (final Object ... objects)
    {
        return new IParserRule()
        {
            private IParserRule mySequence = Sequence(objects);


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
                        result.append(mySequence.evaluate());
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
            public void setRule (IParserRule rule)
            {
                throw new UnsupportedOperationException();
            }
        };
    }
}
