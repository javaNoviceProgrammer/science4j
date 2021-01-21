package util4j.container;

public class Tuple3<U1, U2, U3> {

	public final U1 first ;
	public final U2 second ;
	public final U3 third ;
	
	public Tuple3(U1 first, U2 second, U3 third) {
		this.first = first ;
		this.second = second ;
		this.third = third ;
	}

	public static <U1, U2, U3> Tuple3<U1, U2, U3> makeTuple3(U1 first, U2 second, U3 third) {
		return new Tuple3<U1, U2, U3>(first, second, third) ;
	}
	
}
