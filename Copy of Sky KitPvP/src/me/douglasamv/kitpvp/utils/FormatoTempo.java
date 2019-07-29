package me.douglasamv.kitpvp.utils;

public class FormatoTempo {
	
	public static String tipo1(Integer i) {
		int minutes = i.intValue() / 60;
		int seconds = i.intValue() % 60;
		String disMinu = (minutes < 10 ? "" : "") + minutes;     
		String disSec = (seconds < 10 ? "0" : "") + seconds;
		String formattedTime = disMinu + ":" + disSec;
		return formattedTime;
	}
	
	public static String tipo2(Integer i) {
		if (i.intValue() >= 60) {
			Integer time = Integer.valueOf(i.intValue() / 60);
		       String add = "";
		if (time.intValue() > 1) {
		    add = "s";
		}
		return time + " minuto" + add;
		}
		Integer time = i;
		String add = "";
		if (time.intValue() > 1) {
			add = "s";
		}
		return time + " segundo" + add;
	}

}
