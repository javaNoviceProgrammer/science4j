package visualizer4j.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeFormatter {

	public static String getDateAndTime(long l) {
		Date date = new Date(l);
		SimpleDateFormat f1 = new SimpleDateFormat("_yyyy_MM_dd");
		SimpleDateFormat f2 = new SimpleDateFormat("'at'_HH_mm_ss");
		return f1.format(date)+"_"+f2.format(date);
	}

	public static String getDate(long l) {
		Date date = new Date(l);
		SimpleDateFormat f1 = new SimpleDateFormat("_yyyy_MM_dd");
		return f1.format(date);
	}

	public static String getTime(long l) {
		Date date = new Date(l);
		SimpleDateFormat f2 = new SimpleDateFormat("HH_mm_ss");
		return f2.format(date);
	}

}
