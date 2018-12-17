package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.mapdisplay.MapDisplayFactory;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
// import de.fhpotsdam.unfolding.providers.Google;			// ORIGINAL
import de.fhpotsdam.unfolding.providers.*;					// ADDED BY ME
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a major earthquake			// great, major and strong added by me
	public static final float THRESHOLD_GREAT = 8;				
	// Less than this threshold is a strong earthquake 
	public static final float THRESHOLD_MAJOR = 7;
	// Less than this threshold is a moderate earthquake
	public static final float THRESHOLD_STRONG = 6;
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;		// ORIGINAL
	private UnfoldingMap map1;
	private UnfoldingMap map2;
	private UnfoldingMap map3;
	private UnfoldingMap map4;
	private UnfoldingMap map5;
	private UnfoldingMap map6;
	private UnfoldingMap map7;
	private UnfoldingMap map8;
	private UnfoldingMap map9;
	private UnfoldingMap map0;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// alternate feed of Earthquakes ... need faster internet to test
	// private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/atom.php";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			// map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());    // ORIGINAL
			map1 = new UnfoldingMap(this, 200, 50, 700, 500, new OpenStreetMap.OpenStreetMapProvider());
			map2 = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleSimplifiedProvider());
			map3 = new UnfoldingMap(this, 200, 50, 700, 500, new EsriProvider.WorldTopoMap());
			map4 = new UnfoldingMap(this, 200, 50, 700, 500, new EsriProvider.NatGeoWorldMap());
			map5 = new UnfoldingMap(this, 200, 50, 700, 500, new StamenMapProvider.WaterColor());
			map6 = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
			map7 = new UnfoldingMap(this, 200, 50, 700, 500, new StamenMapProvider.TonerLite());
			map8 = new UnfoldingMap(this, 200, 50, 700, 500, new StamenMapProvider.TonerBackground());
			map9 = new UnfoldingMap(this, 200, 50, 700, 500, new GeoMapApp.TopologicalGeoMapProvider());
			map0 = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.HybridProvider() );
			
			
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
		map = map8;
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map1, map2, map3, map4, map5, map6, map7, map8, map9, map0);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();								

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    //TODO (Step 3): Add a loop here that calls createMarker (see below) 
	    // to create a new SimplePointMarker for each PointFeature in 
	    // earthquakes.  Then add each new SimplePointMarker to the 
	    // List markers (so that it will be added to the map in the line below)
	    for(PointFeature earthquake : earthquakes) {
	    	markers.add(createMarker(earthquake));
	    }
	    
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);
	}
		
	/* createMarker: A suggested helper method that takes in an earthquake 
	 * feature and returns a SimplePointMarker for that earthquake
	 * 
	 * In step 3 You can use this method as-is.  Call it from a loop in the 
	 * setup method.  
	 * 
	 * TODO (Step 4): Add code to this method so that it adds the proper 
	 * styling to each marker based on the magnitude of the earthquake.  
	*/
	private SimplePointMarker createMarker(PointFeature feature)
	{  
		// To print all of the features in a PointFeature (so you can see what they are)
		// uncomment the line below.  Note this will only print if you call createMarker 
		// from setup
		//System.out.println(feature.getProperties());
		
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		// Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int red = color(255, 0, 0);						// GREAT earthquake
	    int orange = color(255, 165, 0);				// MAJOR earthquake
		int yellow = color(255, 255, 0);				// STRONG earthquake
		int green = color(0, 255, 0);					// MODERATE earthquake
		int blue = color(0, 0, 255);					// LIGHT earthquake
		int purple = color(255, 0, 255);				// MINOR earthquake
		
		// TODO (Step 4): Add code below to style the marker's size and color 
	    // according to the magnitude of the earthquake.  
	    // Don't forget about the constants THRESHOLD_MODERATE and 
	    // THRESHOLD_LIGHT, which are declared above.
	    // Rather than comparing the magnitude to a number directly, compare 
	    // the magnitude to these variables (and change their value in the code 
	    // above if you want to change what you mean by "moderate" and "light")
	    if(mag >= THRESHOLD_GREAT) {
	    	marker.setColor(red);
	    	marker.setRadius(16);
	    }else if(mag >= THRESHOLD_MAJOR) {
	    	marker.setColor(orange);
	    	marker.setRadius(14);
	    }else if(mag >= THRESHOLD_STRONG) {
	    	marker.setColor(yellow);
	    	marker.setRadius(12);
	    }else if(mag >= THRESHOLD_MODERATE) {
	    	marker.setColor(green);
	    	marker.setRadius(10);
	    }else if(mag >= THRESHOLD_LIGHT) {
	    	marker.setColor(blue);
	    	marker.setRadius(8);
	    }else {
	    	marker.setColor(purple);
	    	marker.setRadius(6);
	    }
	    
	    // Finally return the marker
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}
	
	
	public void keyPressed() {
	    
		List<Marker> markers = new ArrayList<Marker>();
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		for(PointFeature earthquake : earthquakes) {
	    	markers.add(createMarker(earthquake));
	    }
		 
		if (key == '1') {
	    	map = map1;
	    } else if (key == '2') {
	    	map = map2;
	    } else if (key == '3') {
	    	map = map3;
	    } else if (key == '4') {
	    	map = map4;
	    } else if (key == '5') {
	    	map = map5;
	    } else if (key == '6') {
	    	map = map6;
	    } else if (key == '7') {
	    	map = map7;
	    } else if (key == '8') {
	    	map = map8;
	    } else if (key == '9') {
	    	map = map9;
	    } else if (key == '0') {
	    	map = map0;
	    }
		
		map.addMarkers(markers);
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		
		// Legend (key) container
		stroke(0);					// Legend (key) container outline color
		fill(210);					// Legend (key) container color
		rect(20, 20, 165, 236, 7);
		
		// Legend (Key) writing and line as title at top of Legend (Key) container
		fill(0);					// text color
		textSize(16);
		text("Earthquake Key", 40, 40);
		stroke(0);					// line color
		strokeWeight(6);			// setting strokeWeight to make a bold line
		line(25, 55, 175, 55);
		strokeWeight(1);			// resetting strokeWeight()
		
		// Icon w/ Text - Red icon for "great" earthquake with text explanation
		fill(255, 0, 0);			// ellipse color
		ellipse(35, 80, 16, 16);
		fill(0);					// text color
		textSize(15);
		text("-  great (8.0+)", 55, 85);
		
		// Icon w/ Text - Orange icon for "major" earthquake with text explanation
		fill(255, 165, 0);			// ellipse color
		ellipse(35, 116, 14, 14);
		fill(0);					// text color
		textSize(14);
		text("-  major (7 - 7.9)", 55, 120);
		
		// Icon w/ Text - Yellow icon for "strong" earthquake with text explanation
		fill(255, 255, 0);			// ellipse color
		ellipse(35, 150, 12, 12);
		fill(0);					// text color
		textSize(13);
		text("-  strong (6 - 6.9)", 55, 153);
		
		// Icon w/ Text - Green icon for "moderate" earthquake with text explanation
		fill(0, 255, 0);			// ellipse color
		ellipse(35, 182, 10, 10);
		fill(0);					// text color
		textSize(12);
		text("-  moderate (5 - 5.9)", 55, 185);
		
		// Icon w/ Text - Blue icon for "light" earthquake with text explanation
		fill(0, 0, 255);			// ellipse color
		ellipse(35, 212, 8, 8);
		fill(0);					// text color
		textSize(11);
		text("-  light (4 - 4.9)", 55, 216);
		
		// Icon w/ Text - Purple icon for "minor" earthquake with text explanation
		fill(255, 0, 255);			// ellipse color
		ellipse(35, 240, 6, 6);
		fill(0);					// text color
		textSize(10);
		text("-  minor (3 -3.9)", 55, 243);
	}
}
