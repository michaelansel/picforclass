/**
 * 
 */
package picassa.model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import picassa.model.expression.Expression;
import picassa.model.parser.SimpleParser;
import picassa.util.Complex;
import picassa.util.Pixmap;
import picassa.util.RGBColor;
import util.parser.ParserException;


/**
 * @author Michael Ansel
 */
public class SimpleModel extends AbstractModel
{
    private static final Color[] COLOR_MAP = new Color[4];
    
    static
    {    
    COLOR_MAP[0] = new Color(0, 0, 0);
    COLOR_MAP[1] = new Color(176, 31, 23);
    COLOR_MAP[2] = new Color(220, 60, 20);
    COLOR_MAP[3] = new Color(255, 255, 255);
    }
    
    /**
    private static String colorFile = 
        "/data/ColorMap.txt";
    private static final File colors = new File(colorFile);

    static
    {
        Scanner in = null;    
        try
            {
                in = new Scanner(colors);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Could not open color file.");
                e.printStackTrace();
            }
            
          int i = 0;
            while (in.hasNext() && i<4)
            {
                COLOR_MAP[i] = new Color(in.nextInt(),in.nextInt(), in.nextInt());
            }
    }
    */
    
    
    public static final double DEFAULT_DOMAIN_MAX = 1;
    public static final double DEFAULT_DOMAIN_MIN = -1;
    private static final int MAX_ITERATIONS = 100;
    private static final int RANGE = 1;


    public Pixmap fractalizeExpression (Expression expression, Pixmap baseImage)
    {
        Pixmap fractalImage = new Pixmap(baseImage);
        int height = baseImage.getSize().height;
        int width = baseImage.getSize().width;
        Map<String, Number> variables = new HashMap<String, Number>();
        
        for (int yAxis = 0; yAxis < height; yAxis++)
        {
            double y = imageToDomainScale(yAxis, height);
            for (int xAxis = 0; xAxis < width; xAxis++)
            {
                double x = imageToDomainScale(xAxis, width);
                variables.put("x", x);
                variables.put("y", y); 
            
            //x and y are the locations of the pixel in the pixmap
            //a complex number is created with x the real component and y the imaginary one.
  

            int counter = 0;
            while ((Math.sqrt((x * x) + (y * y)) < RANGE) &&
                   (counter <= MAX_ITERATIONS))
            {
                List<Number> values = expression.evaluate(variables);
                if (values.size()==1)
                {
                    x = values.get(0).doubleValue();
                    y = x;
                }
                else if (values.size()>=2)
                {
                    x = values.get(0).doubleValue();
                    y = values.get(1).doubleValue();
                }
                else throw new RuntimeException("Fractalize failed: " + values.toString());
                counter++;
            }

            //Pixel's color depends on how many iterations it took x and y to "escape"
            Color pixelColor = COLOR_MAP[counter % 4];
            fractalImage.setColor(xAxis, yAxis, pixelColor);
       
            }
        }
        return fractalImage;
    }


    //This could be more exciting but I kept it simple because...
    //1: It already took a pretty long time to research and make this, and
    //2: 8 minutes is considered a "fast" runtime for a fractal algorithm.

    /**
     * Convert from image space to default domain space.
     */
    private double imageToDomainScale (int value, int range)
    {
        return imageToDomainScale(value,
                                  range,
                                  DEFAULT_DOMAIN_MIN,
                                  DEFAULT_DOMAIN_MAX);
    }


    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value,
                                         int range,
                                         double domainMin,
                                         double domainMax)
    {
        double domainRange = domainMax - domainMin;
        return ((double) value / range) * domainRange + domainMin;
    }


    /**
     * @throws ParserException
     * @see picassa.model.AbstractModel#parseExpression(java.lang.String)
     */
    @Override
    public Expression parseExpression (String expression)
        throws ParserException
    {
        return SimpleParser.parse(expression);
    }

    /**
     * @see picassa.model.AbstractModel#renderExpression(picassa.model.expression.Expression,
     *      picassa.util.Pixmap)
     */
    @Override
    public Pixmap renderExpression (Expression expression, Pixmap baseImage)
    {
        int height = baseImage.getSize().height;
        int width = baseImage.getSize().width;
        Pixmap result = new Pixmap(baseImage);
        Map<String, Number> variables = new HashMap<String, Number>();
        // evaluate at each pixel
        for (int imageY = 0; imageY < height; imageY++)
        {
            variables.put("y", imageToDomainScale(imageY, height));
            for (int imageX = 0; imageX < width; imageX++)
            {
                variables.put("x", imageToDomainScale(imageX, width));
                List<Number> values = expression.evaluate(variables);
                RGBColor color;
                if (values.size() == 1) color =
                    new RGBColor(values.get(0).doubleValue());
                else if (values.size() == 3) color =
                    new RGBColor(values.get(0).doubleValue(),
                                 values.get(1).doubleValue(),
                                 values.get(2).doubleValue());
                else throw new RuntimeException("Expression did not return a valid color value");
                result.setColor(imageX, imageY, color.toJavaColor());
            }
        }
        return result;
    }
}
