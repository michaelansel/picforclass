package model;

import java.awt.Color;


/**
 * Represents an immutable RGB color, whose values range from -1 to 1
 * 
 * For more information about RGB colors, see:
 *   http://en.wikipedia.org/wiki/RGB_color_model
 * 
 * @author Robert C. Duvall
 */
public class RGBColor
{
    public static final double COLOR_MIN = -1;
    public static final double COLOR_MAX = 1;
    public static final int JAVA_COLOR_MAX = Color.WHITE.getRed();

    private double myRed;
    private double myGreen;
    private double myBlue;


    /**
     * Create a color that represents BLACK.
     */
    public RGBColor ()
    {
        this(COLOR_MIN);
    }


    /**
     * Create a grey-scale color from the given value.
     */
    public RGBColor (double grey)
    {
        this(grey, grey, grey);
    }


    /**
     * Create a color from the given component values.
     */
    public RGBColor (double red, double green, double blue)
    {
        myRed = red;
        myGreen = green;
        myBlue = blue;
    }


    /**
     * Create a color from its corresponding Java Color.
     */
    public RGBColor (Color javaColor)
    {
        this(fromJava(javaColor.getRed()),
             fromJava(javaColor.getGreen()),
             fromJava(javaColor.getBlue()));
    }


    /**
     * Create a color from another RGBColor.
     */
    public RGBColor (RGBColor other)
    {
        this(other.getRed(), other.getGreen(), other.getBlue());
    }


    /**
     * @return red component of this color
     */
    public double getRed ()
    {
        return myRed;
    }


    /**
     * @return green component of this color
     */
    public double getGreen ()
    {
        return myGreen;
    }


    /**
     * @return blue component of this color
     */
    public double getBlue ()
    {
        return myBlue;
    }


    /**
     * Clamps this colors values to its max and min values.
     */
    public RGBColor clamp ()
    {
        return new RGBColor(clamp(getRed()),
                            clamp(getGreen()),
                            clamp(getBlue()));
    }


    /**
     * Clamps this colors values to its max and min values.
     */
    public RGBColor wrap ()
    {
        return new RGBColor(wrap(getRed()),
                            wrap(getGreen()),
                            wrap(getBlue()));
    }


    /**
     * Converts this colors to its corresponding Java Color.
     */
    public Color toJavaColor ()
    {
        return new Color(toJava(clamp(getRed())),
                         toJava(clamp(getGreen())),
                         toJava(clamp(getBlue())));
    }


    /**
     * @return true iff this Color's component values match the given color's.
     */
    public boolean equals (Object o)
    {
        if (o instanceof RGBColor)
        {
            RGBColor other = (RGBColor) o;
            return getRed() == other.getRed() &&
                   getGreen() == other.getGreen() &&
                   getBlue() == other.getBlue();
        }
        return false;
    }


    /**
     * @return component values of this color as a string
     */
    public String toString ()
    {
        return "[ " + getRed() + " , " + getGreen() + " , " + getBlue() + " ]";
    }


    /**
     * Clamps the given value to the Color's max and min values.
     */
    private double clamp (double value)
    {
        return Math.max(COLOR_MIN, Math.min(COLOR_MAX, value));
    }


    /**
     * Clamps the given value to the Color's max and min values.
     */
    private static double wrap (double value)
    {
        double range = COLOR_MAX - COLOR_MIN;
        value %= range;
        if (value > COLOR_MAX)      return value - range;
        else if (value < COLOR_MIN) return value + range;
        else                        return value;
    }

    
    /**
     * Converts the given value to the RGB Color's range.
     */
    // note, must be static to be called from constructor
    private static double fromJava (int value)
    {
        double range = COLOR_MAX - COLOR_MIN;
        return (double) value / JAVA_COLOR_MAX * range + COLOR_MIN;
    }


    /**
     * Converts the given value to the Java Color's range.
     */
    // note, static to be symmetric with fromJava method
    private static int toJava (double value)
    {
        double range = COLOR_MAX - COLOR_MIN;
        return (int) ((value - COLOR_MIN) / range * JAVA_COLOR_MAX);
    }
}
