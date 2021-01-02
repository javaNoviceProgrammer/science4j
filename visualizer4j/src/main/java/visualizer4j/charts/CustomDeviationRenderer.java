/* This FILE is based on
 *
 *===========================================================
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
 */

package visualizer4j.charts;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ShapeUtilities;

/**
 * A specialised subclass of the {@link XYLineAndShapeRenderer} that requires
 * an {@link IntervalXYDataset} and represents the y-interval by shading an
 * area behind the y-values on the chart.
 *
 * @since 1.0.5
 */
@SuppressWarnings("unchecked")
public class CustomDeviationRenderer extends DeviationRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A state object that is passed to each call to <code>drawItem</code>.
	 */
	public static class State extends XYLineAndShapeRenderer.State {

		/**
		 * A list of coordinates for the upper y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List upperConfidenceCoordinates;

		/**
		 * A list of coordinates for the lower y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List lowerConfidenceCoordinates;

		/**
		 * A list of coordinates for the upper y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List maxCoordinates;

		/**
		 * A list of coordinates for the lower y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List minCoordinates;

		/**
		 * A list of coordinates for the upper y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List firstQuartileCoordinates;

		/**
		 * A list of coordinates for the lower y-values in the current series
		 * (after translation into Java2D space).
		 */
		public List thirdQuartileCoordinates;

		/** The path for the current series median. */
		public GeneralPath seriesMedianPath;

		/** The path for the current series median. */
		public GeneralPath seriesFirstPath;
		/**
		 * Creates a new state instance.
		 *
		 * @param info  the plot rendering info.
		 */
		public State(PlotRenderingInfo info) {
			super(info);
			this.upperConfidenceCoordinates = new java.util.ArrayList();
			this.lowerConfidenceCoordinates = new java.util.ArrayList();
			this.minCoordinates = new java.util.ArrayList();
			this.maxCoordinates = new java.util.ArrayList();
			this.firstQuartileCoordinates = new java.util.ArrayList();
			this.thirdQuartileCoordinates = new java.util.ArrayList();
			seriesMedianPath = new GeneralPath();
			seriesFirstPath = new GeneralPath();
		}

	}

	/** The alpha transparency for the interval shading. */
	private float alpha;

	/**
	 * Creates a new renderer that displays lines and shapes for the data
	 * items, as well as the shaded area for the y-interval.
	 */
	public CustomDeviationRenderer() {
		this(true, true, null);
	}
	
	private ChartContainer chart;

	/**
	 * Creates a new renderer.
	 *
	 * @param lines  show lines between data items?
	 * @param shapes  show a shape for each data item?
	 */
	public CustomDeviationRenderer(boolean lines, boolean shapes, ChartContainer chart) {
		super(lines, shapes);
		super.setDrawSeriesLineAsPath(true);
		this.chart = chart;
		this.alpha = 0.5f;
	}

	/**
	 * Returns the alpha transparency for the background shading.
	 *
	 * @return The alpha transparency.
	 *
	 * @see #setAlpha(float)
	 */
	@Override
	public float getAlpha() {
		return this.alpha;
	}

	/**
	 * Sets the alpha transparency for the background shading, and sends a
	 * {@link RendererChangeEvent} to all registered listeners.
	 *
	 * @param alpha   the alpha (in the range 0.0f to 1.0f).
	 *
	 * @see #getAlpha()
	 */
	@Override
	public void setAlpha(float alpha) {
		if (alpha < 0.0f || alpha > 1.0f) {
			throw new IllegalArgumentException(
			"Requires 'alpha' in the range 0.0 to 1.0.");
		}
		this.alpha = alpha;
		fireChangeEvent();
	}

	/**
	 * This method is overridden so that this flag cannot be changed---it is
	 * set to <code>true</code> for this renderer.
	 *
	 * @param flag  ignored.
	 */
	@Override
	public void setDrawSeriesLineAsPath(boolean flag) {
		// ignore
	}

	/**
	 * Returns the range of values the renderer requires to display all the
	 * items from the specified dataset.
	 *
	 * @param dataset  the dataset (<code>null</code> permitted).
	 *
	 * @return The range (<code>null</code> if the dataset is <code>null</code>
	 *		 or empty).
	 */
	@Override
	public Range findRangeBounds(XYDataset dataset) {
		if (dataset != null) {
			return DatasetUtilities.findRangeBounds(dataset, true);
		}
		else {
			return null;
		}
	}

	/**
	 * Initialises and returns a state object that can be passed to each
	 * invocation of the {@link #drawItem} method.
	 *
	 * @param g2  the graphics target.
	 * @param dataArea  the data area.
	 * @param plot  the plot.
	 * @param dataset  the dataset.
	 * @param info  the plot rendering info.
	 *
	 * @return A newly initialised state object.
	 */
	@Override
	public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea,
			XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
		State state = new State(info);
		state.seriesPath = new GeneralPath();
		state.setProcessVisibleItemsOnly(false);
		previousPoints = new double[400][2];
		index = 0;
		return state;
	}

	/**
	 * Returns the number of passes (through the dataset) used by this
	 * renderer.
	 *
	 * @return <code>3</code>.
	 */
	@Override
	public int getPassCount() {
		return 3;
	}

	/**
	 * Returns <code>true</code> if this is the pass where the shapes are
	 * drawn.
	 *
	 * @param pass  the pass index.
	 *
	 * @return A boolean.
	 *
	 * @see #isLinePass(int)
	 */
	@Override
	protected boolean isItemPass(int pass) {
		return (pass == 2);
	}

	/**
	 * Returns <code>true</code> if this is the pass where the lines are
	 * drawn.
	 *
	 * @param pass  the pass index.
	 *
	 * @return A boolean.
	 *
	 * @see #isItemPass(int)
	 */
	@Override
	protected boolean isLinePass(int pass) {
		return (pass == 1);
	}

	/**
	 * Draws the visual representation of a single data item.
	 *
	 * @param g2  the graphics device.
	 * @param state  the renderer state.
	 * @param dataArea  the area within which the data is being drawn.
	 * @param info  collects information about the drawing.
	 * @param plot  the plot (can be used to obtain standard color
	 *			  information etc).
	 * @param domainAxis  the domain axis.
	 * @param rangeAxis  the range axis.
	 * @param dataset  the dataset.
	 * @param series  the series index (zero-based).
	 * @param item  the item index (zero-based).
	 * @param crosshairState  crosshair information for the plot
	 *						(<code>null</code> permitted).
	 * @param pass  the pass index.
	 */
	@Override
	public void drawItem(Graphics2D g2,
			XYItemRendererState state,
			Rectangle2D dataArea,
			PlotRenderingInfo info,
			XYPlot plot,
			ValueAxis domainAxis,
			ValueAxis rangeAxis,
			XYDataset dataset,
			int series,
			int item,
			CrosshairState crosshairState,
			int pass) {

		// do nothing if item is not visible
		if (!getItemVisible(series, item)) {
			return;
		}

		// first pass draws the shading
		if (pass == 0) {
			CustomXYIntervalSeriesCollection intervalDataset = (CustomXYIntervalSeriesCollection) dataset;
			State drState = (State) state;

			double x = intervalDataset.getXValue(series, item);
			for (int j = 0; j < 3 ; j++) {
				if (!intervalDataset.displayMinMax() && j == 0) {
					continue;
				}
				if (!intervalDataset.displayInterquatileRange() && j == 1) {
					continue;
				}
				if (!intervalDataset.displayConfidenceInterval() && j == 2) {
					continue;
				}

				double yLow = intervalDataset.getMinYValue(series, item);
				double yHigh = intervalDataset.getMaxYValue(series, item);
				if (Double.isNaN(yLow)) {
					yLow = rangeAxis.getRange().getLowerBound();
					rangeAxis.setRange(yLow, yHigh);
				}
				List downList = drState.minCoordinates;
				List upList = drState.maxCoordinates;
				float alpha = this.alpha;
				switch(j) {
				case 0: break;
				case 1:
					yLow = intervalDataset.get1QuartYValue(series, item);
					yHigh = intervalDataset.get3QuartYValue(series, item);
					downList = drState.firstQuartileCoordinates;
					upList = drState.thirdQuartileCoordinates;
					break;
				case 2:
					yLow = intervalDataset.getLowerConfidenceYValue(series, item);
					yHigh = intervalDataset.getUpperConfidenceYValue(series, item);
					downList = drState.lowerConfidenceCoordinates;
					upList = drState.upperConfidenceCoordinates;
				}

				RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
				RectangleEdge yAxisLocation = plot.getRangeAxisEdge();

				double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
				double yyLow = rangeAxis.valueToJava2D(yLow, dataArea,
						yAxisLocation);
				double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea,
						yAxisLocation);

				PlotOrientation orientation = plot.getOrientation();
				if (orientation == PlotOrientation.HORIZONTAL) {
					downList.add(new double[] {yyLow, xx});
					upList.add(new double[] {yyHigh, xx});
				}
				else if (orientation == PlotOrientation.VERTICAL) {
					downList.add(new double[] {xx, yyLow});
					upList.add(new double[] {xx, yyHigh});
				}

				if (item == (dataset.getItemCount(series) - 1)) {
					// last item in series, draw the lot...
					// set up the alpha-transparency...
					Composite originalComposite = g2.getComposite();
					g2.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, alpha));
					Color p = (Color)getItemFillPaint(series, item);
					if (j == 0) {
						p.brighter();
					}
					if (j <= 2) {
						p.darker();
					}
					g2.setPaint(getItemFillPaint(series, item));
					GeneralPath area = new GeneralPath();
					double[] coords = (double[]) downList.get(0);
					area.moveTo((float) coords[0], (float) coords[1]);
					for (int i = 1; i < downList.size(); i++) {
						coords = (double[]) downList.get(i);
						area.lineTo((float) coords[0], (float) coords[1]);
					}
					int count = upList.size();
					coords = (double[]) upList.get(count - 1);
					area.lineTo((float) coords[0], (float) coords[1]);
					for (int i = count - 2; i >= 0; i--) {
						coords = (double[]) upList.get(i);
						area.lineTo((float) coords[0], (float) coords[1]);
					}
					area.closePath();
					g2.fill(area);
					g2.setComposite(originalComposite);
					if (j == 1) {
						Stroke s = new BasicStroke(1f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, medDash, 0f);
						g2.setStroke(s);
						g2.setPaint(Color.BLACK);
						g2.draw(area);
					}
					if (j == 2) {
						Stroke s = new BasicStroke(1f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, medFirst, 0f);
						g2.setStroke(s);
						g2.setPaint(Color.BLACK);
						g2.draw(area);
					}



					downList.clear();
					upList.clear();
				}
			}
		}
		if (isLinePass(pass)) {

			// the following code handles the line for the y-values...it's
			// all done by code in the super class
			if (item == 0) {
				State s = (State) state;
				s.seriesPath.reset();
				s.seriesFirstPath.reset();
				s.seriesMedianPath.reset();
				s.setLastPointGood(false);
			}

			if (getItemLineVisible(series, item)) {
				drawPrimaryLineAsPath(state, g2, plot, dataset, pass,
						series, item, domainAxis, rangeAxis, dataArea);


			}
		}

		// second pass adds shapes where the items are ..
		else if (isItemPass(pass)) {
			CustomXYIntervalSeriesCollection intervalDataset = (CustomXYIntervalSeriesCollection) dataset;

			// setup for collecting optional entity info...
			EntityCollection entities = null;
			if (info != null) {
				entities = info.getOwner().getEntityCollection();
			}
			if (intervalDataset.displayAnyLine()) {
				drawSecondaryPass(g2, plot, dataset, pass, series, item,
						domainAxis, dataArea, rangeAxis, crosshairState, entities);
			}
		}
	}

	/**
	 * Draws the item (first pass). This method draws the lines
	 * connecting the items. Instead of drawing separate lines,
	 * a GeneralPath is constructed and drawn at the end of
	 * the series painting.
	 *
	 * @param g2  the graphics device.
	 * @param state  the renderer state.
	 * @param plot  the plot (can be used to obtain standard color information
	 *			  etc).
	 * @param dataset  the dataset.
	 * @param pass  the pass.
	 * @param series  the series index (zero-based).
	 * @param item  the item index (zero-based).
	 * @param domainAxis  the domain axis.
	 * @param rangeAxis  the range axis.
	 * @param dataArea  the area within which the data is being drawn.
	 */
	@Override
	protected void drawPrimaryLineAsPath(XYItemRendererState state,
			Graphics2D g2, XYPlot plot,
			XYDataset dataset,
			int pass,
			int series,
			int item,
			ValueAxis domainAxis,
			ValueAxis rangeAxis,
			Rectangle2D dataArea) {


		RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
		RectangleEdge yAxisLocation = plot.getRangeAxisEdge();

		CustomXYIntervalSeriesCollection col = (CustomXYIntervalSeriesCollection)dataset;

		// get the data point...
		double x1 = col.getXValue(series, item);
		double meanY = col.getYMeanValue(series, item);
		double medY = col.getYMedianValue(series, item);
		double firstY = col.getYFirstValue(series, item);

		/*double x = col.getXValue(series, item);
		double yMean = col.getYMeanValue(series, item);
		double yMed = col.getYMedianValue(series, item);*/


		double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
		double transYMean = rangeAxis.valueToJava2D(meanY, dataArea, yAxisLocation);
		double transYMed = rangeAxis.valueToJava2D(medY, dataArea, yAxisLocation);
		double transY1 = rangeAxis.valueToJava2D(firstY, dataArea, yAxisLocation);

		State s = (State) state;

		for (int i = 0 ; i < 3 ; i++) {
			if (!col.displayMean() && i == 0) {
				continue;
			}
			if (!col.displayMedian() && i == 1) {
				continue;
			}
			if (!col.displayFirst() && i == 2) {
				continue;
			}
			double transY = transYMean;
			GeneralPath p = s.seriesPath;
			switch(i) {
			case 0:
				transY = transYMean;
				p = s.seriesPath;
				break;
			case 1:
				transY = transYMed;
				p = s.seriesMedianPath;
				break;
			case 2:
				transY = transY1;
				p = s.seriesFirstPath;
				break;
			}
			// update path to reflect latest point
			if (!Double.isNaN(transX1) && !Double.isNaN(transY)) {
				float x = (float) transX1;
				float y = (float) transY;
				PlotOrientation orientation = plot.getOrientation();
				if (orientation == PlotOrientation.HORIZONTAL) {
					x = (float) transY;
					y = (float) transX1;
				}
				if (s.isLastPointGood()) {
					try{
						p.lineTo(x, y);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					p.moveTo(x, y);
				}
				s.setLastPointGood(false);
				if (i == 0 && (!col.displayMedian() && !col.displayFirst() || item > 0)) {
					s.setLastPointGood(true);
				}
				if (i == 1 && (!col.displayFirst() || item > 0)) {
					s.setLastPointGood(true);
				}
				if (i == 2) {
					s.setLastPointGood(true);
				}
			} else {
				if (chart != null) {
					if (Double.isNaN(transX1))
						chart.addProblem(new Problem(Problem.Severity.WARNING, "at least one x value not represented (NaN)",""));
						chart.updateLabel();
					if (Double.isNaN(transY))
						chart.addProblem(new Problem(Problem.Severity.WARNING, "at least one y value not represented (NaN)",""));	
				}					
			}
			/*	else {
				s.setLastPointGood(false);
			}*/
			// if this is the last item, draw the path ...
			if (item == dataset.getItemCount(series) - 1) {
				// draw path
				drawFirstPassShape(g2, pass, series, item, p, i);
				//If last item of the graph (with 3 curves), set LastPoint as not good. (It will be erased by the next repaint)
				if ((i == 2) || (col.displayMedian() && !col.displayFirst() && i == 1) || (col.displayMean() && !col.displayMedian() && !col.displayFirst() && i == 0)) {
					s.setLastPointGood(false);
				}
			}
		}
	}


	double[][] previousPoints;
	int index;
	boolean usePrev;
	
	public void useMultipointHighlight(boolean b) {
		usePrev = b;
	}

	/**
	 * Draws the item shapes and adds chart entities (second pass). This method
	 * draws the shapes which mark the item positions. If <code>entities</code>
	 * is not <code>null</code> it will be populated with entity information
	 * for points that fall within the data area.
	 *
	 * @param g2  the graphics device.
	 * @param plot  the plot (can be used to obtain standard color
	 *              information etc).
	 * @param domainAxis  the domain axis.
	 * @param dataArea  the area within which the data is being drawn.
	 * @param rangeAxis  the range axis.
	 * @param dataset  the dataset.
	 * @param pass  the pass.
	 * @param series  the series index (zero-based).
	 * @param item  the item index (zero-based).
	 * @param crosshairState  the crosshair state.
	 * @param entities the entity collection.
	 */
	@Override
	protected void drawSecondaryPass(Graphics2D g2, XYPlot plot,
			XYDataset dataset,
			int pass, int series, int item,
			ValueAxis domainAxis,
			Rectangle2D dataArea,
			ValueAxis rangeAxis,
			CrosshairState crosshairState,
			EntityCollection entities) {

		Shape entityArea = null;

		// get the data point...
		double x1 = dataset.getXValue(series, item);
		double y1 = dataset.getYValue(series, item);
		if (Double.isNaN(y1) || Double.isNaN(x1)) {
			return;
		}
		int equals = 0;
		for (int i = 0 ; i < index ; i++) {
			if (previousPoints[i][0] == x1 && previousPoints[i][1] == y1) {
				equals++;
			}
		}
		if (index < previousPoints.length) {
			previousPoints[index][0] = x1;
			previousPoints[index][1] = y1;
			index++;
		}

		PlotOrientation orientation = plot.getOrientation();
		RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
		RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
		double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
		double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);

		if (getItemShapeVisible(series, item)) {
			Shape shape = getItemShape(series, item);
			if (orientation == PlotOrientation.HORIZONTAL) {
				shape = ShapeUtilities.createTranslatedShape(shape, transY1,
						transX1);
			}
			else if (orientation == PlotOrientation.VERTICAL) {
				shape = ShapeUtilities.createTranslatedShape(shape, transX1,
						transY1);
			}
			entityArea = shape;
			if (shape.intersects(dataArea)) {
				if (getItemShapeFilled(series, item)) {
					if (getUseFillPaint()) {
						g2.setPaint(getItemFillPaint(series, item));
					}
					else {
						g2.setPaint(getItemPaint(series, item));
					}
					if (equals > 0 && usePrev) {
						g2.drawOval((int)(transX1 - 3 - 2*equals),
								(int)(transY1 - 3 - 2*equals),
								6 + 4*equals,
								6 + 4*equals);
					} else {
						g2.fill(shape);
					}
				}
				if (equals == 0) {
					if (getDrawOutlines()) {
						if (getUseOutlinePaint()) {
							g2.setPaint(getItemOutlinePaint(series, item));
						}
						else {
							g2.setPaint(getItemPaint(series, item));
						}
						g2.setStroke(getItemOutlineStroke(series, item));
						g2.draw(shape);
					}
				}
			}
		}

		double xx = transX1;
		double yy = transY1;
		if (orientation == PlotOrientation.HORIZONTAL) {
			xx = transY1;
			yy = transX1;
		}

		// draw the item label if there is one...
		if (isItemLabelVisible(series, item)) {
			drawItemLabel(g2, orientation, dataset, series, item, xx, yy,
					(y1 < 0.0));
		}

		int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
		int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
		updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex,
				rangeAxisIndex, transX1, transY1, orientation);

		// add an entity for the item, but only if it falls within the data
		// area...
		if (entities != null && isPointInRect(dataArea, xx, yy)) {
			addEntity(entities, entityArea, dataset, series, item, xx, yy);
		}
	}

	
	private static AffineTransform legendShapeEnlarger = AffineTransform.getScaleInstance(1.3, 1.3);

	   /**
     * Returns a legend item for the specified series.
     *
     * @param datasetIndex  the dataset index (zero-based).
     * @param series  the series index (zero-based).
     *
     * @return A legend item for the series.
     */
	@Override
    public LegendItem getLegendItem(int datasetIndex, int series) {

        XYPlot plot = getPlot();
        if (plot == null) {
            return null;
        }

        LegendItem result = null;
        XYDataset dataset = plot.getDataset(datasetIndex);
        if (dataset != null) {
            if (getItemVisible(series, 0)) {
                String label = getLegendItemLabelGenerator().generateLabel(
                        dataset, series);
                String description = label;
                String toolTipText = null;
                if (getLegendItemToolTipGenerator() != null) {
                    toolTipText = getLegendItemToolTipGenerator().generateLabel(
                            dataset, series);
                }
                String urlText = null;
                if (getLegendItemURLGenerator() != null) {
                    urlText = getLegendItemURLGenerator().generateLabel(
                            dataset, series);
                }
                boolean shapeIsVisible = getItemShapeVisible(series, 0);
                Shape shape = lookupSeriesShape(series);
                shape = legendShapeEnlarger.createTransformedShape(shape);
                boolean shapeIsFilled = getItemShapeFilled(series, 0);
                Paint fillPaint = (getUseFillPaint()
                    ? lookupSeriesFillPaint(series)
                    : lookupSeriesPaint(series));
                boolean shapeOutlineVisible = getDrawOutlines();
                Paint outlinePaint = (getUseOutlinePaint()
                    ? lookupSeriesOutlinePaint(series)
                    : lookupSeriesPaint(series));
                Stroke outlineStroke = lookupSeriesOutlineStroke(series);
                boolean lineVisible = getItemLineVisible(series, 0);
                Stroke lineStroke = lookupSeriesStroke(series);
                Paint linePaint = lookupSeriesPaint(series);
                result = new LegendItem(label, description, toolTipText,
                        urlText, shapeIsVisible, shape, shapeIsFilled,
                        fillPaint, shapeOutlineVisible, outlinePaint,
                        outlineStroke, lineVisible, getLegendLine(),
                        lineStroke, linePaint);
                result.setSeriesKey(dataset.getSeriesKey(series));
                result.setSeriesIndex(series);
                result.setDataset(dataset);
                result.setDatasetIndex(datasetIndex);
            }
        }

        return result;

    }


	/**
	 * Draws the first pass shape.
	 *
	 * @param g2  the graphics device.
	 * @param pass  the pass.
	 * @param series  the series index.
	 * @param item  the item index.
	 * @param shape  the shape.
	 */

	private static final float[] medDash = new float[]{2f,2f};
	private static final float[] medFirst = new float[]{2f,5f};
	protected void drawFirstPassShape(Graphics2D g2, int pass, int series,
			int item, Shape shape, int i) {
		BasicStroke s = (BasicStroke)getItemStroke(series, item);
		if (i == 1) {
			s = new BasicStroke(1f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, medDash, 0f);
		}
		if (i == 2) {
			s = new BasicStroke(1f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, medFirst, 0f);
		}
		g2.setStroke(s);
		g2.setPaint(getItemPaint(series, item));
		g2.draw(shape);
	}
}
