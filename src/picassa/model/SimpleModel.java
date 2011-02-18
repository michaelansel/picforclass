/**
 * 
 */
package picassa.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import picassa.model.expression.Expression;
import picassa.model.parser.SimpleParser;
import picassa.util.Pixmap;
import picassa.util.RGBColor;
import util.parser.ParserException;


/**
 * @author Michael Ansel
 */
public class SimpleModel extends AbstractModel
{
    public static final double DEFAULT_DOMAIN_MIN = -1;
    public static final double DEFAULT_DOMAIN_MAX = 1;


    /**
     * Convert from image space to default domain space.
     */
    protected double imageToDomainScale (int value, int range)
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
