package picassa.util;

public class Complex
{
    // a static version of plus
    public static Complex plus (Complex a, Complex b)
    {
        double real = a.real + b.real;
        double imaginary = a.imaginary + b.imaginary;
        Complex sum = new Complex(real, imaginary);
        return sum;
    }

    private final double imaginary;

    private final double real;


    //create new complex number object
    public Complex (double re, double im)
    {
        real = re;
        imaginary = im;
    }


    //absolute value
    public double abs ()
    {
        return Math.sqrt(real * real + imaginary * imaginary);
    }


    // return a new Complex object whose value is the conjugate of this
    public Complex conjugate ()
    {
        return new Complex(real, -imaginary);
    }


    // return a new Complex object whose value is the complex cosine of this
    public Complex cos ()
    {
        return new Complex(Math.cos(real) * Math.cosh(imaginary),
                           -Math.sin(real) * Math.sinh(imaginary));
    }


    // return a / b
    public Complex divides (Complex b)
    {
        Complex a = this;
        return a.times(b.reciprocal());
    }


    // return a new Complex object whose value is the complex exponential of this
    public Complex exp ()
    {
        return new Complex(Math.exp(real) * Math.cos(imaginary),
                           Math.exp(real) * Math.sin(imaginary));
    }


    public double imaginaryComponent ()
    {
        return imaginary;
    }


    // return a new Complex object whose value is (this - b)
    public Complex minus (Complex b)
    {
        Complex a = this;
        double real = a.real - b.real;
        double imag = a.imaginary - b.imaginary;
        return new Complex(real, imag);
    }


    // return a new Complex object whose value is (this + b)
    public Complex plus (Complex b)
    {
        Complex a = this; // invoking object
        double real = a.real + b.real;
        double imag = a.imaginary + b.imaginary;
        return new Complex(real, imag);
    }


    public double realComponent ()
    {
        return real;
    }


    // return a new Complex object whose value is the reciprocal of this
    public Complex reciprocal ()
    {
        double scale = real * real + imaginary * imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }


    // return a new Complex object whose value is the complex sine of this
    public Complex sin ()
    {
        return new Complex(Math.sin(real) * Math.cosh(imaginary),
                           Math.cos(real) * Math.sinh(imaginary));
    }


    // return a new Complex object whose value is the complex tangent of this
    public Complex tan ()
    {
        return sin().divides(cos());
    }


    // return a new Complex object whose value is (this * b)
    public Complex times (Complex b)
    {
        Complex a = this;
        double real = a.real * b.real - a.imaginary * b.imaginary;
        double imag = a.real * b.imaginary + a.imaginary * b.real;
        return new Complex(real, imag);
    }


    // scalar multiplication
    // return a new object whose value is (this * alpha)
    public Complex times (double alpha)
    {
        return new Complex(alpha * real, alpha * imaginary);
    }


    //string representation of complex number
    public String toString ()
    {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary < 0) return real + "-" + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }
}
