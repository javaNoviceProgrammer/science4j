package visualizer4j.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;

public class Chart {

	private VectorSeriesCollection col;
	private JFreeChart jFreeChart;
	
	public Chart() {
		this("x", "y");
	}
		
	public Chart(String xTitle, String yTitle) {	
		jFreeChart = ChartFactory.createXYLineChart("", xTitle,
				yTitle, null, PlotOrientation.VERTICAL, true, false,
					false);
		BasicStroke stroke = new BasicStroke(2, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 1.0f, null, 0);					
		DefaultXYItemRenderer renderer = new DefaultXYItemRenderer();
		col = new VectorSeriesCollection();
		renderer.setBaseStroke(stroke);

		renderer.setBasePaint(Color.CYAN);
		renderer.setAutoPopulateSeriesFillPaint(true);
		renderer.setBaseSeriesVisibleInLegend(true);
		jFreeChart.getXYPlot().setRenderer(0, renderer);
		jFreeChart.getXYPlot().setDataset(0, col);	
	}
	
	/**
	 * Permits an access to the underlying jFreeChart
	 */
	public JFreeChart getJFreeChart() {
		return jFreeChart;
	}
	
	/**
	 * Add a new serie (with legend "name") as a list of (x,y) points.
	 * The x and y parameters must double arrays. These arrays must have the same length
	 */	
	public void addSerie(double[] x, double[] y, String name) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("x and y vectors must have the same length");
		}
		if (name == null) name = "";
		VectorSeries s = new VectorSeries(name);
		for (int i = 0 ; i < x.length ; i++) {
			s.add(x[i],y[i],0,0);
		}
		col.addSeries(s);
	}
	
	/**
	 * Add a new serie (with legend "name") as a list of y points (x are numbered from 0 to 
	 * <code>y.length - 1</code>).
	 * The y parameter is a double array
	 */	
	public void addSerie(double[] y, String name) {
		double[] x = new double[y.length];
		for (int i = 0 ; i < x.length ; i++) {
			x[i] = i;
		}
		addSerie(x,y,name);
	}
	
	/**
	 * Add a new serie (with legend "name") as a list of y points (x are numbered from 0 to 
	 * <code>y.length - 1</code>).
	 * The y parameter is a list of numbers
	 */	
	public void addSerie(ArrayList f, String name) {
		try {
			double[] d = new double[f.size()];
			for (int i = 0 ; i < f.size() ; i++) {
				d[i] = ((Number)f.get(i)).doubleValue();
			}
			addSerie(d, name);
		}
		catch (Exception e) {}
	}
	
	/**
	 * Remove all series associated with current chart
	 */ 
	public void clearSeries() {
		col.removeAllSeries();
	}
	
	public void popupChart() {
		ChartPanel chartPanel = new ChartPanel(jFreeChart, true, // properties
				true, // save
				true, // print
				true, // zoom
				false); // tooltips
		JDialog diag = new JDialog();
		diag.add(chartPanel);
		diag.setSize(400, 400);
		diag.setVisible(true);
	}
	
	
}
