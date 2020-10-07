package org.gsl4j.plot.contour;

import static java.lang.String.format;

import java.io.File;
import java.util.ArrayList;

import org.gsl4j.io.FileOutput;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.plot.util.TerminalExecutor;


/**
 * matplotlib.pyplot.contour(*args, data=None, **kwargs)
 * contour([X, Y,] Z, [levels], **kwargs)
 *
 * @author Meisam
 *
 */
public class ContourPlot {

	String title = null ;
	String xlabel = null ;
	String ylabel = null ;
	boolean grid = false ;
	boolean cla = false ;
	String gridWhich = null ;
	String gridAxis = null ;
	String xlim = null ;
	String ylim = null ;
	// legend
	boolean legend = false ;
	String legendLocation ;
	// other properties
	boolean tightLayout = false ;
	String style ;
	// contour series
	ArrayList<ContourSeries> contourSeriesCollection ;
	int count = 1 ;
	public boolean isSubplot = false ;
	static int id = 0 ;


	public ContourPlot(String title) {
		this.title = title ;
		contourSeriesCollection = new ArrayList<>() ;
	}

	public ContourPlot() {
		this.title = null ;
		contourSeriesCollection = new ArrayList<>() ;
	}

	public ContourSeries contour(double[] x, double[] y, MeshGrid func) {
		ContourSeries series = new ContourSeries(x, y, func) ;
		series.setXvar("x"+count).setYvar("y"+count).setZvar("z"+count) ;
		series.clabel().name("cs"+count) ;
		contourSeriesCollection.add(series) ;
		count++ ;
		return series ;
	}

	public ContourSeries contour(double[] x, double[] y, double[][] z) {
		ContourSeries series = new ContourSeries(x, y, z) ;
		series.setXvar("x"+count).setYvar("y"+count).setZvar("z"+count) ;
		series.clabel().name("cs"+count) ;
		contourSeriesCollection.add(series) ;
		count++ ;
		return series ;
	}

	// clabel of the last contour
	public ContourLabel clabel() {
		return contourSeriesCollection.get(count-2).clabel() ;
	}


	public ContourPlot xlabel(String xlabel) {
		this.xlabel = xlabel ;
		return this ;
	}

	public ContourPlot ylabel(String ylabel) {
		this.ylabel = ylabel ;
		return this ;
	}

	public ContourPlot title(String title) {
		this.title = title ;
		return this ;
	}

	public ContourPlot xlim(double xmin, double xmax) {
		this.xlim = format("[%f, %f]", xmin, xmax) ;
		return this ;
	}

	public ContourPlot ylim(double ymin, double ymax) {
		this.ylim = format("[%f, %f]", ymin, ymax) ;
		return this ;
	}

	public ContourPlot grid(boolean on, String which, String axis) {
		this.grid = on ;
		this.gridWhich = which ;
		this.gridAxis = axis ;
		return this ;
	}

	public ContourPlot grid(boolean on) {
		this.grid = on ;
		this.gridWhich = "both" ;
		this.gridAxis = "both" ;
		return this ;
	}

	public ContourPlot legend(boolean on) {
		this.legend = on ;
		return this ;
	}

	public ContourPlot legend(boolean on, String loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.trim() : null ;
		return this ;
	}

	public ContourPlot legend(boolean on, LegendLocation loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.toString().trim() : null ;
		return this ;
	}

	public ContourPlot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public ContourPlot cla() {
		this.cla = true ;
		return this ;
	}




	public void savefig(String fileName) {
		if(contourSeriesCollection.isEmpty())
			throw new IllegalStateException("ContourPlot data is empty") ;
		// open the output stream
		int index = fileName.lastIndexOf('.') ;
		String pyName = fileName.substring(0, index) ;
		FileOutput fo = new FileOutput(pyName+".py") ;
		pythonCode(fo);
		// show the plot
		if(cla) {
			fo.println("plt.cla()");
		}
		fo.println(format("plt.savefig('%s')", fileName));
		// close the output stream
		fo.close();
		// run the python code
		Thread thread = new Thread(() -> {
			TerminalExecutor.execute("python", fo.getFilename());
		}) ;
		thread.start();
	}


	public void show() {
		// open the output stream
		File file = new File("contour_plot_"+(id++)) ;
		file.deleteOnExit();
		FileOutput fo = new FileOutput(file) ;
		pythonCode(fo);
		// show the plot
		if(cla) {
			fo.println("plt.cla()");
		}
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
		if(!isSubplot) {
			fo.println("from sys import platform as sys_pf") ;
			fo.println("if sys_pf == 'darwin':") ;
			fo.println("\timport matplotlib") ;
			fo.println("\tmatplotlib.use('TkAgg')") ;
			fo.println("import numpy as np") ;
			fo.println("import matplotlib.pyplot as plt");
			fo.println();
		}
		// print x and y values
		for(ContourSeries contourSeries : contourSeriesCollection) {
			// print x
			fo.print(contourSeries.xvar + " = [");
			fo.printcomma(contourSeries.x);
			fo.print("];") ;
			fo.println();
			// print y
			fo.print(contourSeries.yvar + " = [");
			fo.printcomma(contourSeries.y);
			fo.print("];") ;
			fo.println();
			// contour plot
			fo.println(contourSeries.toString());
		}
		// configure the plot
		if(title != null)
			fo.println(format("plt.title('%s')", title)) ;
		if(xlabel != null)
			fo.println(format("plt.xlabel('%s')", xlabel)) ;
		if(ylabel != null)
			fo.println(format("plt.ylabel('%s')", ylabel)) ;
		if(xlim != null)
			fo.println(format("plt.xlim(%s)", xlim));
		if(ylim != null)
			fo.println(format("plt.ylim(%s)", ylim));
		if(grid)
			fo.println(format("plt.grid(%s, which='%s', axis='%s')", "True", gridWhich, gridAxis)) ;
		else
			fo.println(format("plt.grid(%s, which='%s', axis='%s')", "False", gridWhich, gridAxis)) ;
		if(legend) {
			if(legendLocation != null)
				fo.println(format("plt.legend(loc='%s')", legendLocation)) ;
			else
				fo.println("plt.legend()");
		}

		if(tightLayout)
			fo.println("plt.tight_layout()");

	}

}
