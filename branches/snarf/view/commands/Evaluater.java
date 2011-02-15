package view.commands;

import java.awt.Dimension;
import util.commands.Command;
import model.*;
import model.semantics.*;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Evaluater implements Command<Pixmap>
{
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;


    /**
     * Evaluate an expression for each point in the image.
     */
    @Override
    public void execute (Pixmap target)
    {
        // create the expression to evaluate just once
        Operation toEval = createExpression();
        // evaluate it for each pixel
        Dimension size = target.getSize();
        for (int imageY = 0; imageY < size.height; imageY++)
        {
            double evalY = imageToDomainScale(imageY, size.height);
            RGBColor colorY = new RGBColor(evalY);
            for (int imageX = 0; imageX < size.width; imageX++)
            {
                double evalX = imageToDomainScale(imageX, size.width);
                RGBColor colorX = new RGBColor(evalX);
                target.setColor(imageX, imageY, 
                                toEval.evaluate(colorX, colorY).toJavaColor());
            }
        }
    }


    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double) value / bounds) * range + DOMAIN_MIN;
    }


    /**
     * A place holder for more interesting way to build the expression.
     */
    private Operation createExpression ()
    {
         // return new PerlinColor();
         return new Parser().makeExpression("");
    }
}
