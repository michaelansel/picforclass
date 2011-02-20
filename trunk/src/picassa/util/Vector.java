/**
 * 
 */
package picassa.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Michael Ansel
 */
public class Vector<E extends Number> extends ArrayList<E>
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public Vector (E ... values)
    {
        super(Arrays.asList(values));
        fixSize();
    }


    public Vector (List<E> valueList)
    {
        super(valueList);
        fixSize();
    }


    private void fixSize ()
    {
        if (size() == 1)
        {
            add(get(0));
            add(get(0));
        }
    }


    public RGBColor toRGBColor ()
    {
        RGBColor color;
        fixSize();
        if (size() == 3) color =
            new RGBColor(get(0).doubleValue(),
                         get(1).doubleValue(),
                         get(2).doubleValue());
        else throw new RuntimeException("Can't convert Vector to RGB color: " +
                                        toString());
        return color;
    }
}
