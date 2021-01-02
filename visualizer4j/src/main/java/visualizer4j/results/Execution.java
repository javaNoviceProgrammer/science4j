package visualizer4j.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.general_libraries.utils.DateAndTimeFormatter;

public class Execution implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String application;
	private final String version;
	private final List<DataPoint> dataPoints = new ArrayList<DataPoint>();
	private final String date;

	public Execution(String application, String version, String date) {
		this.application = application;
		this.version = version;
		this.date = date;
	}

	public Execution(String application, String version) {
		this.application = application;
		this.version = version;
		this.date = DateAndTimeFormatter.getDate(System.currentTimeMillis());
	}
	
	public Execution() {
		this.application = "Unnamed application";
		this.version = "version 0.0";
		this.date = DateAndTimeFormatter.getDate(System.currentTimeMillis());
	}	
	

	public String getApplication() {
		return application;
	}

	public String getVersion() {
		return version;
	}

	public String getDate() {
		return date;
	}

	public void addDataPoint(DataPoint p) {
		if (p == null) {
			throw new NullPointerException();
		}
		dataPoints.add(p);
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void addArrayResult(String name, double[] trafFrom, DataPoint dp) {
		for (int i = 0 ; i < trafFrom.length ; i++) {
			DataPoint dp2 = dp.getDerivedDataPoint();
			dp2.addProperty("index", i);
			dp2.addResultProperty("array value", trafFrom[i]);
			dp2.addProperty("array name", name);
			this.addDataPoint(dp2);
		}
		
	}

	public void addArrayResult(String string, double[] val, String[] desc,
			DataPoint dp) {
		for (int i = 0 ; i < val.length ; i++) {
			DataPoint dp2 = dp.getDerivedDataPoint();
			dp2.addProperty("type_of "+string, desc[i]);
			dp2.addResultProperty("# " + string, val[i]);
			this.addDataPoint(dp2);
		}
		
	}
}