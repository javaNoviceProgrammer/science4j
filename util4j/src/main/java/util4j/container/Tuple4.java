package util4j.container;

public class Tuple4<U1, U2, U3, U4> {

	public final U1 first ;
	public final U2 second ;
	public final U3 third ;
	public final U4 forth ;
	
	public Tuple4(U1 first, U2 second, U3 third, U4 forth) {
		this.first = first ;
		this.second = second ;
		this.third = third ;
		this.forth = forth ;
	}

	public static <U1, U2, U3, U4> Tuple4<U1, U2, U3, U4> makeTuple(U1 first, U2 second, U3 third, U4 forth) {
		return new Tuple4<U1, U2, U3, U4>(first, second, third, forth) ;
	}
	
	@Override
	public String toString() {
		return "(" + first + "," + second + "," + third + "," + forth + ")";
	}
	
}
