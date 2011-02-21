/**
 * 
 */
package picassa.model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import picassa.model.expression.Expression;


/**
 * @author Michael Ansel
 */
public class FractalModel extends SimpleModel
{
    final private static List<Color> COLORS;
    private static final int MAX_ITERATIONS = 100;
    private static final int RANGE = 1;

    static
    {
        String filename =
            ResourceBundle.getBundle("picassa.resources.model")
                          .getString("SimpleModel.ColorFile");
        Scanner in = null;
        try
        {
            in = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("Could not open color file: " +
                                       e.getMessage());
        }

        List<Color> colors = new ArrayList<Color>();
        while (in.hasNext())
            colors.add(new Color(in.nextInt(), in.nextInt(), in.nextInt()));
        COLORS = colors;
    }


    public FractalModel (Map<String, Expression> variables)
    {
        super(variables);
    }


    @Override
    protected Color renderExpression (Expression expression,
                                      Map<String, Expression> variables)
    {
        double x = variables.get("x").evaluate(variables).get(0).doubleValue();
        double y = variables.get("y").evaluate(variables).get(0).doubleValue();

        //This could be more exciting but I kept it simple because...
        //1: It already took a pretty long time to research and make this, and
        //2: 8 minutes is considered a "fast" runtime for a fractal algorithm.
        int counter = 0;
        while ((Math.sqrt((x * x) + (y * y)) < RANGE) &&
               (counter <= MAX_ITERATIONS))
        {
            Color pixelColor = super.renderExpression(expression, variables);
            x = pixelColor.getRed();
            y = pixelColor.getGreen();
            counter++;
        }

        //Pixel's color depends on how many iterations it took x and y to "escape"
        return COLORS.get(counter % COLORS.size());
    }
}
