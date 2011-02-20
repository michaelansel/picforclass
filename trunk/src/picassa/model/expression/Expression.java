/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import picassa.util.RGBColor;
import picassa.util.Vector;


/**
 * @author Michael Ansel
 */
public abstract class Expression
{
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


    /**
     * Evaluate all child expressions (substituting variables as necessary) and
     * return a List of numbers
     * 
     * @param variables Map of variable names to numeric values
     * @return List of output values
     */
    public abstract Vector<Number> evaluate (Map<String, Expression> variables);


    protected final Vector<Number> evaluateVectors (List<Vector<Number>> vectors)
    {
        // Because Java doesn't let you create an array of generics...
        @SuppressWarnings("unchecked")
        Vector<Number>[] vectorArray = new Vector[vectors.size()];
        for (int i = 0; i < vectorArray.length; i++)
            vectorArray[i] = vectors.get(i);
        return evaluateVectors(vectorArray);
    }


    protected Vector<Number> evaluateVectors (Vector<Number> ... vectors)
    {
        vectors[0].fixSize();
        int length = vectors[0].size();
        for (Vector<Number> vector : vectors) {
            vector.fixSize();
            if (vector.size() != length) throw new IllegalArgumentException("All vectors must be the same size.");
        }

        Vector<Number> results = new Vector<Number>();

        List<Number> rowValues = new ArrayList<Number>();
        // for each "row" in the 2D matrix created by multiple column vectors
        for (int i = 0; i < length; i++)
        {
            rowValues.clear();
            for (Vector<Number> vector : vectors)
                rowValues.add(vector.get(i));
            results.add(evaluateValues(rowValues.toArray(new Number[] {})));
        }
        return results;
    }


    protected Number evaluateValues (Number ... values)
    {
        throw new UnsupportedOperationException("Not implemented");
    }


    protected abstract Collection<Expression> getExpressions ();
}
