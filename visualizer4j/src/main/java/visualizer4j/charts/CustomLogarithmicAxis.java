package visualizer4j.charts;

import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.data.Range;

public class CustomLogarithmicAxis extends LogarithmicAxis {



	private static final long serialVersionUID = 2520708820879849081L;

	private double maxRange;

	public CustomLogarithmicAxis(String label, double maxRange) {
		super(label);
		this.maxRange = maxRange;
	}

	@Override
	public void autoAdjustRange() {
		setRange(new Range(0, this.maxRange), false, false);
	}
}
