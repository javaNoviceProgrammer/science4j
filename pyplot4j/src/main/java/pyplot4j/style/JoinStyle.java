package pyplot4j.style;

public enum JoinStyle {

	mitter("mitter") ,
	round("round") ,
	bevel("bevel") ;

	private String value ;

	private JoinStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
