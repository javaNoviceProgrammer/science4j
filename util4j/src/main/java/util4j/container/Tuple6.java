package util4j.container;

public class Tuple6<U1, U2, U3, U4, U5, U6> {

	public final U1 first ;
	public final U2 second ;
	public final U3 third ;
	public final U4 forth ;
	public final U5 fifth ;
	public final U6 sixth ;
	
	public Tuple6(U1 first, U2 second, U3 third, U4 forth, U5 fifth, U6 sixth) {
		this.first = first ;
		this.second = second ;
		this.third = third ;
		this.forth = forth ;
		this.fifth = fifth ;
		this.sixth = sixth ;
	}

	public static <U1, U2, U3, U4, U5, U6> Tuple6<U1, U2, U3, U4, U5, U6> makeTuple(
			U1 first, 
			U2 second, 
			U3 third, 
			U4 forth,
			U5 fifth,
			U6 sixth) {
		return new Tuple6<U1, U2, U3, U4, U5, U6>(first, second, third, forth, fifth, sixth) ;
	}
	
	@Override
	public String toString() {
		return "(" + first + "," + second + "," + third + "," + forth + "," + fifth + "," + sixth + ")";
	}
	
}
