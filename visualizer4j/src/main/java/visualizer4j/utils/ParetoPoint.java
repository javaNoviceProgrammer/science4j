package visualizer4j.utils;


public abstract class ParetoPoint {
	public abstract double getValueOfDimensionN(int n);
	public abstract boolean isDimensionNtheHigherTheBetter(int n);
	public abstract int getDimensions();
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int dim = getDimensions();
		for (int i = 0 ; i < dim ; i++) {
			if (isDimensionNtheHigherTheBetter(i)) {
				sb.append( "/\\");
			} else {
				sb.append("\\/");
			}
			sb.append(getValueOfDimensionN(i));
			if (i < dim-1) {
				sb.append (" \t ");
			}
		}
		return sb.toString();
	}

	public boolean dominates(ParetoPoint p) {
		int dim = this.getDimensions();
		if (dim != p.getDimensions()) throw new IllegalStateException();
		boolean domInOne = false;
		for (int i = 0 ; i < dim ; i++) {
			double thidV = this.getValueOfDimensionN(i);
			double altV = p.getValueOfDimensionN(i);
			if (isDimensionNtheHigherTheBetter(i)) {
				if (thidV < altV)
					return false;
				if (thidV > altV)
					domInOne = true;
			} else {
				if (thidV > altV)
					return false;
				if (thidV < altV)
					domInOne = true;
			}
		}
		return domInOne;
	}
}
