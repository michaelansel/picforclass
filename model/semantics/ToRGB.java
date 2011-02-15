package model.semantics;

import model.RGBColor;


/**
 * Convert color from YUV to RGB color space.
 * 
 * Details and constants derived from:
 *   http://www.answers.com/topic/yuv
 * 
 * @author Robert C. Duvall
 */
public class ToRGB extends UnaryOperation
{
    @Override
    public RGBColor evaluateColor (RGBColor c)
    {
        return new RGBColor(
            c.getRed() + c.getBlue() *  1.4022,
            c.getRed() + c.getGreen() * -0.3456 + c.getBlue() * -0.7145,
            c.getRed() + c.getGreen() *  1.7710);
    }
}
