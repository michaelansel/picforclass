/**
 * 
 */
package picassa.model.parser;

import java.util.List;
import picassa.model.expression.Expression;
import util.parser.ParserResult;


/**
 * @author Michael Ansel
 */
public class ExpressionParserResult extends ParserResult
{

    /**
     * 
     */
    public ExpressionParserResult ()
    {
        super();
    }


    /**
     * @param objects
     */
    public ExpressionParserResult (List<Object> objects)
    {
        super(objects);
    }


    public ExpressionParserResult (ParserResult result)
    {
        this();
        addResult(result);
    }


    @Override
    public void addResult (ParserResult result)
    {
        if (result instanceof ExpressionParserResult &&
            ((ExpressionParserResult) result).getExpression() != null) setExpression(((ExpressionParserResult) result).getExpression());
        super.addResult(result);
    }


    public Expression getExpression ()
    {
        if (getList().size() == 1 && getList().get(0) instanceof Expression) return (Expression) getList().get(0);
        return null;
    }


    public void setExpression (Expression expression)
    {
        if (getList().size() > 0) clearList();
        add(expression);
    }


    @Override
    public String toString ()
    {
        if (getExpression() != null) return String.format("ExpressionParserResult(%s<%s>)",
                                                          getExpression().getClass()
                                                                         .getSimpleName(),
                                                          getExpression().toString());
        else return "Expression" + super.toString();
    }

}
