/**
 * 
 */
package picassa.model.expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;


/**
 * @author Michael Ansel
 */
public abstract class UnaryExpression extends Expression
{
    private Expression mySubExpression;


    public UnaryExpression (Expression subExpression)
    {
        mySubExpression = subExpression;
    }


    // TODO define flattening interface
//    @Override
//    public Set<IndexEntry> getDocuments (DocumentIndex index)
//    {
//        // Extension: check/update cache of computed results to avoid recalculating
//        if (myDocuments == null)
//            myDocuments = processExpression(mySubExpression, index);
//        return myDocuments;
//    }


    // TODO define flattening interface
//    protected abstract Set<IndexEntry> processExpression (Expression expression,
//                                                              DocumentIndex index);


    @Override
    public String toString ()
    {
        return toString(mySubExpression);
    }


    @Override
    public Collection<Expression> getExpressions ()
    {
        Expression[] expressions = { mySubExpression };
        return Arrays.asList(expressions);
    }


    protected String toString (Expression subExpression)
    {
        return String.format("(%s)", subExpression.toString());
    }
}
