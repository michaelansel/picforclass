package model.util;
import java.math.*;

import model.Pixmap;

//Will need a "fractalize" button in the GUI
//Will need to ask user for a function and c value and pass it here.
	//This means we'll need to parse user's function and also make sure it's valid.
		//Function must be polynomial in the form of "Z= ..." and contain Z and C
	//This means we need to be able to recognize and handle (parse) complex numbers.
public class Fractalize 
{
	private static final double THRESHOLD = 0.0;
	private static final int MAX_ITERATIONS = 20;
	
	//How to transform color to complex number
	
	//Complex numbers are represented in the form of an array of doubles -
	//The first entry is the real part and the second entry is the complex part.
	private static final double[] ORIGIN = new double[2]; 
	
	//Assuming we're getting a Pixmap that has already been evaluated
	
	//Get function from user as a string (will have to use the parser)
	//Get value of c from the user as a double (could we let them input complex nums?)
	
	//How to simplify the complex component?
	
	private static Pixmap iterateImage(String function, double[] c, double[] z, Pixmap image)
	{
		//Take the image and transform each pixel's color value to complex number.  
		//Make a 2D array of double[]'s with this information??
		
		//Based on what function is, figure out what the magnitude's threshold should be
		
		//Iterate through each element in the array:
			//Do the following MAX_ITERATIONS times;
			//Update Z in accordance with the provided function
			//Find Z's magnitude
			//If magnitude>threshold, break
			//Else if currentiteration == MAX_ITERATIONS, make Z black
		
		//Transform array of complex numbers back into a pixmap of colors
		//Draw the new image.		
	}
	
	
	//Performs addition on two complex numbers
	private static double[] complexAddition(double[] a, double[] b)
	{
		double[] sum = new double[2];
		sum[0] = a[0]+b[0];
		sum[1] = a[1]+b[1];
		return sum;
	}
	
	//Performs subtraction on two complex numbers
	private static double[] complexSubtraction(double[] a, double[] b)
	{
		double[] difference = new double[2];
		difference[0] = a[0]-b[0];
		difference[1] = a[1]-b[1];
		return difference;
	}
	
	private static double[] complexMultiplication(double[] a, double[] b)
	{
		double[] product = new double[2];
		product[0] = a[0]*b[0] - a[1]*b[1];
		product[1] = a[1]*b[0] + a[0]*b[1];
		return product;
	}
	
	private static double[] complexDivision(double[] a, double[] b)
	{
		double[] quotient = new double[2];
		//Do division!
		return quotient;
	}
	
	private static double[] complexExponential(double[] a, int power)
	{
		double[] exponential = a;
		
		for (int k=1; k<power; k++)
		{
			exponential = complexMultiplication(exponential, a);
		}
		return exponential;
	}
	
	//Finds the distance from Z to the origin (known as Z's magnitude)
	private static double getMagnitude(double[] ORIGIN, double[] coordinatesOfZ)
	{	
		double[] distanceArray = complexSubtraction(ORIGIN, coordinatesOfZ);
		double magnitude = Math.abs(Math.sqrt(Math.pow(distanceArray[1], 2) 
				+ Math.pow(distanceArray[2], 2)));
		return magnitude;
	}
}
