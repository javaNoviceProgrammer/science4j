package util4j.container;

public class Tuples {
	
	private Tuples() {
		
	}

	public static <U,V> Pair<U,V> makePair(U first, V second) {
		return new Pair<U,V>(first, second) ;
	}
	
	public static <U,V> Pair<U,V> makeTuple(U first, V second) {
		return new Pair<U,V>(first, second) ;
	}
	
	public static <U1, U2, U3> Tuple3<U1, U2, U3> makeTuple(U1 first, U2 second, U3 third) {
		return new Tuple3<U1, U2, U3>(first, second, third) ;
	}
	
	public static <U1, U2, U3, U4> Tuple4<U1, U2, U3, U4> makeTuple(U1 first, U2 second, U3 third, U4 forth) {
		return new Tuple4<U1, U2, U3, U4>(first, second, third, forth) ;
	}
	
	public static <U1, U2, U3, U4, U5> Tuple5<U1, U2, U3, U4, U5> makeTuple(
			U1 first, 
			U2 second, 
			U3 third, 
			U4 forth,
			U5 fifth) {
		return new Tuple5<U1, U2, U3, U4, U5>(first, second, third, forth, fifth) ;
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
	
	
	public static void main(String[] args) {
		String name = "Hi" ;
		int id = 23 ;
		Pair<Integer, String> pair1 = Tuples.makeTuple(id, name) ;
		System.out.println(pair1);
		System.out.println(pair1.first);
		System.out.println(pair1.second);
		System.out.println(pair1.second.codePointAt(0));
		System.out.printf("0x%x\n", pair1.second.codePointAt(0)) ;
		System.out.println(pair1);
	}
	
}
