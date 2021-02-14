package util4j.container;

public class Tuple5<U1, U2, U3, U4, U5> {

	public final U1 first ;
	public final U2 second ;
	public final U3 third ;
	public final U4 forth ;
	public final U5 fifth ;
	
	public Tuple5(U1 first, U2 second, U3 third, U4 forth, U5 fifth) {
		this.first = first ;
		this.second = second ;
		this.third = third ;
		this.forth = forth ;
		this.fifth = fifth ;
	}

	public static <U1, U2, U3, U4, U5> Tuple5<U1, U2, U3, U4, U5> makeTuple(
			U1 first, 
			U2 second, 
			U3 third, 
			U4 forth,
			U5 fifth) {
		return new Tuple5<U1, U2, U3, U4, U5>(first, second, third, forth, fifth) ;
	}
	
	@Override
	public String toString() {
		return "(" + first + "," + second + "," + third + "," + forth + "," + fifth + ")";
	}
	
}
