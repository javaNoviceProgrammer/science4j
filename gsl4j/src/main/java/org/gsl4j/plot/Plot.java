package org.gsl4j.plot;

import static java.lang.String.format;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gsl4j.io.FileOutput;
import org.gsl4j.plot.contour.ContourSeries;
import org.gsl4j.plot.contour.MeshGrid;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.plot.util.TerminalExecutor;
import org.gsl4j.plot.xy.XYSeries;


public class Plot {
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
	// ticks
	double[] xticks ;
	String[] xtickLabels ;
	double[] yticks ;
	String[] ytickLabels ;
	// other properties
	boolean tightLayout = false ;

	// data
	List<XYSeries> xyseriesCollection;
	List<ContourSeries> contourSeriesCollection;
	// other param
	int count = 1 ;
	static int id = 0 ;
	// header
	public boolean isSubplot = false ;


	public Plot(String title) {
		this.title = title ;
		xyseriesCollection = new ArrayList<>() ;
		contourSeriesCollection = new ArrayList<>() ;
	}

	public Plot() {
		this(null) ;
	}

	public XYSeries plot(double[] x, double[] y) {
		XYSeries xyseries = new XYSeries(x, y) ;
		xyseries.setXvar("x"+count).setYvar("y"+count) ;
		xyseriesCollection.add(xyseries) ;
		count++ ;
		return xyseries ;
	}

	public ContourSeries contour(double[] x, double[] y, MeshGrid z) {
		ContourSeries contourSeries = new ContourSeries(x, y, z) ;
		contourSeries.setXvar("x"+count).setYvar("y"+count).setZvar("z"+count) ;
		contourSeries.clabel().name("cs"+count) ;
		contourSeriesCollection.add(contourSeries) ;
		count++ ;
		return contourSeries ;
	}

	public ContourSeries contour(double[] x, double[] y, double[][] z) {
		ContourSeries contourSeries = new ContourSeries(x, y, z) ;
		contourSeries.setXvar("x"+count).setYvar("y"+count).setZvar("z"+count) ;
		contourSeries.clabel().name("cs"+count) ;
		contourSeriesCollection.add(contourSeries) ;
		count++ ;
		return contourSeries ;
	}


	public Plot xlabel(String xlabel) {
		this.xlabel = xlabel ;
		return this ;
	}

	public Plot ylabel(String ylabel) {
		this.ylabel = ylabel ;
		return this ;
	}

	public Plot title(String title) {
		this.title = title ;
		return this ;
	}

	public Plot xlim(double xmin, double xmax) {
		this.xlim = format("[%f, %f]", xmin, xmax) ;
		return this ;
	}

	public Plot ylim(double ymin, double ymax) {
		this.ylim = format("[%f, %f]", ymin, ymax) ;
		return this ;
	}

	public Plot grid(boolean on, String which, String axis) {
		this.grid = on ;
		this.gridWhich = which ;
		this.gridAxis = axis ;
		return this ;
	}

	public Plot grid(boolean on) {
		this.grid = on ;
		this.gridWhich = "both" ;
		this.gridAxis = "both" ;
		return this ;
	}

	public Plot legend(boolean on) {
		this.legend = on ;
		return this ;
	}

	public Plot legend(boolean on, String loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.trim() : null ;
		return this ;
	}

	public Plot legend(boolean on, LegendLocation loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.toString().trim() : null ;
		return this ;
	}

	public Plot xticks(double... xticks) {
		this.xticks = (xticks!=null) ? xticks : null ;
		return this ;
	}

	public Plot xticks(String... xlabels) {
		this.xtickLabels = (xlabels!=null) ? xlabels : null ;
		return this ;
	}

	public Plot yticks(double... yticks) {
		this.yticks = (yticks!=null) ? yticks : null ;
		return this ;
	}

	public Plot yticks(String... ylabels) {
		this.ytickLabels = (ylabels!=null) ? ylabels : null ;
		return this ;
	}

	public Plot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public Plot cla() {
		this.cla = true ;
		return this ;
	}

	public void savefig(String fileName) {
		if (xyseriesCollection.isEmpty() && contourSeriesCollection.isEmpty())
			throw new IllegalStateException("Plot data is empty");
		// open the output stream
		int index = fileName.lastIndexOf('.');
		String pyName = fileName.substring(0, index);
		FileOutput fo = new FileOutput(pyName + ".py");
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
		if (xyseriesCollection.isEmpty() && contourSeriesCollection.isEmpty())
			throw new IllegalStateException("Plot data is empty");
		// open the output stream
		FileOutput fo = new FileOutput(fileName);
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
		if (xyseriesCollection.isEmpty() && contourSeriesCollection.isEmpty())
			throw new IllegalStateException("Plot data is empty");
		// open the output stream
		File file = new File("plot_" + (id++));
		file.deleteOnExit();
		FileOutput fo = new FileOutput(file);
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
		for(XYSeries xyseries: xyseriesCollection) {
			// use reflection to access fields
			Class<?> clazz = xyseries.getClass() ;
			try {
				Field fx = clazz.getDeclaredField("x") ;
				fx.setAccessible(true);
				double[] x = (double[]) fx.get(xyseries) ;
				Field fxvar = clazz.getDeclaredField("xvar") ;
				fxvar.setAccessible(true);
				String xvar = (String) fxvar.get(xyseries) ;
				Field fy = clazz.getDeclaredField("y") ;
				fy.setAccessible(true);
				double[] y = (double[]) fy.get(xyseries) ;
				Field fyvar = clazz.getDeclaredField("yvar")  ;
				fyvar.setAccessible(true);
				String yvar = (String) fyvar.get(xyseries) ;
				// print x
				fo.print(xvar + " = [");
				fo.printcomma(x);
				fo.print("];") ;
				fo.println();
				// print y
				fo.print(yvar + " = [");
				fo.printcomma(y);
				fo.print("];") ;
				fo.println();
				// plot
				fo.println(xyseries.toString());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// for each contour series, write the data
		for(ContourSeries contourSeries : contourSeriesCollection) {
			// use reflection to access fields
			Class<?> clazz = contourSeries.getClass() ;
			try {
				Field fx = clazz.getDeclaredField("x") ;
				fx.setAccessible(true);
				double[] x = (double[]) fx.get(contourSeries) ;
				Field fxvar = clazz.getDeclaredField("xvar") ;
				fxvar.setAccessible(true);
				String xvar = (String) fxvar.get(contourSeries) ;
				Field fy = clazz.getDeclaredField("y") ;
				fy.setAccessible(true);
				double[] y = (double[]) fy.get(contourSeries) ;
				Field fyvar = clazz.getDeclaredField("yvar")  ;
				fyvar.setAccessible(true);
				String yvar = (String) fyvar.get(contourSeries) ;
				// print x
				fo.print(xvar + " = [");
				fo.printcomma(x);
				fo.print("];") ;
				fo.println();
				// print y
				fo.print(yvar + " = [");
				fo.printcomma(y);
				fo.print("];") ;
				fo.println();
				// contour plot
				fo.println(contourSeries.toString());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
