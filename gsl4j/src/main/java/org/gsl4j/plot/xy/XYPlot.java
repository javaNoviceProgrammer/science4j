package org.gsl4j.plot.xy;

import static java.lang.String.format;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import org.gsl4j.io.FileOutput;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.plot.util.TerminalExecutor;


public class XYPlot {

	String title = null ;
	String xlabel = null ;
	String ylabel = null ;
	boolean grid = false ;
	boolean cla = false ;
	String gridWhich = "both" ;
	String gridAxis = "both" ;
	String xlim = null ;
	String ylim = null ;
	// legend
	boolean legend = false ;
	String legendLocation ;
	// ticks
	double[] xticks ;
	String[] xtickLabels ;
	double[] yticks ;
	String[] ytickLabels ;
	// other properties
	boolean tightLayout = false ;
	// data
	ArrayList<XYSeries> xySeriesCollection ;
	int count = 1 ;
	static int id = 0 ;
	// header
	public boolean isSubplot = false ;

	public XYPlot(String title) {
		this.title = title ;
		xySeriesCollection = new ArrayList<>() ;
	}

	public XYPlot() {
		this(null) ;
	}

	public XYSeries plot(double[] x, double[] y, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries plot(double[] x, double[] y) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, null, null, 0, null, 2, null) ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries semilogx(double[] x, double[] y, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		xyseries.semilogx() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries semilogx(double[] x, double[] y) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, null, null, 0, null, 2, null) ;
		xyseries.semilogx() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries semilogy(double[] x, double[] y, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		xyseries.semilogy() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries semilogy(double[] x, double[] y) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, null, null, 0, null, 2, null) ;
		xyseries.semilogy() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries loglog(double[] x, double[] y, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		xyseries.loglog() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYSeries loglog(double[] x, double[] y) {
		XYSeries xyseries = new XYSeries(x, y, "x"+count, "y"+count, null, null, 0, null, 2, null) ;
		xyseries.loglog() ;
		xySeriesCollection.add(xyseries) ;
		++count ;
		return xyseries ;
	}

	public XYPlot xlabel(String xlabel) {
		this.xlabel = xlabel ;
		return this ;
	}

	public XYPlot ylabel(String ylabel) {
		this.ylabel = ylabel ;
		return this ;
	}

	public XYPlot title(String title) {
		this.title = title ;
		return this ;
	}

	public XYPlot xlim(double xmin, double xmax) {
		this.xlim = format("[%f, %f]", xmin, xmax) ;
		return this ;
	}

	public XYPlot ylim(double ymin, double ymax) {
		this.ylim = format("[%f, %f]", ymin, ymax) ;
		return this ;
	}

	public XYPlot grid(boolean on, String which, String axis) {
		this.grid = on ;
		this.gridWhich = which ;
		this.gridAxis = axis ;
		return this ;
	}

	public XYPlot grid(boolean on) {
		this.grid = on ;
		this.gridWhich = "both" ;
		this.gridAxis = "both" ;
		return this ;
	}

	public XYPlot legend(boolean on) {
		this.legend = on ;
		return this ;
	}

	public XYPlot legend(boolean on, String loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.trim() : null ;
		return this ;
	}

	public XYPlot legend(boolean on, LegendLocation loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.toString().trim() : null ;
		return this ;
	}

	public XYPlot xticks(double... xticks) {
		this.xticks = (xticks!=null) ? xticks : null ;
		return this ;
	}

	public XYPlot xticks(String... xlabels) {
		this.xtickLabels = (xlabels!=null) ? xlabels : null ;
		return this ;
	}

	public XYPlot yticks(double... yticks) {
		this.yticks = (yticks!=null) ? yticks : null ;
		return this ;
	}

	public XYPlot yticks(String... ylabels) {
		this.ytickLabels = (ylabels!=null) ? ylabels : null ;
		return this ;
	}

	public XYPlot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public XYPlot cla() {
		this.cla = true ;
		return this ;
	}

	public void savefig(String fileName) {
		if(xySeriesCollection.isEmpty())
			throw new IllegalStateException("XYPlot data is empty") ;
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

	public void show(String fileName) {
		if(xySeriesCollection.isEmpty())
			throw new IllegalStateException("XYPlot data is empty") ;
		// open the output stream
		FileOutput fo = new FileOutput(fileName) ;
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

	public void show() {
		if(xySeriesCollection.isEmpty())
			throw new IllegalStateException("XYPlot data is empty") ;
		// open the output stream
		File file = new File("xy_plot_"+(id++)) ;
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
		// for each xy series, write the data
		for(XYSeries xyseries: xySeriesCollection) {
			// print x
			fo.print(xyseries.xvar + " = [");
			fo.printcomma(xyseries.x);
			fo.print("];") ;
			fo.println();
			// print y
			fo.print(xyseries.yvar + " = [");
			fo.printcomma(xyseries.y);
			fo.print("];") ;
			fo.println();
			// plot
			fo.println(xyseries.toString());
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
		if(xticks != null) {
			if(xtickLabels == null)
				fo.println(format("plt.xticks(ticks=%s)", Arrays.toString(xticks)));
			else
				fo.println(format("plt.xticks(ticks=%s, labels=%s)", Arrays.toString(xticks), Arrays.toString(xtickLabels)));
		}
		if(yticks != null) {
			if(ytickLabels == null)
				fo.println(format("plt.yticks(ticks=%s)", Arrays.toString(yticks)));
			else
				fo.println(format("plt.yticks(ticks=%s, labels=%s)", Arrays.toString(yticks), Arrays.toString(ytickLabels)));
		}
		if(tightLayout)
			fo.println("plt.tight_layout()");
	}

	public void help() {
		try {
			URI doc = new URI("https://matplotlib.org/Matplotlib.pdf") ;
			Desktop.getDesktop().browse(doc);
		} catch (IOException e) {
			System.out.println("Could not open documentation for matplotlib");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
