/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @author Michael Ansel
 */
public abstract class Expression
{
    /**
     * Evaluate all child expressions (substituting variables as necessary) and
     * return a List of numbers
     * 
     * @param variables Map of variable names to numeric values
     * @return List of output values
     */
    public abstract List<Number> evaluate (Map<String, Number> variables);


    protected List<Number> evaluateValueLists (List<Number> ... valueLists)
    {
        int length = valueLists[0].size();
        for (List<Number> valueList : valueLists)
            if (valueList.size() != length) throw new IllegalArgumentException("All value lists must be the same size.");

        List<Number> results = new ArrayList<Number>();

        List<Number> values = new ArrayList<Number>();
        for (int i = 0; i < length; i++)
        {
            values.clear();
            for (List<Number> valueList : valueLists)
                values.add(valueList.get(i));
            results.add(evaluateValues((Number[]) values.toArray()));
        }
        return results;
    }


    protected abstract Number evaluateValues (Number ... values);


    protected abstract Collection<Expression> getExpressions ();


    /**
     * Recursively build an easy to view representation of the Expression tree
     * starting at startExpression.
     * 
     * @param startExpression root of Expression tree
     * @return Ready-to-print String representation of Expression tree
     */
    public static String printExpressionTree (Expression startExpression)
    {
        return printExpressionTree(startExpression, 0);
    }


    private static String printExpressionTree (Expression startExpression,
                                               int level)
    {
        Collection<Expression> expressions = startExpression.getExpressions();
        String result = "";
        String indent = "";
        for (int i = 0; i < level; i++)
            indent += "  ";

        if (expressions.size() > 0)
        {
            result +=
                String.format("%s[%s]\n", indent, startExpression.getClass()
                                                                 .getName());
            for (Expression expr : expressions)
            {
                result += printExpressionTree(expr, level + 1);
            }
        }
        else
        {
            result +=
                String.format("%s\"%s\"\n", indent, startExpression.toString());
        }
        return result;
    }
}
