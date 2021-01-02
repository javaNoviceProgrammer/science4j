/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ---------------
 * XYInterval.java
 * ---------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 *
 */

package visualizer4j.charts;

import java.io.Serializable;

import org.jfree.data.xy.XYIntervalDataItem;

/**
 * An  xy-interval.  This class is used internally by the
 * {@link XYIntervalDataItem} class.
 *
 * @since 1.0.3
 */
public class CustomXYInterval implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double yMean;
	private double yMedian;
	private double yFirst;

	private double yConfLow;
	private double yConfHigh;
	private double yMin;
	private double yMax;
	private double y1Quart;
	private double y3Quart;


	/**
	 * Creates a new instance of <code>XYInterval</code>.
	 *
	 * @param xLow  the lower bound of the x-interval.
	 * @param xHigh  the upper bound of the y-interval.
	 * @param y  the y-value.
	 * @param yLow  the lower bound of the y-interval.
	 * @param yHigh  the upper bound of the y-interval.
	 */
	public CustomXYInterval(double yMean, double yMedian, double yFirst,
			double yConfLow, double yConfHigh, double yMin, double yMax,
			double y1Quart, double y3Quart) {

		this.yMean = yMean;
		this.yMedian = yMedian;
		this.yFirst = yFirst;
		this.yConfLow = yConfLow;
		this.yConfHigh = yConfHigh;
		this.yMin = yMin;
		this.yMax = yMax;
		this.y1Quart = y1Quart;
		this.y3Quart = y3Quart;
	}

	public double getYMean() {
		return yMean;
	}

	public double getYMedian() {
		return yMedian;
	}

	public double getYFirst() {
		return yFirst;
	}

	public double getYConfLow() {
		return yConfLow;
	}

	public double getYConfHigh() {
		return yConfHigh;
	}

	public double getYMin() {
		return yMin;
	}

	public double getYMax() {
		return yMax;
	}

	public double getY1Quart() {
		return y1Quart;
	}

	public double getY3Quart() {
		return y3Quart;
	}

}
