/**
 * 
 */
package picassa.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import picassa.model.expression.ConstantExpression;
import picassa.model.expression.Expression;
import picassa.util.Pixmap;
import util.parser.ParserException;


/**
 * @author Michael Ansel
 */
public abstract class AbstractModel
{
    public static final double DEFAULT_DOMAIN_MAX = 1;

    public static final double DEFAULT_DOMAIN_MIN = -1;


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
     * TODO JavaDoc
     * 
     * @param expression
     * @return
     * @throws ParserException
     */
    public abstract Expression parseExpression (String expression)
        throws ParserException;


    /**
     * TODO JavaDoc
     * 
     * @param expression expression tree to render
     * @param base image to use as start point for rendering
     * @return
     */
    public Pixmap renderExpression (Expression expression, Pixmap baseImage)
    {
        System.out.println("Rendering expression: " + expression.toString());
        int height = baseImage.getSize().height;
        int width = baseImage.getSize().width;
        Pixmap result = new Pixmap(baseImage);
        Map<String, Expression> variables = new HashMap<String, Expression>();
        // evaluate at each pixel
        for (int imageY = 0; imageY < height; imageY++)
        {
            double y = imageToDomainScale(imageY, height);
            variables.put("y", new ConstantExpression(y));
            for (int imageX = 0; imageX < width; imageX++)
            {
                double x = imageToDomainScale(imageX, width);
                variables.put("x", new ConstantExpression(x));

                Color color = renderExpression(expression, variables);
                result.setColor(imageX, imageY, color);
            }
        }
        return result;
    }


    /**
     * Short-cut helper to parse and render an expression in one step.
     * 
     * @param expression expression to parse and render
     * @param image base image to use as start point for rendering
     * @return rendered expression
     * @throws ParserException
     */
    public Pixmap renderExpression (String expression, Pixmap image)
        throws ParserException
    {
        return renderExpression(parseExpression(expression), image);
    }


    protected abstract Color renderExpression (Expression expression,
                                               Map<String, Expression> variables);
}
