package visualizer4j.results;

import java.util.List;

public interface AdvancedDataRetriever extends  AbstractDataRetriever {
	public abstract List<DataSeries> getChartValues(DataRetrievalOptions options, String methodName);
}
