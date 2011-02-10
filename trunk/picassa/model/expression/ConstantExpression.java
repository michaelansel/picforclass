/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author Michael Ansel
 *
 */
public class ConstantExpression extends Expression
{
    private List<Integer> myConstants;
    
    /**
     * @param term
     */
    public ConstantExpression (String term)
    {
        myConstants = new ArrayList<Integer>();
    }
        
    @Override
    public String toString()
    {
        if(myConstants.size() == 1)
            return myConstants.get(0).toString();
        // else
        StringBuilder result = new StringBuilder();
        result.append("[");
        for(Integer element : myConstants)
        {
            result.append(element);
            result.append(",");
        }
        // delete last comma
        result.deleteCharAt(result.length()-1);
        result.append("]");
        return result.toString();
    }

    @Override
    public Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>();
    }

}
