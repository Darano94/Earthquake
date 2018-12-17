package guiModule;


import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet{
	
	private UnfoldingMap map;
	
	
	public void setup() {
		
		size(950, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLoc = new Location(-38.14f, -73.03f);
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdivia, Chile");
		valEq.addProperty("magnitude", 9.5);
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", 1960);
		valEq.addProperty("UTC - time", "19:11");
		Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		map.addMarker(valMk);
		SimplePointMarker val = new SimplePointMarker(valLoc);
		map.addMarker(val);
		
		Location alaskaLoc = new Location(60.91f, -147.34f);
		Feature alaskaEq = new PointFeature(alaskaLoc);
		alaskaEq.addProperty("title", "Prince William Sound, Alaska");
		alaskaEq.addProperty("magnitude", 9.2);
		alaskaEq.addProperty("date", "March 28, 1964");
		alaskaEq.addProperty("year", 1964);
		alaskaEq.addProperty("UTC - time", "03:36");
		Marker alaskaMk = new SimplePointMarker(alaskaLoc, alaskaEq.getProperties());
		map.addMarker(alaskaMk);
		SimplePointMarker alaska = new SimplePointMarker(alaskaLoc);
		map.addMarker(alaska);
		
		Location sumatraLoc = new Location(3.30f, 95.98f);
		Feature sumatraEq = new PointFeature(sumatraLoc);
		sumatraEq.addProperty("title", "Off of Sumatra-Andaman Islands");
		sumatraEq.addProperty("magnitude", 9.1);
		sumatraEq.addProperty("date", "December 26, 2004");
		sumatraEq.addProperty("year", 2004);
		sumatraEq.addProperty("UTC - time", "00:58");
		Marker sumatraMk = new SimplePointMarker(sumatraLoc, sumatraEq.getProperties());
		map.addMarker(sumatraMk);
		SimplePointMarker sumatra = new SimplePointMarker(sumatraLoc);
		map.addMarker(sumatra);
		
		Location japanLoc = new Location(38.30f, 142.37f);
		Feature japanEq = new PointFeature(japanLoc);
		japanEq.addProperty("title", "East Coast of Honshu, Japan");
		japanEq.addProperty("magnitude", 9.1);
		japanEq.addProperty("date", "March 11, 2011");
		japanEq.addProperty("year", 2011);
		japanEq.addProperty("UTC - time", "05:46");
		Marker japanMk = new SimplePointMarker(japanLoc, japanEq.getProperties());
		map.addMarker(japanMk);
		SimplePointMarker japan = new SimplePointMarker(valLoc);
		map.addMarker(japan);
		
		Location kamchatkaLoc = new Location(52.62f, 159.78f);
		Feature kamchatkaEq = new PointFeature(kamchatkaLoc);
		kamchatkaEq.addProperty("title", "Off the East Coast of the Kamchatka Peninsula, Russia");
		kamchatkaEq.addProperty("magnitude", 9.0);
		kamchatkaEq.addProperty("date", "November 4, 1952");
		kamchatkaEq.addProperty("year", 1952);
		kamchatkaEq.addProperty("UTC - time", "16:58");
		Marker kamchatkaMk = new SimplePointMarker(kamchatkaLoc, kamchatkaEq.getProperties());
		map.addMarker(kamchatkaMk);
		SimplePointMarker kamchatka = new SimplePointMarker(kamchatkaLoc);
		map.addMarker(kamchatka);
		
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add((PointFeature) valEq);
		bigEqs.add((PointFeature) alaskaEq);
		bigEqs.add((PointFeature) sumatraEq);
		bigEqs.add((PointFeature) japanEq);
		bigEqs.add((PointFeature) kamchatkaEq);
		
		List<Marker> markers = new ArrayList<Marker>();
		for(PointFeature eq : bigEqs) {
			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
		}
		
		int yellow = color(255, 255, 0);
		int gray = color(150, 150, 150);
		for(Marker mk : markers) {
			if((int) mk.getProperty("year") > 2000) {
				mk.setColor(yellow);
			}else {
				mk.setColor(gray);
			}
		}
		
		map.addMarkers(markers);
	}
	
	
	public void draw() {
		
		background(10);
		map.draw();
		// addKey();
	}
}
