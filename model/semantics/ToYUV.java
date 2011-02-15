package model.semantics;

import model.RGBColor;


/**
 * Convert color from RGB to YUV color space.
 * 
 * Details and constants derived from:
 *   http://www.answers.com/topic/yuv
 * 
 * @author Robert C. Duvall
 */
public class ToYUV extends UnaryOperation
{
    /**
	 * @see model.semantics.Operation#evaluate(model.RGBColor, model.RGBColor)
	 */
    @Override
	public RGBColor evaluateColor (RGBColor c)
	{
        return new RGBColor(
            c.getRed() *  0.2989 + c.getGreen() *  0.5866 + c.getBlue() *  0.1145,
            c.getRed() * -0.1687 + c.getGreen() * -0.3312 + c.getBlue() *  0.5,
            c.getRed() *  0.5000 + c.getGreen() * -0.4183 + c.getBlue() * -0.0816);
	}
}
