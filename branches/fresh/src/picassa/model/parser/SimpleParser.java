/**
 * 
 */
package picassa.model.parser;

import java.util.ArrayList;
import java.util.List;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.Expression;
import picassa.model.expression.FunctionExpression;
import picassa.model.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public class SimpleParser extends AbstractParser
{
    public static Expression parse (String input) throws ParserException
    {
        SimpleParser parser = new SimpleParser(new SimpleLexer(input));
        Expression result = parser.Root.evaluate().getExpression();
        if (parser.hasNextToken()) parser.parseError();
        return result;
    }

    private AbstractParserRule BinaryExpression = new AbstractParserRule()
    {
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


        @Override
        public void initializeRule ()
        {
            setRule(Sequence(SimpleExpression,
                             ZeroOrMore(FirstOf(SimpleLexer.Token.BinaryOperator,
                                                SimpleLexer.Token.NegativeOperator),
                                        SimpleExpression)));
        }
    };

    private AbstractParserRule SimpleExpression = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(FirstOf(Function, Group, Constant));
        }
    };

    private AbstractParserRule Group = new AbstractParserRule()
    {
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
        public void initializeRule ()
        {
            setRule(Sequence(SimpleLexer.Token.BeginGroup,
                             BinaryExpression,
                             SimpleLexer.Token.EndGroup));
        }
    };

    private AbstractParserRule Constant = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult result = myRule.evaluate();
            List<Object> results = result.getList();
            if (results.size() == 2 &&
                results.get(0) instanceof TokenMatch &&
                ((TokenMatch) results.get(0)).token == SimpleLexer.Token.NegativeOperator)
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
        public void initializeRule ()
        {
            setRule(Sequence(Optional(SimpleLexer.Token.NegativeOperator),
                             SimpleLexer.Token.Constant));
            // Alternative Implementation
            // setRule( FirstOf( Sequence(SimpleLexer.Token.NegativeOperator, SimpleLexer.Token.Constant), SimpleLexer.Token.Constant));

        }
    };

    private AbstractParserRule Root = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(BinaryExpression);
        }
    };

    private AbstractParserRule Function = new AbstractParserRule()
    {
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
                    ((TokenMatch) o).token == SimpleLexer.Token.Delimiter) continue;
                else if (o instanceof Expression) parameters.add((Expression) o);
                else parseError();
            }
            result.setExpression(new FunctionExpression(functionName.value,
                                                        parameters));
            return result;
        }


        @Override
        public void initializeRule ()
        {
            setRule(Sequence(SimpleLexer.Token.FunctionName,
                             SimpleLexer.Token.BeginGroup,
                             Optional(Root,
                                      ZeroOrMore(SimpleLexer.Token.Delimiter,
                                                 Root)),
                             SimpleLexer.Token.EndGroup));
        }
    };


    public SimpleParser (AbstractLexer lexer)
    {
        super(lexer);

        AbstractParserRule[] rules =
            {
                    Root,
                    BinaryExpression,
                    SimpleExpression,
                    Function,
                    Group,
                    Constant };
        for (AbstractParserRule rule : rules)
            rule.initializeRule();
    }
}
