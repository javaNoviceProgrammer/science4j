package pyplot4j.xyz;

import static java.lang.String.format;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import pyplot4j.style.LegendLocation;
import pyplot4j.util.FileOutput;
import pyplot4j.util.TerminalExecutor;

/**
 * Axes3D.plot_wireframe(X, Y, Z, *args, **kwargs)
 * 
 * @author Meisam
 *
 */

public class XYZWireframePlot {
	
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
	boolean useTkAgg = false ;
	String backend = "MacOSX" ;
	// data
	ArrayList<XYZWireframeSeries> xyzSeriesCollection ;
	int count = 1 ;
	static int id = 0 ;
	// header
	public boolean isSubplot = false ;
	// font
	double fontSize = -1.0 ;
	
	
	public XYZWireframePlot(String title) {
		this.title = title ;
		xyzSeriesCollection = new ArrayList<>() ;
	}

	public XYZWireframePlot() {
		this(null) ;
	}

	public XYZWireframeSeries plot(double[] x, double[] y, double[] z, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYZWireframeSeries series = new XYZWireframeSeries(x, y, z, "x"+count, "y"+count, "z"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZWireframeSeries plot(double[] x, double[] y, double[] z) {
		XYZWireframeSeries series = new XYZWireframeSeries(x, y, z, "x"+count, "y"+count, "z"+count,  null, null, 0, null, 2, null) ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZWireframeSeries semilogx(double[] x, double[] y, double[] z, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYZWireframeSeries series = new XYZWireframeSeries(x, y, z, "x"+count, "y"+count, "z"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		series.semilogx() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterSeries semilogx(double[] x, double[] y, double[] z) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, z, "x"+count, "y"+count, "z"+count, null, null, 0, null, 2, null) ;
		series.semilogx() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterSeries semilogy(double[] x, double[] y, double[] z, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, z, "x"+count, "y"+count, "z"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		series.semilogy() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterSeries semilogy(double[] x, double[] y, double[] z) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, z, "x"+count, "y"+count, "z"+count, null, null, 0, null, 2, null) ;
		series.semilogy() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterSeries loglog(double[] x, double[] y, double[] z, String color, String marker, int markerSize, String linestyle, int linewidth, String label) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, z, "x"+count, "y"+count, "z"+count, color, marker, markerSize, linestyle, linewidth, label) ;
		series.loglog() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterSeries loglog(double[] x, double[] y, double[] z) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, z, "x"+count, "y"+count, "z"+count, null, null, 0, null, 2, null) ;
		series.loglog() ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}
	
	// XY plot at a given z location
	public XYZScatterSeries plotXY(double[] x, double[] y, double zloc) {
		XYZScatterSeries series = new XYZScatterSeries(x, y, new double[] {zloc}, "x"+count, "y"+count, "z"+count, "z",  null, null, 0, null, 2, null) ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}
	
	// XZ plot at a given y location
	public XYZScatterSeries plotXZ(double[] x, double[] z, double yloc) {
		XYZScatterSeries series = new XYZScatterSeries(x, z, new double[] {yloc}, "x"+count, "y"+count, "z"+count, "y",  null, null, 0, null, 2, null) ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}
	
	// YZ plot at a given y location
	public XYZScatterSeries plotYZ(double[] y, double[] z, double xloc) {
		XYZScatterSeries series = new XYZScatterSeries(y, z, new double[] {xloc}, "x"+count, "y"+count, "z"+count, "x",  null, null, 0, null, 2, null) ;
		xyzSeriesCollection.add(series) ;
		++count ;
		return series ;
	}

	public XYZScatterPlot xlabel(String xlabel) {
		this.xlabel = xlabel ;
		return this ;
	}

	public XYZScatterPlot ylabel(String ylabel) {
		this.ylabel = ylabel ;
		return this ;
	}

	public XYZScatterPlot title(String title) {
		this.title = title ;
		return this ;
	}

	public XYZScatterPlot xlim(double xmin, double xmax) {
		this.xlim = format("[%f, %f]", xmin, xmax) ;
		return this ;
	}

	public XYZScatterPlot ylim(double ymin, double ymax) {
		this.ylim = format("[%f, %f]", ymin, ymax) ;
		return this ;
	}

	public XYZScatterPlot grid(boolean on, String which, String axis) {
		this.grid = on ;
		this.gridWhich = which ;
		this.gridAxis = axis ;
		return this ;
	}

	public XYZScatterPlot grid(boolean on) {
		this.grid = on ;
		this.gridWhich = "both" ;
		this.gridAxis = "both" ;
		return this ;
	}

	public XYZScatterPlot legend(boolean on) {
		this.legend = on ;
		return this ;
	}

	public XYZScatterPlot legend(boolean on, String loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.trim() : null ;
		return this ;
	}

	public XYZScatterPlot legend(boolean on, LegendLocation loc) {
		this.legend = on ;
		this.legendLocation = (loc!=null) ? loc.toString().trim() : null ;
		return this ;
	}

	public XYZScatterPlot xticks(double... xticks) {
		this.xticks = (xticks!=null) ? xticks : null ;
		return this ;
	}

	public XYZScatterPlot xticks(String... xlabels) {
		this.xtickLabels = (xlabels!=null) ? xlabels : null ;
		return this ;
	}

	public XYZScatterPlot yticks(double... yticks) {
		this.yticks = (yticks!=null) ? yticks : null ;
		return this ;
	}

	public XYZScatterPlot yticks(String... ylabels) {
		this.ytickLabels = (ylabels!=null) ? ylabels : null ;
		return this ;
	}

	public XYZScatterPlot tightLayout() {
		this.tightLayout = true ;
		return this ;
	}

	public XYZScatterPlot cla() {
		this.cla = true ;
		return this ;
	}
	
	public XYZScatterPlot fontSize(double fontSize) {
		this.fontSize = fontSize ;
		return this ;
	}
	
	public XYZScatterPlot backend(String backend) {
		this.backend = backend.trim() ;
		return this ;
	}
	
//	public XYZScatterPlot useTkAgg(boolean useTkAgg) {
//		this.useTkAgg = useTkAgg ;
//		return this ;
//	}
	
	
	public void savefig(String fileName, boolean saveSource) {
		if(xyzSeriesCollection.isEmpty())
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
		if(!saveSource) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FileOutput.deleteFile(pyName+".py") ;
		}
	}
	
