package guiModule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet{
	
	PImage img;
	
	
	public void setup() {
		
		// PApplet window size and background image. Could also just set background to a color
		size(400, 400);    			// Set canvas size
		background(255);  			// Set background color
		stroke(0);					// Set pen color
		img = loadImage("http://cseweb.ucsd.edu/~minnes/palmTrees.jpg", "jpg");
		img.resize(0, height);		// Resize loaded image to full height of canvas
		image(img, 0, 0);			// Display image
	}
	
	
	public void draw() {
		
		// Add drawing code for MyPApplet
		int[] color = sunColorSec(second());				// Calculate code for sun....
		fill(color[0], color[1], color[2]);					// Set sun color
		ellipse(width/4, height/5, width/4, height/5);		// Draw sun 
	}
	
	
	public int[] sunColorSec(float seconds) {
		
		int[] rgb = new int[3];
		// Scale the brightness of the yellow based on the seconds.    30 seconds
		// is black.    0 seconds is bright yellow.
		float diffFrom30 = Math.abs(30 - seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255 * ratio);
		rgb[1] = (int)(255 * ratio);
		rgb[2] = 0;
		
		// System.out.println("R" + rgb[0] + " G" + rgb[1] + " B" + rgb[2]);
		return rgb;
	}
	
	
	
}
