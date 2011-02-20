/**
 * 
 */
package picassa.model.parser;

import java.util.ArrayList;
import java.util.List;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.Expression;
import picassa.model.expression.VariableExpression;
import picassa.model.expression.function.FunctionExpression;
import util.parser.AbstractLexer;
import util.parser.AbstractLexer.TokenMatch;
import util.parser.AbstractParser;
import util.parser.AbstractParserRule;
import util.parser.ParserException;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class SimpleParser extends AbstractParser
{
    public static Expression parse (String input) throws ParserException
    {
        SimpleParser parser = new SimpleParser(new SimpleLexer(input));
        Expression result =
            ((ExpressionParserResult) parser.Root.evaluate()).getExpression();
        if (parser.hasNextToken()) parser.parseError();
        return result;
    }

    private AbstractParserRule BinaryExpression = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ExpressionParserResult result =
                new ExpressionParserResult(super.evaluate());
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

    private AbstractParserRule Constant = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = super.evaluate();
            List<Object> results = subResult.getList();
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

            ExpressionParserResult result =
                new ExpressionParserResult(subResult);
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

    private AbstractParserRule Function = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = super.evaluate();
            List<Object> results = subResult.getList();
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
            ExpressionParserResult result =
                new ExpressionParserResult(subResult);
            result.setExpression(FunctionExpression.create(functionName.value,
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

    private AbstractParserRule Group = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ExpressionParserResult result =
                new ExpressionParserResult(super.evaluate());
            for (Object o : result.getList())
                if (o instanceof Expression) result.setExpression((Expression) o);
            return result;
        }


        @Override
        public void initializeRule ()
        {
            setRule(Sequence(SimpleLexer.Token.BeginGroup,
                             Root,
                             SimpleLexer.Token.EndGroup));
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

    private AbstractParserRule SimpleExpression = new AbstractParserRule()
    {
        @Override
        public void initializeRule ()
        {
            setRule(FirstOf(UnaryExpression,
                            Function,
                            Group,
                            Variable,
                            Vector,
                            Constant));
        }
    };

    private AbstractParserRule UnaryExpression = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = super.evaluate();
            List<Object> results = subResult.getList();
            if (results.size() == 2 && results.get(0) instanceof TokenMatch &&
                results.get(1) instanceof Expression)
            {
                ExpressionParserResult result =
                    new ExpressionParserResult(subResult);
                result.setExpression(picassa.model.expression.UnaryExpression.create(((TokenMatch) results.get(0)).value,
                                                                                     (Expression) results.get(1)));
                return result;
            }
            parseError();
            return null;
        }


        @Override
        public void initializeRule ()
        {
            setRule(Sequence(SimpleLexer.Token.UnaryOperator, SimpleExpression));
        }
    };

    private AbstractParserRule Variable = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = super.evaluate();
            List<Object> results = subResult.getList();
            if (results.size() == 1 && results.get(0) instanceof TokenMatch)
            {
                ExpressionParserResult result =
                    new ExpressionParserResult(subResult);
                result.setExpression(new VariableExpression(((TokenMatch) results.get(0)).value));
                return result;
            }
            parseError();
            return null;
        }


        @Override
        public void initializeRule ()
        {
            setRule(ExactlyOne(SimpleLexer.Token.Variable));
        }
    };

    private AbstractParserRule Vector = new AbstractParserRule()
    {
        @Override
        public ParserResult evaluate () throws ParserException
        {
            ParserResult subResult = super.evaluate();
            List<Object> resultList = subResult.getList();
            resultList.remove(0); // BeginVector
            resultList.remove(resultList.size() - 1); // EndVector
            ExpressionParserResult result = new ExpressionParserResult();
            result.setExpression(ConstantExpression.create(resultList));
            return result;
        }


        @Override
        public void initializeRule ()
        {
            setRule(Sequence(SimpleLexer.Token.BeginVector,
                             Constant,
                             ZeroOrMore(SimpleLexer.Token.Delimiter, Constant),
                             SimpleLexer.Token.EndVector));
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
                    UnaryExpression,
                    Function,
                    Group,
                    Variable,
                    Vector,
                    Constant };
        for (AbstractParserRule rule : rules)
            rule.initializeRule();
    }
}
