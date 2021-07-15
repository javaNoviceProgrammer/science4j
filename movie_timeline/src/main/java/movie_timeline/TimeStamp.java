package movie_timeline;

public class TimeStamp {
	
	int timeSeconds ;
	int hours, minutes, seconds ;
	
	public TimeStamp(int timeSeconds) {
		this.timeSeconds = timeSeconds ;
		hours = timeSeconds/3600 ;
		minutes = (timeSeconds-hours*3600)/60 ;
		seconds = timeSeconds - (hours*3600 + minutes*60) ;
	}
	
	public int getSeconds() {
		return timeSeconds ;
	}

	@Override
	public String toString() {
		return String.format("%s:%s:%s", Utils.toDoubleDigitString(hours), Utils.toDoubleDigitString(minutes), Utils.toDoubleDigitString(seconds)) ;
	}
	
	public static void main(String[] args) {
		TimeStamp stamp1 = new TimeStamp(0) ;
		System.out.println(stamp1);
		TimeStamp stamp2 = new TimeStamp(1333) ;
		System.out.println(stamp2);
	}

}
