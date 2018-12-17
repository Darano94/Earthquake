package guiModule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyDisplay extends PApplet{

	PImage img;
	
	
	public void setup() {
		
		// PApplet window size and background image. Could also just set background to a color
		size(612, 408);    			// Set canvas size
		img = loadImage("C:\\Users\\user\\Desktop\\Storm Picture.jpg");
		background(img);
	}
	
	
	public void draw() {
		
		// Smiley-face outline
		fill(250, 187, 134);
		ellipse(306, 204, 380, 380);
		
		// Left-eye outline of smiley-face
		fill(255, 255, 255);
		ellipse(220, 150, 80, 80);
		
		// Left-eye iris of smiley-face
		fill(0, 255, 255);
		ellipse(220, 150, 44, 44);
				
		// Left-eye pupil of smiley-face
		fill(0, 0, 0);
		ellipse(220, 150, 20, 20);
		
		// Right-eye outline of smiley-face
		fill(255, 255, 255);
		ellipse(392, 150, 80, 80);
		
		// Right-eye iris of smiley-face
		fill(0, 255, 255);
		ellipse(392, 150, 44, 44);
		
		// Right-eye pupil of smiley-face
		fill(0, 0, 0);
		ellipse(392, 150, 20, 20);
		
		// Smile outline on the smiley-face
		noFill();
		arc(308, 250, 260, 180, 0, PI);
	}
}
