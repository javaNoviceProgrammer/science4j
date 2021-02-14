package util4j.container;

public class Tuple2<U1, U2> {

	public final U1 first ;
	public final U2 second ;
	
	public Tuple2(U1 first, U2 second) {
		this.first = first ;
		this.second = second ;
	}

	public static <U1, U2> Tuple2<U1, U2> makeTuple(U1 first, U2 second) {
		return new Tuple2<U1, U2>(first, second) ;
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
	
}
