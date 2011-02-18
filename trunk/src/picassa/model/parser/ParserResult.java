package picassa.model.parser;

import java.util.ArrayList;
import java.util.List;
import picassa.model.expression.Expression;
import picassa.model.parser.AbstractLexer.TokenMatch;


class ParserResult
{
    private List<Object> myObjects;
    private Expression myExpression;


    public ParserResult ()
    {
        myObjects = new ArrayList<Object>();
        myExpression = null;
    }


    public ParserResult (List<Object> objects)
    {
        myObjects = objects;
        myExpression = null;
    }


    public void add (TokenMatch token)
    {
        myObjects.add(token);
    }


    public void append (ParserResult result)
    {
        if (result.getExpression() != null) myObjects.add(result.getExpression());
        else myObjects.addAll(result.getList());
    }


    public Expression getExpression ()
    {
        return myExpression;
    }


    public List<Object> getList ()
    {
        return new ArrayList<Object>(myObjects);
    }


    public void setExpression (Expression expression)
    {
        myExpression = expression;
    }


    public String toString ()
    {
        if (myExpression != null) return String.format("ParserResult(%s)",
                                                       myExpression.toString());
        else return String.format("ParserResult(%s)", myObjects.toString());
    }
}
