package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ParetoSet<X extends ParetoPoint> implements Iterable<X> {
	
	private ArrayList<X> points;
	
	private int dimensions;
	
	public ParetoSet(int dimensions) {
		this.dimensions = dimensions;
		points = new ArrayList<X>();
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public void addCandidates(ArrayList<X> results) {
		for (X x : results) {
			addCandidate(x);
		}
	}
	
	public boolean addCandidate(X pp) {
		if (pp.getDimensions() > dimensions) throw new IllegalStateException();
		if (points.size() == 0) {
			points.add(pp);
			return true;
		}
		// a point is added :
			// 1. as soon as it dominates another
				// in which case dominated point must be removed
			// 2. If it is dominated by no existing point
		ArrayList<X> toRem = new ArrayList<X>();
		boolean keep = false;
		boolean passedAll = true;
		for (int i = 0 ; i < points.size() ; i++) {
			X alt = points.get(i);
			if (pp.dominates(alt)) {
				toRem.add(alt);
				keep = true;
			}
			if (keep == false) {
				if (alt.dominates(pp)) {
					passedAll = false;
					break;
				}
			}
		}
		if (keep || passedAll) {
			points.removeAll(toRem);
			points.add(pp);
			return true;
		}
		return false;
	}
	
	public static class ParetoPoint3DTest extends ParetoPoint {
		
		private int[] val;
		
		public ParetoPoint3DTest(int a, int b, int c) {
			val = new int[]{a,b,c};
		}

		@Override
		public double getValueOfDimensionN(int n) {
			return val[n];
		}

		@Override
		public boolean isDimensionNtheHigherTheBetter(int n) {
			return false;
		}

		@Override
		public int getDimensions() {
			return 3;
		}
		
		public String toString() {
			return Arrays.toString(val);
		}
	}
	
//	@Test
//	public static void testPareto() {
//		ParetoSet<ParetoPoint3DTest> ps = new ParetoSet<ParetoPoint3DTest>(3);
//		ps.addCandidate(new ParetoPoint3DTest(1,4,8));
//		ps.addCandidate(new ParetoPoint3DTest(1,3,8));
//		ps.addCandidate(new ParetoPoint3DTest(1,2,8));
//		ps.addCandidate(new ParetoPoint3DTest(2,4,8));
//		ps.addCandidate(new ParetoPoint3DTest(2,3,8));
//		ps.addCandidate(new ParetoPoint3DTest(2,2,8));
//		ps.addCandidate(new ParetoPoint3DTest(2,1,8));
//		ps.addCandidate(new ParetoPoint3DTest(3,4,8));
//		ps.addCandidate(new ParetoPoint3DTest(3,3,8));
//		ps.addCandidate(new ParetoPoint3DTest(3,2,8));
//		ps.addCandidate(new ParetoPoint3DTest(3,1,8));
//		ps.addCandidate(new ParetoPoint3DTest(4,4,8));
//		ps.addCandidate(new ParetoPoint3DTest(4,3,8));
//		ps.addCandidate(new ParetoPoint3DTest(4,2,8));
//		ps.addCandidate(new ParetoPoint3DTest(4,1,8));
//		ps.addCandidate(new ParetoPoint3DTest(8,4,7));
//		ps.addCandidate(new ParetoPoint3DTest(1,5,7));
//		ps.addCandidate(new ParetoPoint3DTest(10,8,8));
//		ps.addCandidate(new ParetoPoint3DTest(7,1,6));
//		
//		System.out.println(ps.points);
//		
//	}
//	
//	public static void main(String[] args) {
//		testPareto();
//	}

	@Override
	public Iterator<X> iterator() {
		return points.iterator();
	}
	
}
