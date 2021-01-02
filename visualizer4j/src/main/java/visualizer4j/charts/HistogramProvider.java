package visualizer4j.charts;

import java.util.HashSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import visualizer4j.statistics.StatisticalDistribution;


public class HistogramProvider {
	
	public static JFreeChart getHistogram(double[] rVals, String title) {
			HistogramDataset histogramdataset = new HistogramDataset();
			histogramdataset.setType(HistogramType.RELATIVE_FREQUENCY);
			JFreeChart jFreeChart = ChartFactory.createHistogram(title, null, null, histogramdataset, PlotOrientation.VERTICAL, false, false, false);
			jFreeChart.getXYPlot().setForegroundAlpha(0.75F);
	
			double maxValue = -Float.MAX_VALUE;
			double minValue = Float.MAX_VALUE;

			HashSet<Double> sort = new HashSet<Double>();
			
			StatisticalDistribution.DoubleDistribution dis = new StatisticalDistribution.DoubleDistribution();
			dis.add(rVals);
	
			for (int i = 0 ; i < rVals.length ; i++) {
				if (rVals[i] >= maxValue) {
					maxValue = rVals[i];
				}
				if (rVals[i] <= minValue) {
					minValue = rVals[i];
				}
				sort.add(rVals[i]);			
			}
			double ranger = (maxValue-minValue)*0.1f;			
			histogramdataset.addSeries("legen", rVals, Math.min(sort.size(),15));
			
			double maxY = 0;
			for (int i = 0 ; i < histogramdataset.getItemCount(0) ; i++) {
				double d = histogramdataset.getEndYValue(0,i);
				if (d > maxY) maxY = d;
			}

			if (minValue != maxValue) {
				jFreeChart.getXYPlot().getDomainAxis().setRange(minValue-ranger, maxValue+ranger);
			} else {
				jFreeChart.getXYPlot().getDomainAxis().setRange(minValue-1, maxValue+1);				
			}
			jFreeChart.getXYPlot().getRangeAxis().setRange(0, maxY*1.1);
			
			return jFreeChart;		
	}
}
