/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import picassa.util.Vector;
import util.parser.AbstractLexer.TokenMatch;


/**
 * @author Michael Ansel
 */
public class ConstantExpression extends Expression
{
    public static final String TOKEN_REGEX = "[0-9]+([.][0-9]+)?";


    public static Expression create (List<Object> objects)
    {
        if (objects.size() == 1 && objects.get(0) instanceof TokenMatch) return new ConstantExpression(((TokenMatch) objects.get(0)).value);
        else if (objects.size() > 1)
        {
            List<Object> values = new ArrayList<Object>();
            for (Object o : objects)
                if (o instanceof ConstantExpression) values.addAll(((ConstantExpression) o).myVector);
            return new ConstantExpression(values);
        }
        throw new IllegalArgumentException("Invalid input: " +
                                           objects.toString());
    }

    private Vector<Number> myVector;


    public ConstantExpression (List<Object> terms)
    {
        myVector = new Vector<Number>();
        for (Object term : terms)
            if (term instanceof String) myVector.add(Double.parseDouble((String) term));
            else if (term instanceof Number) myVector.add((Number) term);
            else throw new IllegalArgumentException("Invalid input: " +
                                                    term.toString());
    }


    public ConstantExpression (String term)
    {
        myVector = new Vector<Number>(Double.parseDouble(term));
    }


    public ConstantExpression (Number value)
    {
        myVector = new Vector<Number>(value);
    }


    @Override
    public Vector<Number> evaluate (Map<String, Expression> variables)
    {
        return new Vector<Number>(myVector);
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>();
    }


    @Override
    public String toString ()
    {
        if (myVector.size() == 1) return myVector.get(0).toString();
        // else
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Number element : myVector)
        {
            result.append(element);
            result.append(",");
        }
        // delete last comma
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }
}
