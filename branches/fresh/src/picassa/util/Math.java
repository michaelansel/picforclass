/**
 * 
 */
package picassa.util;

/**
 * @author Michael Ansel
 */
public class Math
{
    public static Number sum (Number ... numbers)
    {
        Double sum = 0.0;
        for (Number number : numbers)
            sum += number.doubleValue();
        return sum;
    }
}
