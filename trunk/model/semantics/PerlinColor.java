package model.semantics;

import model.RGBColor;


/**
 * Combine two colors by using their components to seed noise function.
 * 
 * Perlin noise algorithm described in detail at:
 *   http://freespace.virgin.net/hugo.elias/models/m_perlin.htm
 * 
 * @author Robert C. Duvall
 */
public class PerlinColor extends BinaryOperation
{
    @Override
    public RGBColor evaluateColor (RGBColor left, RGBColor right)
    {
        return new RGBColor(
                PerlinNoise.noise(left.getRed() + 0.3, right.getRed() + 0.3, 0),
                PerlinNoise.noise(left.getGreen() - 0.8, right.getGreen() - 0.8, 0),
                PerlinNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1, 0));
    }
}
