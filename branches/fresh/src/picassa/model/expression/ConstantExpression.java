/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author Michael Ansel
 */
public class ConstantExpression extends Expression
{
    private List<Number> myConstants;


    public ConstantExpression (String term)
    {
        myConstants = new ArrayList<Number>();
        myConstants.add(Integer.parseInt(term));
    }


    public ConstantExpression (List<String> terms)
    {
        myConstants = new ArrayList<Number>();
        for (String term : terms)
            myConstants.add(Integer.parseInt(term));
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        return new ArrayList<Number>(myConstants);
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        throw new UnsupportedOperationException("Constant is a non-combinatorial Expression");
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
