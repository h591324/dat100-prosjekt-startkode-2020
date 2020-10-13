package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {
		
		//finner min ved å sette sette d < min

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		//lager en tabell med lengden til gpspoints og enter riktig verdi med getLatitude()

		double[] breddeTabell = new double[gpspoints.length];

		for (int i = 0; i < breddeTabell.length; i++) {

			breddeTabell[i] = gpspoints[i].getLatitude();
			;
		}

		return breddeTabell;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] lengdeTabell = new double[gpspoints.length];

		for (int i = 0; i < lengdeTabell.length; i++) {
			lengdeTabell[i] = gpspoints[i].getLongitude();
		}

		return lengdeTabell;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		//henter lat og long punkter for gpspoint 1 og 2
		//gjør om punktene til radianer og bruker Haversine-formelen

		double d;

		double lat1 = gpspoint1.getLatitude();
		double lon1 = gpspoint1.getLongitude();
		double lat2 = gpspoint2.getLatitude();
		double lon2 = gpspoint2.getLongitude();
		double latAvstand = toRadians(lat2 - lat1);
		double lonAvstand = toRadians(lon2 - lon1);
		double a = Math.sin(latAvstand / 2) * Math.sin(latAvstand / 2) + Math.cos(toRadians(lat1))
				* Math.cos(toRadians(lat2)) * Math.sin(lonAvstand / 2) * Math.sin(lonAvstand / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = R * c;

		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		//henter tid for gpspoint 1 og 2, sjekker hvilken har høyest verdi og gjør om til km/t

		int secs;
		double speed;
		int timeDif = 0;

		int sek1 = gpspoint1.getTime();
		int sek2 = gpspoint2.getTime();

		double avstand = distance(gpspoint1, gpspoint2);

		if (sek1 < sek2) {
			timeDif = sek2 - sek1;
		} else {
			timeDif = sek1 - sek2;
		}
		double meterPerSekund = avstand / timeDif;
		speed = (meterPerSekund * 60 * 60) / 1000;
		// double speed = (distance(gpspoint1, gpspoint2) / secs) * 3600;

		return speed;
	}

	public static String formatTime(int secs) {
		
		//lager variabler for timer, minutter og sekunder

		String timestr;
		String TIMESEP = ":";
		timestr = "  ";

		int hours = secs / 3600;

		if (hours >= 10) {
			timestr += hours;
		} else {
			timestr += 0;
			timestr += hours;
		}

		timestr += TIMESEP;

		int rest = hours * 3600;

		int seconds = secs - rest;

		int minutes = seconds / 60;

		if (minutes >= 10) {
			timestr += minutes;
		} else {
			timestr += 0;
			timestr += minutes;
		}

		timestr += TIMESEP;

		rest = minutes * 60;

		seconds = seconds - rest;

		if (seconds >= 10) {
			timestr += seconds;
		} else {
			timestr += 0;
			timestr += seconds;
		}
		
		timestr = String.format("%10s", timestr); //bruker String.format for å sette totale lengden på 10

		return timestr;

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		str = String.format("%.2f", d); //setter 2 desimaler
		str = String.format("%10s", str); //totale lengden på strengen blir 10

		return str;

	}
}
