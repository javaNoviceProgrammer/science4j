package visualizer4j.charts;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import org.jfree.chart.JFreeChart;
import visualizer4j.charts.Problem.Severity;

public class ChartContainer {

	private boolean multiple;
	private JFreeChart chart;
	private List<Problem> problems = new ArrayList<Problem>();

	// this reference is really ugly and should be removed in the future
	// the chart has a pointer to the problem label, so this panel can be refreshed
	// whenever problems are added
	public JLabel problemLabel;

	public void updateLabel() {
		if (problemLabel == null)
			return;
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");

		for (Problem p : this.getProblems()) {
			sb.append("<font color=");
			if (p.getSeverity() == Severity.ERROR) {
				sb.append("#DC0000");
			} else if (p.getSeverity() == Severity.WARNING) {
				sb.append("#FFB400");
			} else if (p.getSeverity() == Severity.INFORMATION) {
				sb.append("#3491C0");
			} else {
				sb.append("black");
			}

			sb.append(">");

			sb.append(p.getMessage());

			sb.append("</font><br>");
		}
		sb.append("</html>");

		problemLabel.setText(sb.toString());
	}

	public void setMultiple(boolean b) {
		multiple = b;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public List<Problem> getProblems() {
		return problems;
	}

	public void addProblem(Problem p) {
		if (!problems.contains(p)) {
			problems.add(p);
		}
	}
}
