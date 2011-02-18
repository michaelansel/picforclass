/**
 * 
 */
package picassa.model.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * @author Michael Ansel
 */
public abstract class FunctionExpression extends Expression
{

    private static final Map<String, Class<? extends FunctionExpression>> TOKEN_CLASS_MAP;
    public static final String TOKEN_REGEX;
    static
    {
        Map<String, Class<? extends FunctionExpression>> tokenClassMap =
            new HashMap<String, Class<? extends FunctionExpression>>();

        List<String> tokenClassNames =
            Arrays.asList(ResourceBundle.getBundle("picassa.resources.syntax")
                                        .getString("FunctionExpression.Functions")
                                        .split(","));
        StringBuilder regex = new StringBuilder();

        for (String tokenClassName : tokenClassNames)
        {
            try
            {
                Class<? extends FunctionExpression> tokenClass =
                    (Class<? extends FunctionExpression>) Class.forName(tokenClassName);
                String functionName =
                    (String) tokenClass.getField("FUNCTION_NAME").get(null);
                tokenClassMap.put(functionName, tokenClass);
                regex.append(String.format("|(%s)", functionName));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize FunctionExpression class: " +
                                           e.toString());
            }
        }
        TOKEN_CLASS_MAP = tokenClassMap;
        regex.deleteCharAt(0); // remove leading |
        TOKEN_REGEX = regex.toString();
    }


    public static Expression create (String functionName,
                                     List<Expression> parameters)
    {
        if (!TOKEN_CLASS_MAP.containsKey(functionName)) throw new IllegalArgumentException("Unknown function: " +
                                                                                           functionName);
        Class<? extends FunctionExpression> newClass =
            TOKEN_CLASS_MAP.get(functionName);

        int parameterCount = getParameterCount(functionName);

        if (parameterCount != -1 && parameterCount != parameters.size()) throw new IllegalArgumentException(String.format("Mismatched parameter count: expected %d, got %d",
                                                                                                                          parameterCount,
                                                                                                                          parameters.size()));

        try
        {
            return newClass.getConstructor(String.class, List.class)
                           .newInstance(functionName, parameters);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error creating new instance: " +
                                       e.toString());
        }
    }


    public static int getParameterCount (String functionName)
    {
        Class<? extends FunctionExpression> klass =
            TOKEN_CLASS_MAP.get(functionName);

        try
        {
            return (Integer) (klass.getField("PARAMETER_COUNT").get(null));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving parameter count for " +
                                       functionName + ": " + e.toString());
        }
    }

    private String myFunctionName;

    private List<Expression> myParameters;


    public FunctionExpression (String functionName, List<Expression> parameters)
    {
        myFunctionName = functionName;
        myParameters = parameters;
    }


    @Override
    public List<Number> evaluate (Map<String, Number> variables)
    {
        List<List<Number>> valueLists = new ArrayList<List<Number>>();
        for (Expression expression : myParameters)
            valueLists.add(expression.evaluate(variables));

        return evaluateValueLists(valueLists);
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
