/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
                if (o instanceof ConstantExpression) values.addAll(((ConstantExpression) o).myConstants);
            return new ConstantExpression(values);
        }
        throw new IllegalArgumentException("Invalid input: " +
                                           objects.toString());
    }

    private List<Number> myConstants;


    public ConstantExpression (List<Object> terms)
    {
        myConstants = new ArrayList<Number>();
        for (Object term : terms)
            if (term instanceof String) myConstants.add(Double.parseDouble((String) term));
            else if (term instanceof Number) myConstants.add((Number) term);
            else throw new IllegalArgumentException("Invalid input: " +
                                                    term.toString());
    }


    public ConstantExpression (String term)
    {
        myConstants = new ArrayList<Number>();
        myConstants.add(Double.parseDouble(term));
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        return new ArrayList<Number>(myConstants);
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>();
    }


    @Override
    public String toString ()
    {
        if (myConstants.size() == 1) return myConstants.get(0).toString();
        // else
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Number element : myConstants)
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
