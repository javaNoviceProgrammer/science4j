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
 * ---------------------
 * XYIntervalSeries.java
 * ---------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 * 13-Feb-2007 : Added several new accessor methods (DG);
 *
 */

package visualizer4j.charts;

import org.jfree.data.ComparableObjectItem;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;

/**
 * A list of (x, x-low, x-high, y, y-low, y-high) data items.
 *
 * @since 1.0.3
 *
 * @see XYIntervalSeriesCollection
 */
public class CustomXYIntervalSeries extends XYIntervalSeries {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a new empty series.  By default, items added to the series will
	 * be sorted into ascending order by x-value, and duplicate x-values will
	 * be allowed (these defaults can be modified with another constructor).
	 *
	 * @param key  the series key (<code>null</code> not permitted).
	 */
	public CustomXYIntervalSeries(Comparable key) {
		this(key, true, true);
	}

	/**
	 * Constructs a new xy-series that contains no data.  You can specify
	 * whether or not duplicate x-values are allowed for the series.
	 *
	 * @param key  the series key (<code>null</code> not permitted).
	 * @param autoSort  a flag that controls whether or not the items in the
	 *                  series are sorted.
	 * @param allowDuplicateXValues  a flag that controls whether duplicate
	 *                               x-values are allowed.
	 */
	public CustomXYIntervalSeries(Comparable key, boolean autoSort,
			boolean allowDuplicateXValues) {
		super(key, autoSort, allowDuplicateXValues);
	}

	/**
	 * Adds a data item to the series.
	 *
	 * @param x  the x-value.
	 * @param xLow  the lower bound of the x-interval.
	 * @param xHigh  the upper bound of the x-interval.
	 * @param y  the y-value.
	 * @param yLow  the lower bound of the y-interval.
	 * @param yHigh  the upper bound of the y-interval.
	 */
	public void add(double x, double yMean, double yMed,
			double yFirst, double yConfLow, double yConfHigh, double yMin,
			double yMax, double y1Quart, double y3Quart) {
		super.add(new CustomXYIntervalDataItem(x, yMean, yMed, yFirst,
				yConfLow, yConfHigh, yMin, yMax, y1Quart, y3Quart), true);
	}

	public void add(double x, double[] allButXAndFirst, double first) {
		super.add(new CustomXYIntervalDataItem(x, allButXAndFirst[0], allButXAndFirst[1], first,
				allButXAndFirst[2], allButXAndFirst[3], allButXAndFirst[4],
				allButXAndFirst[5], allButXAndFirst[6], allButXAndFirst[7]), true);
	}

	public void add(double[] all) {
		super.add(new CustomXYIntervalDataItem(all[9], all[0], all[1], all[8],
				all[2], all[3], all[4], all[5], all[6], all[7]), true);
	}

	public void add(float[] all) {
		super.add(new CustomXYIntervalDataItem(all[9], all[0], all[1], all[8],
				all[2], all[3], all[4], all[5], all[6], all[7]), true);
	}

	@Override
	public Number getX(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getX();
	}

	public double getYMeanValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYMeanValue();
	}

	public double getYMedianValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYMedianValue();
	}
	public double getYFirstValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYFirstValue();
	}
	public double getYConfLowValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYConfLowValue();
	}
	public double getYConfHighValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYConfHighValue();
	}
	public double getYMinValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYMinValue();
	}
	public double getYMaxValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getYMaxValue();
	}
	public double getY1QValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getY1QValue();
	}
	public double getY3QValue(int index) {
		CustomXYIntervalDataItem item = (CustomXYIntervalDataItem) getDataItem(index);
		return item.getY3QValue();
	}


	/**
	 * Returns the data item at the specified index.
	 * 
	 * @param index  the item index.
	 * 
	 * @return The data item.
	 */
	@Override
	public ComparableObjectItem getDataItem(int index) {
		return super.getDataItem(index);
	}

}
