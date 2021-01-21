package util4j.container;

public class Pair<U,V> {
	
	public final U first ;
	public final V second ;
	
	public Pair(U first, V second) {
		this.first = first ;
		this.second = second ;
	}
	
	public static <U,V> Pair<U,V> makePair(U first, V second) {
		return new Pair<U,V>(first, second) ;
	}

}
