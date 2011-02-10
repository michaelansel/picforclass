/**
 * 
 */
package picassa.model.expression;

import java.util.Collection;

/**
 * @author Michael Ansel
 *
 */
public abstract class Expression
{
    // TODO define interface for image generation
    // public abstract Set<IndexEntry> getDocuments(DocumentIndex index);

    public abstract Collection<Expression> getExpressions();
    
    
    public static String printExpressionTree (Expression startExpression)
    {
        return printExpressionTree(startExpression,0);
    }

    public static String printExpressionTree (Expression startExpression, int level)
    {
        Collection<Expression> expressions = startExpression.getExpressions();
        String result = "";
        String indent = "";
        for(int i=0; i<level; i++)
            indent += "  ";
        
        if(expressions.size() > 0) {
            result += String.format("%s[%s]\n",indent,startExpression.getClass().getName());
            for(Expression expr : expressions)
            {
                result += printExpressionTree(expr, level+1);
            }
        } else {
            result += String.format("%s\"%s\"\n",indent,startExpression.toString());
        }
        return result;
    }
}
