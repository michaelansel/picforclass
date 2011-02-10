/**
 * 
 */
package picassa.model.expression;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * @author Michael Ansel
 */
public abstract class BinaryExpression extends Expression
{
    public static final String DEFAULT_OPERATOR = "+";
    private Expression mySubExpressionA, mySubExpressionB;


    public BinaryExpression (Expression subExpressionA,
                             Expression subExpressionB)
    {
        mySubExpressionA = subExpressionA;
        mySubExpressionB = subExpressionB;
    }


    // TODO define flattening interface
//    @Override
//    public Set<IndexEntry> getDocuments (DocumentIndex index)
//    {
//        // Extension: check/update cache of computed results to avoid recalculating
//        if (myDocuments == null)
//            myDocuments = combineExpressions(mySubExpressionA, mySubExpressionB, index);
//        return myDocuments;
//    }


    // TODO define flattening interface
//    protected abstract Set<IndexEntry> combineExpressions (Expression expressionA,
//                                                               Expression expressionB,
//                                                               DocumentIndex index);


    @Override
    public Collection<Expression> getExpressions ()
    {
        Expression[] expressions = { mySubExpressionA, mySubExpressionB };
        return Arrays.asList(expressions);
    }


    @Override
    public String toString ()
    {
        return toString(' ');
    }


    protected String toString (char operator)
    {
        return String.format("(%s%c%s)",
                             mySubExpressionA.toString(),
                             operator,
                             mySubExpressionB.toString());
    }
    
    public static BinaryExpression createFromList(List<Object> list)
    {
        return (BinaryExpression)postfixToExpression(infixToPostfix(list));
    }


    /**
     * @param postfix
     * @return
     */
    private static Expression postfixToExpression (List<Object> postfix)
    {
        Stack<Expression> stack = new Stack<Expression>();
        while(!postfix.isEmpty())
        {
            while(!postfix.isEmpty() &&
                    postfix.get(0) instanceof Expression)
                stack.push((Expression) postfix.remove(0));
            if(postfix.isEmpty())
                break;
            if(!(postfix.get(0) instanceof Operator))
                throw new RuntimeException("WTF?");
            if(stack.size() < 2)
                throw new RuntimeException("RPN stack too small!");
            Expression a = (Expression)stack.pop();
            Expression b = (Expression)stack.pop();
            Operator op = (Operator)postfix.remove(0);
            Expression result = op.newExpression(a,b);
            stack.push(result);
        }
        assert(stack.size() == 1);
        return stack.pop();
    }


    /**
     * @param list
     * @return 
     */
    private static List<Object> infixToPostfix (List<Object> list)
    {
        Stack<Operator> stack = new Stack<Operator>();
        List<Object> output = new ArrayList<Object>();
        for(Object token : list)
        {
            if(token instanceof Expression)
                output.add((Expression) token);
            else if(token instanceof String)
            {
                Operator o1 = new Operator((String) token);
                while(!stack.isEmpty() && o1.compareTo(stack.peek()) < 0)
                {
                    output.add(stack.pop());
                }
                stack.push(o1);
            }
            else
                throw new RuntimeException("Invalid list entry: "+token.toString());
        }
        while(!stack.isEmpty())
            output.add(stack.pop());
        assert(stack.isEmpty());
        return output;
    }

}
class Operator implements Comparable<Operator>
{
    String[] tokenChars = {"%","^","-","+","/","*"};
    String[] tokenClasses = {"expression.ModuloExpression",     // %
                             "expression.ExponentExpression",   // ^
                             "expression.SumExpression",        // -
                             "expression.SumExpression",        // +
                             "expression.ProductExpression",    // /
                             "expression.ProductExpression"};   // *
    List<String> TOKENS = Arrays.asList(tokenChars);
    public String myToken;
    
    public Operator(String token)
    {
        if(!TOKENS.contains(token))
            throw new RuntimeException("Invalid operator: "+token);
        myToken = token;
    }

    public Expression newExpression (Expression a, Expression b)
    {
        try
        {
            @SuppressWarnings("unchecked")
            Class<? extends BinaryExpression> klass = (Class<? extends BinaryExpression>) Class.forName(tokenClasses[TOKENS.indexOf(myToken)]);
            Constructor<? extends BinaryExpression> constructor = klass.getConstructor(Expression.class, Expression.class);
            return constructor.newInstance(a,b);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        throw new RuntimeException("Unable to create Expression instance");
    }

    @Override
    public int compareTo (Operator other)
    {
        return new Integer(TOKENS.indexOf(this.myToken)).compareTo(TOKENS.indexOf(((Operator)other).myToken));
    }
    
    @Override
    public String toString()
    {
        return "Operator("+myToken+")";
    }
}
