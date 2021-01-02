package visualizer4j.results;

public interface AbstractResultsManager {

	public void addExecution(Execution e);

	public float confidenceInterval(CriteriumSet cs, String metric);

	public void clear();

	public void terminate();

	public void addDataPoint(DataPoint dp);

	/*
	public class Adapter {
		public static Execution getExecution(Map<String, String> M1, Map<String, String> m2, Execution exec) {
			if (exec == null) {
				exec = new Execution("", "", null);
			}
			DataPoint dp = new DataPoint();
			for (Map.Entry<String, String> en : M1.entrySet()) {
				dp.addProperty(new Property(en.getKey(), en.getValue()));
			}
			for (Map.Entry<String, String> en : m2.entrySet()) {
				dp.addProperty(new ResultProperty(en.getKey(), en.getValue()));
			}
			exec.addDataPoint(dp);
			return exec;
		}

	}*/
}