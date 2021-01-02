package visualizer4j.results;

import java.util.List;
import java.util.Map;

public class DataRetrievalOptions {

	public String[] method;
	public String xAxisProperty;
	public Map<String, List<String>> filters;
	public boolean isMeanOrSum;
	public boolean isMedian;
	public boolean isFirst;
	public boolean isMax;
	public boolean isConfInt;
	public boolean isQuartInt;
	public boolean is95;
	public int confInt;
	public boolean isMeanInsteadOfSum;
	public CriteriumSet criterias;
	
	public DataRetrievalOptions() {}

	public DataRetrievalOptions(String[] method, String xAxisProperty, CriteriumSet criterias,
			Map<String, List<String>> filters,
			boolean isMeanOrSum,
			boolean isMedian,
			boolean isFirst,
			boolean isMax,
			boolean isConfInt,
			boolean isQuartInt,
			int confInt, boolean is95, boolean isMeanInsteadOrSum) {
		this.method = method;
		this.method[0] = method[0].equals("") ? null : this.method[0];
		this.is95 = is95;
		this.xAxisProperty = xAxisProperty;
		this.filters = filters;
		this.confInt = confInt;
		this.isMeanInsteadOfSum = isMeanInsteadOrSum;
		this.isMeanOrSum =isMeanOrSum;
		this.isMedian = isMedian;
		this.isFirst = isFirst;
		this.isMax =isMax;
		this.isConfInt = isConfInt;
		this.isQuartInt = isQuartInt;
		this.criterias = criterias;
	}

	public String getYLabel() {
		if (method.length > 1 && !method[1].isEmpty()) {
			return method[0] + " & " + method[1];
		} else {
			return method[0];
		}
	}

/*	public String getXAxis() {
		return xAxisProperty;
	}*/
	
/*	public String getYAxis() {
		return method[0];
	}	*/

	public boolean hasSecondMethod() {
		return method.length > 1;
	}
	
	public CriteriumSet getCriteriumSet() {
		return criterias;
	}

	public Map<String, List<String>> getFilters() {
		return filters;
	}	

/*	public boolean indicatesConstant() {
		List<Criterium> colors = criterias.get(0);
		return (colors.size() == 1 && colors.get(0).isConstant());
	}*/

/*	public boolean hasCriteria(int index) {
		return criterias.size() >= index+1;
	}*/

/*	public int nbCriterias() {
		return criterias.size();
	}*/

/*	public List<String> getCriteriasAsString(int index) {
		List<Criterium> l = criterias.get(index);
		ArrayList<String> L = new ArrayList<String>(l.size());
		for (int i = 0 ; i < l.size() ; i++) {
			L.add(l.get(i).getName());
		}
		return L;
	}*/
}
