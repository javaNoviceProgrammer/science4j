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
 * -----------------------
 * XYIntervalDataItem.java
 * -----------------------
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

import org.jfree.data.ComparableObjectItem;


public class CustomXYIntervalDataItem extends ComparableObjectItem  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates a new instance of <code>XYIntervalItem</code>.
	 *
	 * @param x  the x-value.
	 * @param xLow  the lower bound of the x-interval.
	 * @param xHigh  the upper bound of the x-interval.
	 * @param y  the y-value.
	 * @param yLow  the lower bound of the y-interval.
	 * @param yHigh  the upper bound of the y-interval.
	 */
	public CustomXYIntervalDataItem(double x, double yMean, double yMed,
			double yFirst, double yConfLow, double yConfHigh, double yMin,
			double yMax, double y1Quart, double y3Quart) {
		super(new Double(x), new CustomXYInterval(yMean, yMed, yFirst, yConfLow,
				yConfHigh, yMin, yMax, y1Quart, y3Quart));
	}

	/**
	 * Returns the x-value.
	 *
	 * @return The x-value (never <code>null</code>).
	 */
	public Double getX() {
		return (Double) getComparable();
	}


	public double getYMeanValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYMean();
		}
		else {
			return Double.NaN;
		}
	}

	public double getYMedianValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYMedian();
		}
		else {
			return Double.NaN;
		}
	}

	public double getYFirstValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYFirst();
		}
		else {
			return Double.NaN;
		}
	}
	public double getYConfLowValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYConfLow();
		}
		else {
			return Double.NaN;
		}
	}
	public double getYConfHighValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYConfHigh();
		}
		else {
			return Double.NaN;
		}
	}
	public double getYMinValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYMin();
		}
		else {
			return Double.NaN;
		}
	}
	public double getYMaxValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getYMax();
		}
		else {
			return Double.NaN;
		}
	}
	public double getY1QValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getY1Quart();
		}
		else {
			return Double.NaN;
		}
	}
	public double getY3QValue() {
		CustomXYInterval interval = (CustomXYInterval) getObject();
		if (interval != null) {
			return interval.getY3Quart();
		}
		else {
			return Double.NaN;
		}
	}

}
