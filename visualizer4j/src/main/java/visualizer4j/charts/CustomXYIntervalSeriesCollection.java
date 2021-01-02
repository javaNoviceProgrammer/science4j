/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
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
 * -------------------------------
 * XYIntervalSeriesCollection.java
 * -------------------------------
 * (C) Copyright 2006-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Oct-2006 : Version 1 (DG);
 * 13-Feb-2007 : Provided a number of method overrides that enhance
 *			   performance, and added a proper clone()
 *			   implementation (DG);
 * 18-Jan-2008 : Added removeSeries() and removeAllSeries() methods (DG);
 * 22-Apr-2008 : Implemented PublicCloneable (DG);
 *
 */

package visualizer4j.charts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.XYIntervalSeries;
import org.jfree.data.xy.XYIntervalSeriesCollection;

import ch.epfl.general_libraries.results.DataRetrievalOptions;

/**
 * A collection of {@link XYIntervalSeries} objects.
 *
 * @since 1.0.3
 *
 * @see XYIntervalSeries
 */
public class CustomXYIntervalSeriesCollection extends XYIntervalSeriesCollection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Storage for the data series. */
	private List<CustomXYIntervalSeries> data;
	
	public void setDisplayMode(boolean[] mode1, boolean[] mode2) {

	}

	private boolean[] mode2;
	private boolean[] mode1;	

	/**
	 * Creates a new instance of <code>XIntervalSeriesCollection</code>.
	 */
	public CustomXYIntervalSeriesCollection(DataRetrievalOptions options) {
		this.data = new java.util.ArrayList<CustomXYIntervalSeries>();
		mode1 = new boolean[]{options.isMeanOrSum, options.isMedian, options.isFirst};
		mode2 = new boolean[]{options.isMax, options.isConfInt, options.isQuartInt};	
	}

	/**
	 * Adds a series to the collection and sends a {@link DatasetChangeEvent}
	 * to all registered listeners.
	 *
	 * @param series  the series (<code>null</code> not permitted).
	 */
	public void addSeries(CustomXYIntervalSeries series) {
		if (series == null) {
			throw new IllegalArgumentException("Null 'series' argument.");
		}
		this.data.add(series);
		series.addChangeListener(this);
		fireDatasetChanged();
	}

	/**
	 * Returns the number of series in the collection.
	 *
	 * @return The series count.
	 */
	@Override
	public int getSeriesCount() {
		return this.data.size();
	}

	/**
	 * Returns a series from the collection.
	 *
	 * @param series  the series index (zero-based).
	 *
	 * @return The series.
	 *
	 * @throws IllegalArgumentException if <code>series</code> is not in the
	 *	 range <code>0</code> to <code>getSeriesCount() - 1</code>.
	 */
	@Override
	public XYIntervalSeries getSeries(int series) {
		if ((series < 0) || (series >= getSeriesCount())) {
			throw new IllegalArgumentException("Series index out of bounds");
		}
		return (XYIntervalSeries) this.data.get(series);
	}

	/**
	 * Returns the key for a series.
	 *
	 * @param series  the series index (in the range <code>0</code> to
	 *	 <code>getSeriesCount() - 1</code>).
	 *
	 * @return The key for a series.
	 *
	 * @throws IllegalArgumentException if <code>series</code> is not in the
	 *	 specified range.
	 */
	@Override
	public Comparable getSeriesKey(int series) {
		// defer argument checking
		return getSeries(series).getKey();
	}

	private ArrayList<Double> getYs(int series, int item) {
		CustomXYIntervalSeries cus = (CustomXYIntervalSeries) this.data.get(series);
		CustomXYIntervalDataItem it = (CustomXYIntervalDataItem) cus.getDataItem(item);
		ArrayList<Double> list = new ArrayList<Double>(9);
		if (displayMean()) {
			list.add(it.getYMeanValue());
		}
		if (displayMedian()) {
			list.add(it.getYMedianValue());
		}
		if (displayFirst()) {
			list.add(it.getYFirstValue());
		}
		if (displayMinMax()) {
			list.add(it.getYMinValue());
			list.add(it.getYMaxValue());
		}
		if (displayConfidenceInterval()) {
			list.add(it.getYConfLowValue());
			list.add(it.getYConfHighValue());
		}
		if (displayInterquatileRange()) {
			list.add(it.getY1QValue());
			list.add(it.getY3QValue());
		}
		return list;
	}

	/**
	 * Returns the number of items in the specified series.
	 *
	 * @param series  the series (zero-based index).
	 *
	 * @return The item count.
	 *
	 * @throws IllegalArgumentException if <code>series</code> is not in the
	 *	 range <code>0</code> to <code>getSeriesCount() - 1</code>.
	 */
	@Override
	public int getItemCount(int series) {
		// defer argument checking
		return getSeries(series).getItemCount();
	}


	@Override
	public Number getX(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getX(item);
	}

	@Override
	public double getStartYValue(int series, int item) {
		ArrayList<Double> ard = getYs(series, item);
		if (ard.size() > 0) {
			return Collections.min(ard);
		} else {
			return getYMeanValue(series, item);
		}
	}

	@Override
	public double getEndYValue(int series, int item) {
		ArrayList<Double> ard = getYs(series, item);
		if (ard.size() > 0) {
			return Collections.max(ard);
		} else {
			return getYMeanValue(series, item);
		}
	}

	@Override
	public double getYValue(int series, int item) {
		if (displayMean()) {
			return getYMeanValue(series, item);
		}
		if (displayMedian()) {
			return getYMedianValue(series, item);
		}
		if (displayFirst()) {
			return getYFirstValue(series, item);
		}
		throw new IllegalStateException();
		//	return getYMeanValue(series, item);
	}


	public double getYMeanValue(int series, int item) {
		CustomXYIntervalSeries cus = (CustomXYIntervalSeries) this.data.get(series);
		return cus.getYMeanValue(item);
	}

	public double getYMedianValue(int series, int item) {
		CustomXYIntervalSeries cus = (CustomXYIntervalSeries) this.data.get(series);
		return cus.getYMedianValue(item);
	}

	public double getYFirstValue(int series, int item) {
		CustomXYIntervalSeries cus = (CustomXYIntervalSeries) this.data.get(series);
		return cus.getYFirstValue(item);
	}

	public double getLowerConfidenceYValue(int series, int item) {
		CustomXYIntervalSeries cus = (CustomXYIntervalSeries) this.data.get(series);
		return cus.getYConfLowValue(item);
	}

	public double getUpperConfidenceYValue(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getYConfHighValue(item);
	}

	public double getMaxYValue(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getYMaxValue(item);
	}

	public double getMinYValue(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getYMinValue(item);
	}

	public double get1QuartYValue(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getY1QValue(item);
	}

	public double get3QuartYValue(int series, int item) {
		CustomXYIntervalSeries s = (CustomXYIntervalSeries) this.data.get(series);
		return s.getY3QValue(item);
	}

	public boolean displayAnyLine() {
		return mode1[0] || mode1[1] || mode1[2];
	}

	public boolean displayMean() {
		return mode1[0];
	}

	public boolean displayMedian() {
		return mode1[1];
	}

	public boolean displayFirst() {
		return mode1[2];
	}

	public boolean displayMinMax() {
		return mode2[0];
	}

	public boolean displayConfidenceInterval() {
		return mode2[1];
	}

	public boolean displayInterquatileRange() {
		return mode2[2];

	}

	/**
	 * Returns the y-value for an item within a series.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The y-value.
	 */
	@Override
	public Number getY(int series, int item) {
		return new Double(getYValue(series, item));
	}

	/**
	 * Returns the start x-value for an item within a series.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The x-value.
	 */
	@Override
	public Number getStartX(int series, int item) {
		return new Double(getStartXValue(series, item));
	}

	/**
	 * Returns the end x-value for an item within a series.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The x-value.
	 */
	@Override
	public Number getEndX(int series, int item) {
		return new Double(getEndXValue(series, item));
	}

	/**
	 * Returns the start y-value for an item within a series.  This method
	 * maps directly to {@link #getY(int, int)}.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The start y-value.
	 */
	@Override
	public Number getStartY(int series, int item) {
		return new Double(getStartYValue(series, item));
	}

	/**
	 * Returns the end y-value for an item within a series.  This method
	 * maps directly to {@link #getY(int, int)}.
	 *
	 * @param series  the series index.
	 * @param item  the item index.
	 *
	 * @return The end y-value.
	 */
	@Override
	public Number getEndY(int series, int item) {
		return new Double(getEndYValue(series, item));
	}

	/**
	 * Removes a series from the collection and sends a
	 * {@link DatasetChangeEvent} to all registered listeners.
	 *
	 * @param series  the series index (zero-based).
	 *
	 * @since 1.0.10
	 */
	@Override
	public void removeSeries(int series) {
		if ((series < 0) || (series >= getSeriesCount())) {
			throw new IllegalArgumentException("Series index out of bounds.");
		}
		XYIntervalSeries ts = (XYIntervalSeries) this.data.get(series);
		ts.removeChangeListener(this);
		this.data.remove(series);
		fireDatasetChanged();
	}

	/**
	 * Removes a series from the collection and sends a
	 * {@link DatasetChangeEvent} to all registered listeners.
	 *
	 * @param series  the series (<code>null</code> not permitted).
	 *
	 * @since 1.0.10
	 */
	@Override
	public void removeSeries(XYIntervalSeries series) {
		if (series == null) {
			throw new IllegalArgumentException("Null 'series' argument.");
		}
		if (this.data.contains(series)) {
			series.removeChangeListener(this);
			this.data.remove(series);
			fireDatasetChanged();
		}
	}

	/**
	 * Removes all the series from the collection and sends a
	 * {@link DatasetChangeEvent} to all registered listeners.
	 *
	 * @since 1.0.10
	 */
	@Override
	public void removeAllSeries() {
		// Unregister the collection as a change listener to each series in
		// the collection.
		for (int i = 0; i < this.data.size(); i++) {
			XYIntervalSeries series = (XYIntervalSeries) this.data.get(i);
			series.removeChangeListener(this);
		}
		this.data.clear();
		fireDatasetChanged();
	}

	/**
	 * Returns a clone of this dataset.
	 *
	 * @return A clone of this dataset.
	 *
	 * @throws CloneNotSupportedException if there is a problem cloning.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		XYIntervalSeriesCollection clone
		= (XYIntervalSeriesCollection) super.clone();
		return clone;
	}

}
