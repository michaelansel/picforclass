/**
 * 
 */
package picassa.model.expression;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import picassa.util.Vector;
import util.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public abstract class BinaryExpression extends Expression
{
    static class BinaryOperator implements Comparable<BinaryOperator>
    {
        final static List<String> TOKEN_CLASS_NAMES;

        final static Map<String, Class<? extends BinaryExpression>> TOKEN_CLASS_MAP;
        static
        {

            Map<String, Class<? extends BinaryExpression>> tokenClassMap =
                new HashMap<String, Class<? extends BinaryExpression>>();

            List<String> tokenClassNames =
                Arrays.asList(ResourceBundle.getBundle("picassa.resources.syntax")
                                            .getString("BinaryExpression.OperatorPrecedence")
                                            .split(","));
            Collections.reverse(tokenClassNames);

            for (String tokenClassName : tokenClassNames)
            {
                try
                {
                    Class<? extends BinaryExpression> tokenClass =
                        (Class<? extends BinaryExpression>) Class.forName(tokenClassName);
                    String tokenRegex =
                        (String) tokenClass.getField("TOKEN_REGEX").get(null);
                    tokenClassMap.put(tokenRegex, tokenClass);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new RuntimeException("Failed to initialize BinaryOperator class: " +
                                               e.toString());
                }
            }
            TOKEN_CLASS_NAMES = tokenClassNames;
            TOKEN_CLASS_MAP = tokenClassMap;
        }

        private String myToken;
        private String myRegex;
        private Class<? extends BinaryExpression> myClass;
        private Integer myPrecedence;


        public BinaryOperator (String token)
        {
            myToken = token;
            myRegex = null;
            myClass = null;
            myPrecedence = null;
            for (String regex : TOKEN_CLASS_MAP.keySet())
            {
                if (Pattern.matches(regex, token))
                {
                    myRegex = regex;
                    myClass = TOKEN_CLASS_MAP.get(regex);
                    myPrecedence = TOKEN_CLASS_NAMES.indexOf(myClass.getName());
                }
            }
            if (myClass == null) throw new RuntimeException("Invalid operator: " +
                                                            token);
        }


        @Override
        public int compareTo (BinaryOperator other)
        {
            return myPrecedence.compareTo(other.myPrecedence);
        }


        public Expression newExpression (Expression a, Expression b)
        {
            try
            {
                Constructor<? extends BinaryExpression> constructor =
                    myClass.getConstructor(Expression.class, Expression.class);
                return constructor.newInstance(a, b);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            throw new RuntimeException("Unable to create Expression instance");
        }


        @Override
        public String toString ()
        {
            return "BinaryOperator(" + myToken + ")";
        }
    }


    public static BinaryExpression create (List<Object> list)
    {
        return (BinaryExpression) postfixToExpression(infixToPostfix(list));
    }


    /**
     * @param list
     * @return
     */
    private static List<Object> infixToPostfix (List<Object> list)
    {
        Stack<BinaryOperator> stack = new Stack<BinaryOperator>();
        List<Object> output = new ArrayList<Object>();
        for (Object token : list)
        {
            if (token instanceof Expression) output.add((Expression) token);
            else if (token instanceof TokenMatch)
            {
                BinaryOperator o1 =
                    new BinaryOperator(((TokenMatch) token).value);
                while (!stack.isEmpty() && o1.compareTo(stack.peek()) < 0)
                {
                    output.add(stack.pop());
                }
                stack.push(o1);
            }
            else throw new RuntimeException("Invalid list entry: " +
                                            token.toString());
        }
        while (!stack.isEmpty())
            output.add(stack.pop());
        assert (stack.isEmpty());
        return output;
    }


    /**
     * @param postfix
     * @return
     */
    private static Expression postfixToExpression (List<Object> postfix)
    {
        Stack<Expression> stack = new Stack<Expression>();
        while (!postfix.isEmpty())
        {
            while (!postfix.isEmpty() && postfix.get(0) instanceof Expression)
                stack.push((Expression) postfix.remove(0));
            if (postfix.isEmpty()) break;
            if (!(postfix.get(0) instanceof BinaryOperator)) throw new RuntimeException("WTF? Must be either an Expression or a BinaryOperator...");
            if (stack.size() < 2) throw new RuntimeException("RPN stack too small! It appears we can't generate good postfix...");
            Expression a = (Expression) stack.pop();
            Expression b = (Expression) stack.pop();
            BinaryOperator op = (BinaryOperator) postfix.remove(0);
            Expression result = op.newExpression(b, a); // operands reversed on stack, so "un-reverse"
            stack.push(result);
        }
        assert (stack.size() == 1);
        return stack.pop();
    }

    private Expression mySubExpressionA, mySubExpressionB;


    public BinaryExpression (Expression subExpressionA,
                             Expression subExpressionB)
    {
        mySubExpressionA = subExpressionA;
        mySubExpressionB = subExpressionB;
    }


    @Override
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        return evaluateVectors(mySubExpressionA.evaluate(variables),
                                  mySubExpressionB.evaluate(variables));
    }


    @Override
    protected Vector<Number> evaluateVectors (Vector<Number> ... vectors)
    {
        if (vectors.length != 2) throw new IllegalArgumentException("BinaryExpressions can only merge 2 sets of values");
        return super.evaluateVectors(vectors);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        if (values.length != 2) throw new IllegalArgumentException("BinaryExpressions can only merge 2 values");
        return mergeValues(values[0], values[1]);
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return Arrays.asList(new Expression[] { mySubExpressionA, mySubExpressionB });
    }


    protected abstract Number mergeValues (Number valueA, Number valueB);


    @Override
    public abstract String toString ();


    protected String toString (char operator)
    {
        return String.format("(%s%c%s)",
                             mySubExpressionA.toString(),
                             operator,
                             mySubExpressionB.toString());
    }
}
