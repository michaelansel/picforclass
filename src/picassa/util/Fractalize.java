package model.util;
import java.math.*;

import model.Pixmap;

//This could be more exciting but I kept it simple because...
//1: It already took a pretty long time to research and make this, and
//2: 8 minutes is considered a "fast" runtime for a fractal algorithm.
public class Fractalize 
{
	private static final int RANGE = 10;
	private static final int MAX_ITERATIONS = 100;
	private static final Color[] COLOR_MAP = new Color[100]; //why red?
	//where should I initialize colormap?  resource bundle?
	
	private static Pixmap createImage(String function, Pixmap image)
	{
		Pixmap fractalImage;
		for (pixel : image)
		{
			//x and y are the locations of the pixel in the pixmap
			double x; //= x-location on pixmap, will be real component.
			double y; //= y-location on pixmap, will be imaginary component.
			//a complex number is created with x the real component and y the imaginary one.
			Complex z0 = new Complex(x, y);
			Complex zN = new Complex(0, 0);
			
			int counter = 0;
			while((Math.sqrt((x*x)+(y*y)) < RANGE) && (counter <= MAX_ITERATIONS))
			{
				//zN = Evaluater.evaluate(x, y);
				//xUpdated = zN.real;
				//yUpdated = zN.imaginary;
				counter++;
			}
			
			//Pixel's color depends on how many iterations it took x and y to "escape"
			Color pixelcolor = COLOR_MAP[counter % MAX_ITERATIONS];
			fractalImage[pixel] = pixelcolor;
		}
		
		return fractalImage;	
	}	
}