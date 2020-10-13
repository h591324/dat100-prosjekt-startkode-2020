package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		//finner max og min latitudes
		
		double maxlon = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return ystep;
		
	}

	public void showRouteMap(int ybase) {
		//finner min latitude og longitude fra gpspoints
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minLon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		int radius = 5; //setter radius på 5 pixler
		setColor(0,255,0); //farge = grønn
		int x,y;
		

		for (int i = 0; i < gpspoints.length; i++) {
			//henter longitude for punkt i og tar minus minste long og ganger med xstep
			x = (int)((gpspoints[i].getLongitude() - minLon)*xstep()) + MARGIN;	
			y = ybase - (int)((gpspoints[i].getLatitude() - minLat)*ystep());
			
			fillCircle(x,y,radius); //tegner en fyllt sirkel for hvert punkt
			
			
			
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int ybase = 20;
		

		setColor(0,0,0);
		setFont("Courier",12);
		
		//skriver ut en string i easygraphics med String.format slik at det ser pent ut
		
		drawString("Time: " + (gpscomputer.totalTime()/3600) + ":" + (gpscomputer.totalTime() / 60 % 60)
		+ ":" + (gpscomputer.totalTime()%60), TEXTDISTANCE, ybase);
		drawString(String.format("Average speed: %.2f", (gpscomputer.averageSpeed())) + "km/t", 
				TEXTDISTANCE, ybase + 20);
		drawString(String.format("Total distance: %.2f", (gpscomputer.totalDistance())) + "km",
				TEXTDISTANCE, ybase + 40);
		drawString(String.format("Total elevation: %.2f", (gpscomputer.totalElevation())) + "elevation",
				TEXTDISTANCE, ybase + 60);
		drawString(String.format("Total kcal: %.2f", (gpscomputer.totalKcal(80))) + "kcal",
				TEXTDISTANCE, ybase + 80);
	}

}
