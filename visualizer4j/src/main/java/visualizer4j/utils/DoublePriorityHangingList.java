package visualizer4j.utils;

import java.util.Comparator;

class Criterium {
	Criterium(float f1, float f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	float f1;
	float f2;

	@Override
	public String toString() {
		return f1 + ":" + f2;
	}
}

public class DoublePriorityHangingList<T> extends AbstractSortedHangingList<T, Criterium> {

	@SuppressWarnings("unchecked")
	public DoublePriorityHangingList () {
		super(new Comparator() {

			public int compare(Object c1, Object c2) {
				float f = ((Criterium)c1).f1 - ((Criterium)c2).f1;
				if (f != 0) {
					return (int)Math.signum(f);
				}
				return (int)Math.signum(((Criterium)c1).f2 - ((Criterium)c2).f2);
			}
		});
	}

	public void add(T i, float crit1, float crit2) {
		super.add(i, new Criterium(crit1, crit2));
	}

	public void add(T i, int crit1, int crit2) {
		super.add(i, new Criterium(crit1, crit2));
	}

}