	public void savefig(String fileName) {
		savefig(fileName, false);
	}
	
//	public void savefig(String fileName) {
//		if(xyzSeriesCollection.isEmpty())
//			throw new IllegalStateException("XYPlot data is empty") ;
//		// open the output stream
//		int index = fileName.lastIndexOf('.') ;
//		String pyName = fileName.substring(0, index) ;
//		FileOutput fo = new FileOutput(pyName+".py") ;
//		pythonCode(fo);
//		// show the plot
//		if(cla) {
//			fo.println("plt.cla()");
//		}
//		fo.println(format("plt.savefig('%s')", fileName));
//		// close the output stream
//		fo.close();
//		// run the python code
//		Thread thread = new Thread(() -> {
//			TerminalExecutor.execute("python", fo.getFilename());
//		}) ;
//		thread.start();
//	}

	public void show(String fileName) {
		if(xyzSeriesCollection.isEmpty())
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
		if(xyzSeriesCollection.isEmpty())
			throw new IllegalStateException("XYPlot data is empty") ;
		// open the output stream
		File file = new File("xyz_lineplot_"+(id++)) ;
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
//			fo.println("from sys import platform as sys_pf") ;
//			fo.println("if sys_pf == 'darwin':") ;
			fo.println("import matplotlib") ;
			fo.println(format("matplotlib.use('%s')", backend)) ;
//			if(useTkAgg) {
//				fo.println("\tmatplotlib.use('TkAgg')") ;
//			}
			fo.println("import numpy as np") ;
			fo.println("import matplotlib.pyplot as plt");
			if(fontSize>0.0) {
				fo.println(format("plt.rcParams['font.size']=%f", fontSize));
			}
			fo.println();
		}
		// for each xy series, write the data
		for(XYZScatterSeries series: xyzSeriesCollection) {
			// print x
			fo.print(series.xvar + " = [");
			fo.printcomma(series.x);
			fo.print("];") ;
			fo.println();
			// print y
			fo.print(series.yvar + " = [");
			fo.printcomma(series.y);
			fo.print("];") ;
			fo.println();
			// print z
			fo.print(series.zvar + " = [");
			fo.printcomma(series.z);
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
