/**
 * 
 */
package picassa.model.expression;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Michael Ansel
 */
public class FunctionExpression extends Expression
{

    private static final Map<String, Method> METHODS;
    static
    {
        METHODS = new HashMap<String, Method>();
        METHODS.put("random", getStaticMethod("java.lang.Math.random", null));
        METHODS.put("abs", getStaticMethod("java.lang.Math.abs", double.class));
        METHODS.put("sum", getStaticMethod("picassa.util.Math.sum", Number[].class));
    }

    public static final String TOKEN_REGEX;
    static
    {
        StringBuilder regex = new StringBuilder();
        for (String method : METHODS.keySet())
            regex.append(String.format("|(%s)", method));
        regex.deleteCharAt(0); // remove leading |
        TOKEN_REGEX = regex.toString();
    }


    private static Method getStaticMethod (String fullName,
                                           Class<?> parameterTypes)
    {
        String className = fullName.substring(0, fullName.lastIndexOf("."));
        String methodName =
            fullName.substring(fullName.lastIndexOf(".") + 1, fullName.length());
        try
        {
            if (parameterTypes == null) return Class.forName(className)
                                                    .getMethod(methodName);
            else return Class.forName(className).getMethod(methodName,
                                                           parameterTypes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
    }

    private String myFunctionName;

    private List<Expression> myParameters;

    private Method myMethod;


    public FunctionExpression (String functionName, List<Expression> parameters)
    {
        myFunctionName = functionName;
        myParameters = parameters;

        if (!METHODS.containsKey(functionName)) throw new IllegalArgumentException("Unknown function: " +
                                                                                   functionName);
        myMethod = METHODS.get(functionName);
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        List<List<Number>> valueLists = new ArrayList<List<Number>>();
        for (Expression expression : myParameters)
            valueLists.add(expression.evaluate(variables));
        return evaluateValueLists((List<Number>[]) valueLists.toArray());
    }


    @Override
    protected Number evaluateValues (Number ... values)
    {
        try
        {
            return (Number) myMethod.invoke(null, (Object) values);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
    }


    @Override
    protected Collection<Expression> getExpressions ()
    {
        return new ArrayList<Expression>(myParameters);
    }


    @Override
    public String toString ()
    {
        StringBuilder paramString = new StringBuilder(myParameters.toString());
        paramString.deleteCharAt(0); // remove leading "["
        paramString.deleteCharAt(paramString.length() - 1); // remove trailing "]"
        return String.format("%s(%s)", myFunctionName, paramString.toString());
    }

}
