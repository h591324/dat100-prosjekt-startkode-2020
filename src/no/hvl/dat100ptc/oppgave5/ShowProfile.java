package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start
	
		int x = MARGIN,y;
		
		setColor(0,0,255);
		
		
		
		for (int i = 0; i < gpspoints.length; i++) {			//lager en for-løkke med lengden til gpspoints
			double tempElevation = gpspoints[i].getElevation(); //henter elevation for hver i i gpspoints
			y = (int)(ybase - gpspoints[i].getElevation());
			
			if (tempElevation >= 0) {							//hvis elevation er større eller lik 0 printer vi ut grafen for punktet
				
				drawLine(x+i*2, ybase, x + i*2, y);				//tegner grafen med 2 pixler mellomrom og starter i ybase
			} 
		}

		
	}

}
