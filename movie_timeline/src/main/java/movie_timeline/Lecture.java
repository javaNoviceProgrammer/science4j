package movie_timeline;

public class Lecture {
	
	String name ; // lecture name
	int hours, minutes, seconds ; // duration: hours:minutes:seconds
	TimeStamp startStamp, endStamp ; // start & end time stamps
	TimeStampSpecifier specifier ; // format of time stamp
	int offsetSeconds ; // start time offset
	
	public Lecture(String name, int hours, int minutes, int seconds) {
		this.name = name ;
		this.hours = hours ;
		this.minutes = minutes ;
		this.seconds = seconds ;
		specifier = TimeStampSpecifier.DURATION ;
	}
	
	public Lecture(String name, int hours, int minutes, int seconds, TimeStampSpecifier specifier) {
		this.name = name ;
		this.hours = hours ;
		this.minutes = minutes ;
		this.seconds = seconds ;
		this.specifier = specifier ;
	}
	
	// format: "hh:mm:ss name"
	public static Lecture parseString(String str, TimeStampSpecifier specifier) {
		String temp = str.strip() ;
		// do nothing if first char is not number
		boolean isNumber = false ;
		char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'} ;
		for (char c : numbers) {
			if (temp.charAt(0) == c) {
				isNumber = true ;
				break ;
			}
		}
		if (!isNumber) return null ;
		// continue if first char is a number
		int index = temp.indexOf(' ') ;
		String name = temp.substring(index).strip() ;
		String[] stamp = temp.substring(0, index).split(":") ;
		Lecture lecture = new Lecture(name, Integer.parseInt(stamp[0]), Integer.parseInt(stamp[1]), Integer.parseInt(stamp[2])) ;
		return lecture ;
	}
	
	public Lecture setOffset(int offsetSeconds) {
		this.offsetSeconds = offsetSeconds ;
		return this ;
	}
	
	public Lecture setFormatSpecifier(TimeStampSpecifier specifier) {
		this.specifier = specifier ;
		return this ;
	}
	
	public int getTotalSeconds() {
		return hours*3600 + minutes*60 + seconds ;
	}
	
	public void setTotalSeconds(int totalSeconds) {
		hours = totalSeconds/3600 ;
		minutes = (totalSeconds-hours*3600)/60 ;
		seconds = totalSeconds - (hours*3600 + minutes*60) ;
	}
	
	public void setStartTimeStamp(int startTimeSeconds) {
		startStamp = new TimeStamp(startTimeSeconds) ;
	}
	
	public TimeStamp getStartTimeStamp() {
		if (startStamp==null) {
			startStamp = new TimeStamp(offsetSeconds) ;
		}
		return startStamp ;
	}
	
	public TimeStamp getEndTimeStamp() {
		if (endStamp==null) {
			endStamp = new TimeStamp(getTotalSeconds()+offsetSeconds) ;
		}
		return endStamp ;
	}

	// format: xx:xx:xx name
	@Override
	public String toString() {
		String str ;
		switch (specifier) {
			case START_TIME:
				str = "%s %s".formatted(getStartTimeStamp(), name) ;
				break ;
			case END_TIME:
				str = "%s %s".formatted(getEndTimeStamp(), name) ;
				break ;
			default:
				str = "%s %s".formatted(new TimeStamp(getTotalSeconds()), name) ;
				break ;
		}
		return str ;
	}
	
	public static void main(String[] args) {
		Lecture lecture1 = new Lecture("Introduction to mixed Java/C++ programming", 0, 19, 3) ;
		System.out.println(lecture1);
		lecture1.setOffset(100).setFormatSpecifier(TimeStampSpecifier.START_TIME) ;
		System.out.println(lecture1);
		
		Lecture lecture2 = Lecture.parseString("00:30:2 Introduction to java", TimeStampSpecifier.DURATION) ;
		System.out.println(lecture2);
	}
	
	
}
