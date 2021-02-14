package util4j.container;

public class Pair<U,V> extends Tuple2<U, V> {
	
	public Pair(U first, V second) {
		super(first, second) ;
	}
	
	public static <U,V> Pair<U,V> makePair(U first, V second) {
		return new Pair<U,V>(first, second) ;
	}
	
	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}

}
