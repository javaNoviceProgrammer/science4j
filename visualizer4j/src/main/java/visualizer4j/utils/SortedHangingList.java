package visualizer4j.utils;

import java.util.Collection;


public class SortedHangingList<T> extends AbstractSortedHangingList<T, Float> {

	/*	public void add(T i, float attached) {
		super.add(i, (Float)attached);
	}*/

	public void add(T i, int attached) {
		super.add(i, new Float(attached));
	}

	public boolean remove(float f) {
		return super.remove(f);
	}

	@Override
	public boolean addAll(Collection<? extends T> e) {
		for (T t : e) {
			this.add(t, (float)0);
		}
		return true;
	}



}
