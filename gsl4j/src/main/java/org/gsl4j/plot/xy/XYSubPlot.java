package org.gsl4j.plot.xy;

import static java.lang.String.format;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gsl4j.io.FileOutput;
import org.gsl4j.plot.util.TerminalExecutor;


public class XYSubPlot {

	List<XYPlot> xyplotSeries ;
	Map<XYPlot, int[]> subplotIndex ;
	// parameters
	String tightLayout ;
	static int id = 0 ;

	public XYSubPlot() {
		xyplotSeries = new ArrayList<>() ;
		subplotIndex = new HashMap<>() ;
	}

	public XYPlot subplot(int row, int column, int index) {
		XYPlot xyplot = new XYPlot() ;
		xyplot.isSubplot = true ;
		xyplotSeries.add(xyplot) ;
		subplotIndex.put(xyplot, new int[] {row, column, index}) ;
		return xyplot ;
	}


	public XYSubPlot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public void savefig(String fileName) {
		if(xyplotSeries.isEmpty())
			throw new IllegalStateException("SubPlots data is empty") ;
		// open the output stream
		int index = fileName.lastIndexOf('.') ;
		String pyName = fileName.substring(0, index) ;
		FileOutput fo = new FileOutput(pyName+".py") ;
		pythonCode(fo);
		// save fig
		fo.println(format("plt.savefig('%s')", fileName));
		// close the output stream
		fo.close();
		// run the python code
		Thread thread = new Thread(() -> {
			TerminalExecutor.execute("python", fo.getFilename());
		}) ;
		thread.start();
	}

	public void show(String fileName) {
		if(xyplotSeries.isEmpty())
			throw new IllegalStateException("SubPlots data is empty") ;
		// open the output stream
		FileOutput fo = new FileOutput(fileName) ;
		pythonCode(fo);
		// show the plot
		fo.println("plt.show()");
		// close the output stream
		fo.close();
		// run the python code
		Thread thread = new Thread(() -> {
			TerminalExecutor.execute("python", fo.getFilename());
		}) ;
		thread.start();
	}

	public void show() {
		if(xyplotSeries.isEmpty())
			throw new IllegalStateException("SubPlots data is empty") ;
		// open the output stream
		File file = new File("xy_subplot_"+(id++)) ;
		file.deleteOnExit();
		FileOutput fo = new FileOutput(file) ;
		pythonCode(fo);
		fo.println("plt.show()");
		// close the output stream
		fo.close();
		// run the python code
		Thread thread = new Thread(() -> {
			TerminalExecutor.execute("python", fo.getFilename());
		}) ;
		thread.start();
	}


	void pythonCode(FileOutput fo) {
		// import header
		fo.println("from sys import platform as sys_pf") ;
		fo.println("if sys_pf == 'darwin':") ;
		fo.println("\timport matplotlib") ;
		fo.println("\tmatplotlib.use('TkAgg')") ;
		fo.println("import matplotlib.pyplot as plt");
		fo.println();
		// for each xy series, write the data
		for(XYPlot series: xyplotSeries) {
			// print subplot command
			int[] vals = subplotIndex.get(series) ;
			fo.println(format("plt.subplot(%d, %d, %d)", vals[0], vals[1], vals[2]));
			// print each subplot
			series.pythonCode(fo);
		}
		if(tightLayout != null)
			fo.println("plt.tight_layout()");
	}



}
