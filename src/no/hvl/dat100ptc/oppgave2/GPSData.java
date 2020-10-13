package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		
		// lager en konstruktør som oppretter en tabell av GPS punkter

		gpspoints= new GPSPoint[n];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if (antall < gpspoints.length) {
			// hvis antall er mindre enn lengden av tabellen, så setter vi inn gpspoint
			
			gpspoints[antall] = gpspoint;
			// øker variabelen antall med 1 for hver gang slik at neste punkt kommmer inn på neste posisjon
			
			antall++;
		
			inserted = true;
		
			
		}
		
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		// koverterer data ved å kalle på klassen GPSDataConverter.convert

		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		// oppretter et nytt GPSPoint objekt og bruker insertGPS metoden fra forrige oppgave
		boolean inserted = insertGPS(gpspoint);
		
		return inserted;
		
	}

	public void print() {

System.out.println("====== Konvertert GPS Data - START ======");
		
		
		for (antall = 0; antall < gpspoints.length; antall++)
			
			System.out.println(gpspoints[antall].toString());

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
