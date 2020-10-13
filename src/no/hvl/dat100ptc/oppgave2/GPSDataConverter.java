package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
	// skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26

	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {

		int secs;
		int hr, min, sec;
		
		// gjør om index nr 11-13 om til en int fra string

		String s = timestr.substring(11, 13);
		hr = Integer.parseInt(s);

		String s1 = timestr.substring(14, 16);
		min = Integer.parseInt(s1);

		String s2 = timestr.substring(17, 19);
		sec = Integer.parseInt(s2);
		
		// gjør så om timer og minuter til sekunder

		secs = (hr * 60 * 60) + (min * 60) + sec;

		System.out.println(secs);

		return secs;

	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		
		// kaller først på toSeconds fra oppgave a for å konvertere string timeStre til int
        // kaller så på double klassen for å bruke parsedouble til å konvertere latitude, longitude og elevation til double

		int time = toSeconds(timeStr);

		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		//oppretter så et nytt variabelnavn som peker på GPSPoint objektet

		GPSPoint gpspoint = new GPSPoint(time, latitude, longitude, elevation);

		return gpspoint;
	}

}
