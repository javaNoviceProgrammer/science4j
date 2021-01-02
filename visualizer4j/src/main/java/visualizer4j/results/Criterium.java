package visualizer4j.results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Criterium {

	private String criteriumName;
	private List<String> possibleValues = null;
	private boolean constant = false;

	public Criterium(String name, List<String> posVal) {
		this.criteriumName = name;
		this.possibleValues = posVal;
	}

	public Criterium(String name) {
		this.criteriumName = name;
	}

	@SuppressWarnings("unused")
	private Criterium() {
		constant = true;
	}

	public void setPossibleValues(List<String> l) {
		if (constant == true) {
			throw new IllegalStateException();
		}
		if (possibleValues != null) {
			throw new IllegalStateException("Cannot define criterium values only once");
		}
		possibleValues = l;
	}

	public void setPossibleValues(Collection<String> c) {
		if (possibleValues != null) {
			throw new IllegalStateException("Cannot define criterium values only once");
		}
		possibleValues = new ArrayList<String>(c.size());
		possibleValues.addAll(c);
	}

	public String getPossibleValue(int i) {
		return possibleValues.get(i);
	}

	public List<String> getPossibleValues() {
		return possibleValues;
	}

	public boolean isConstant() {
		return constant;
	}

	public String getName() {
		return criteriumName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(criteriumName);
		if (possibleValues != null && possibleValues.size() > 0) {
			sb.append("(");
			for (int i = 0 ; i < possibleValues.size() ; i++) {
				sb.append(possibleValues.get(i));
				if (i+1 < possibleValues.size()) {
					sb.append(", ");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}

}
