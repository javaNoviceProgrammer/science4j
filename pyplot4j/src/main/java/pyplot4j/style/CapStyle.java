package pyplot4j.style;

public enum CapStyle {

	butt("butt") ,
	round("round") ,
	projecting("projecting") ;

	private String value ;

	private CapStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
