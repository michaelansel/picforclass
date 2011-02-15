package model.semantics;

import model.RGBColor;


/**
 * Combine two colors by using their components to seed noise function. 
 * 
 * Perlin noise algorithm described in detail at:
 *    http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
 * 
 * @author Robert C. Duvall
 */
public class PerlinBW extends BinaryOperation
{
    @Override
    public RGBColor evaluateColor (RGBColor left, RGBColor right)
    {
        return new RGBColor(
                PerlinNoise.noise(left.getRed() + right.getRed(),
                                  left.getGreen() + right.getGreen(),
                                  left.getBlue() + right.getBlue()));
     }
}
