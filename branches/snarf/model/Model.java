package model;

import model.semantics.Operation;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;
    
    private Parser myParser;


    /**
     * Create a basic model of the system.
     */
    public Model ()
    {
        myParser = new Parser();
    }


    /**
     * Evaluate an expression for each point in the image.
     */
    public Pixmap evaluate (String infix, int width, int height)
    {
        Pixmap result = new Pixmap(width, height);
        // create expression to evaluate just once
        Operation toEval = myParser.makeExpression(infix);
        // evaluate at each pixel
        for (int imageY = 0; imageY < height; imageY++)
        {
            double evalY = imageToDomainScale(imageY, height);
            RGBColor colorY = new RGBColor(evalY);
            for (int imageX = 0; imageX < width; imageX++)
            {
                double evalX = imageToDomainScale(imageX, width);
                RGBColor colorX = new RGBColor(evalX);
                result.setColor(imageX, imageY,
                                toEval.evaluate(colorX, colorY).toJavaColor());
            }
        }
        return result;
    }


    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double)value / bounds) * range + DOMAIN_MIN;
    }
}
