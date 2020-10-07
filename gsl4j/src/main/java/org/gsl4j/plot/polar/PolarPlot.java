package org.gsl4j.plot.polar;

import static java.lang.String.format;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.gsl4j.io.FileOutput;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.plot.util.TerminalExecutor;



public class PolarPlot {

	/*
	 * plt.subplot(1,1,1, polar=True)
	 * plt.plot(x,y, ...)
	 *
	 */


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
	// data
	ArrayList<PolarSeries> polarSeriesCollection ;
	int count = 1 ;
	boolean isSubplot = false ;
	static int id = 0 ;


	public PolarPlot(String title) {
		this.title = title ;
		polarSeriesCollection = new ArrayList<>() ;
	}

	public PolarPlot() {
		this(null) ;
	}

	public PolarSeries plot(double[] theta, double[] r) {
		PolarSeries polarSeries = new PolarSeries(theta, r) ;
		polarSeries.setRvar("r"+count).setThetaVar("theta"+count) ;
		polarSeriesCollection.add(polarSeries) ;
		++count ;
		return polarSeries ;
	}

	public PolarPlot title(String title) {
		this.title = title ;
		return this ;
	}

	public PolarPlot grid(boolean on, String which, String axis) {
		this.grid = on ;
		this.gridWhich = which ;
		this.gridAxis = axis ;
		return this ;
	}

	public PolarPlot grid(boolean on) {
		this.grid = on ;
		this.gridWhich = "both" ;
		this.gridAxis = "both" ;
		return this ;
	}

	public PolarPlot legend(boolean on) {
		this.legend = on ;
		return this ;
	}

	public PolarPlot legend(boolean on, String loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.trim() : null ;
		return this ;
	}

	public PolarPlot legend(boolean on, LegendLocation loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.toString().trim() : null ;
		return this ;
	}

	public PolarPlot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public PolarPlot cla() {
		this.cla = true ;
		return this ;
	}

	public void savefig(String fileName) {
		if(polarSeriesCollection.isEmpty())
			throw new IllegalStateException("PolarPlot data is empty") ;
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
		if(polarSeriesCollection.isEmpty())
			throw new IllegalStateException("PolarPlot data is empty") ;
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
		if(polarSeriesCollection.isEmpty())
			throw new IllegalStateException("PolarPlot data is empty") ;
		// open the output stream
		File file = new File("polar_plot_"+(id++)) ;
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
			fo.println("import matplotlib.pyplot as plt");
			fo.println("\nplt.subplot(1, 1, 1, polar=True)");
			fo.println();
		}
		// for each xy series, write the data
		for(PolarSeries series: polarSeriesCollection) {
			// print r
			fo.print(series.rvar + " = [");
			fo.printcomma(series.r);
			fo.print("];") ;
			fo.println();
			// print theta
			fo.print(series.thetavar + " = [");
			fo.printcomma(series.theta);
			fo.print("];") ;
			fo.println();
			// plot
			fo.println(series.toString());
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
