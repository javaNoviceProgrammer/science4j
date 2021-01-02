package visualizer4j.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;

import visualizer4j.charts.paints.Texture;

import visualizer4j.logging.Logger;
import ch.epfl.general_libraries.results.DataRetrievalOptions;


public class CustomBarRenderer extends BarRenderer {
	
	private static Logger logger = new Logger(CustomBarRenderer.class);

	private static final long serialVersionUID = 7682299239686959905L;

	private boolean[] s1;
	private boolean[] s2;

	public CustomBarRenderer(DataRetrievalOptions options) {
		super();
		s1 = new boolean[]{options.isMeanOrSum, options.isMedian, options.isFirst};
		s2 = new boolean[]{options.isMax, options.isConfInt, options.isQuartInt};		
	}

	@Override
	public void drawItem(Graphics2D g2, CategoryItemRendererState state,
			Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
			ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
			int pass) {
		if (dataset instanceof CustomCategoryDataset) {
			this.drawCustomBar(g2, state, dataArea, plot, domainAxis,
					rangeAxis, (CustomCategoryDataset) dataset, row, column,
					pass);
		} else {
			this.drawNormalBar(g2, state, dataArea, plot, domainAxis,
					rangeAxis, dataset, row, column, pass,
					dataset.getValue(row, column));
		}
	}

	private void drawNormalBar(Graphics2D g2, CategoryItemRendererState state,
			Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
			ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
			int pass, Number dataValue) {

		// nothing is drawn for null values...
		if (dataValue == null) {
			return;
		}

		double value = dataValue.doubleValue();
		PlotOrientation orientation = plot.getOrientation();
		double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis,
				state, row, column);
		double[] barL0L1 = calculateBarL0L1(value);
		if (barL0L1 == null) {
			return; // the bar is not visible
		}

		RectangleEdge edge = plot.getRangeAxisEdge();
		double transL0 = rangeAxis.valueToJava2D(barL0L1[0], dataArea, edge);
		double transL1 = rangeAxis.valueToJava2D(barL0L1[1], dataArea, edge);

		// in the following code, barL0 is (in Java2D coordinates) the LEFT
		// end of the bar for a horizontal bar chart, and the TOP end of the
		// bar for a vertical bar chart. Whether this is the BASE of the bar
		// or not depends also on (a) whether the data value is 'negative'
		// relative to the base value and (b) whether or not the range axis is
		// inverted. This only matters if/when we apply the minimumBarLength
		// attribute, because we should extend the non-base end of the bar
		boolean positive = (value >= super.getBase());
		boolean inverted = rangeAxis.isInverted();
		double barL0 = Math.min(transL0, transL1);
		double barLength = Math.abs(transL1 - transL0);
		double barLengthAdj = 0.0;
		if (barLength > 0.0 && barLength < getMinimumBarLength()) {
			barLengthAdj = getMinimumBarLength() - barLength;
		}
		double barL0Adj = 0.0;
		if (orientation == PlotOrientation.HORIZONTAL) {
			if (positive && inverted || !positive && !inverted) {
				barL0Adj = barLengthAdj;
			}
		} else {
			if (positive && !inverted || !positive && inverted) {
				barL0Adj = barLengthAdj;
			}
		}

		// draw the bar...
		Rectangle2D bar = null;
		if (orientation == PlotOrientation.HORIZONTAL) {
			bar = new Rectangle2D.Double(barL0 - barL0Adj, barW0, barLength
					+ barLengthAdj, state.getBarWidth());
		} else {
			bar = new Rectangle2D.Double(barW0, barL0 - barL0Adj,
					state.getBarWidth(), barLength + barLengthAdj);
		}
		Color color = (Color) getItemPaint(row, column);
		Texture itemPaint = (Texture) getItemFillPaint(row, column);
		itemPaint.setColor(color);
		int r = color.getRed();
		int b = color.getBlue();
		int g = color.getGreen();
		logger.trace("Selected color " + r + " " + b + " " + g);
		g2.setPaint(itemPaint);
		g2.fill(bar);

		// draw the outline...
		if (isDrawBarOutline()
				&& state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
			Stroke stroke = getItemOutlineStroke(row, column);
			Paint paint = getItemOutlinePaint(row, column);
			if (stroke != null && paint != null) {
				g2.setStroke(stroke);
				g2.setPaint(paint);
				g2.draw(bar);
			}
		}

		CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
				column);
		if (generator != null && isItemLabelVisible(row, column)) {
			drawItemLabel(g2, dataset, row, column, plot, generator, bar,
					(value < 0.0));
		}

		// add an item entity, if this information is being collected
		EntityCollection entities = state.getEntityCollection();
		if (entities != null) {
			addItemEntity(entities, dataset, row, column, bar);
		}
	}

	private void DrawFilledRectangles(Graphics2D g2,
			CategoryItemRendererState state, Rectangle2D dataArea,
			CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis,
			CustomCategoryDataset dataset, int row, int column, int pass,
			Number dataValue, boolean median, boolean border) {
		// nothing is drawn for null values...
		if (dataValue == null) {
			return;
		}

		double value = dataValue.doubleValue();
		PlotOrientation orientation = plot.getOrientation();
		double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis,
				state, row, column);
		double[] barL0L1 = calculateBarL0L1(value);
		if (barL0L1 == null) {
			return; // the bar is not visible
		}

		RectangleEdge edge = plot.getRangeAxisEdge();
		double transL0 = rangeAxis.valueToJava2D(barL0L1[0], dataArea, edge);
		double transL1 = rangeAxis.valueToJava2D(barL0L1[1], dataArea, edge);

		// in the following code, barL0 is (in Java2D coordinates) the LEFT
		// end of the bar for a horizontal bar chart, and the TOP end of the
		// bar for a vertical bar chart. Whether this is the BASE of the bar
		// or not depends also on (a) whether the data value is 'negative'
		// relative to the base value and (b) whether or not the range axis is
		// inverted. This only matters if/when we apply the minimumBarLength
		// attribute, because we should extend the non-base end of the bar
		boolean positive = (value >= super.getBase());
		boolean inverted = rangeAxis.isInverted();
		double barL0 = Math.min(transL0, transL1);
		double barLength = Math.abs(transL1 - transL0);
		double barLengthAdj = 0.0;
		if (barLength > 0.0 && barLength < getMinimumBarLength()) {
			barLengthAdj = getMinimumBarLength() - barLength;
		}
		double barL0Adj = 0.0;
		if (orientation == PlotOrientation.HORIZONTAL) {
			if (positive && inverted || !positive && !inverted) {
				barL0Adj = barLengthAdj;
			}
		} else {
			if (positive && !inverted || !positive && inverted) {
				barL0Adj = barLengthAdj;
			}
		}

		double x1, y1, x2, y2;
		if (orientation == PlotOrientation.HORIZONTAL) {
			x1 = barL0 - barL0Adj;
			y1 = barW0;
			x2 = x1 + 3;
			y2 = y1 + state.getBarWidth();
		} else {
			x1 = barW0;
			y1 = barL0 - barL0Adj;
			x2 = x1 + state.getBarWidth();
			y2 = y1 + 3;
		}

		Rectangle2D rect = new Rectangle2D.Double(x1, y1, Math.abs(x2 - x1),
				Math.abs(y2 - y1));
		g2.setColor(Color.BLACK);
		if (median) {
			if (border) {
				g2.draw(rect);
			}
			g2.setColor(Color.WHITE);
		}
		g2.fill(rect);

	}

	private void drawLine(Graphics2D g2, CategoryItemRendererState state,
			Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
			ValueAxis rangeAxis, CustomCategoryDataset dataset, int row,
			int column, int pass, Number min, Number max, int place) {
		if (min == null || max == null) {
			return;
		}

		double maxValue = max.doubleValue();
		double minValue = min.doubleValue();

		PlotOrientation orientation = plot.getOrientation();
		double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis,
				state, row, column);

		RectangleEdge edge = plot.getRangeAxisEdge();

		double minCoor = rangeAxis.valueToJava2D(minValue, dataArea, edge);
		double maxCoor = rangeAxis.valueToJava2D(maxValue, dataArea, edge);


		double x1, y1, x2, y2;
		if (orientation == PlotOrientation.HORIZONTAL) {
			x1 = minCoor;
			y1 = barW0;
			x2 = maxCoor;
			y2 = y1 + state.getBarWidth();
			if (place == 0) {
				y2 = y1;
			} else if (place == 1) {
				y2 = (y1 + y2) / 2.0;
				y1 = y2;
			} else {
				y1 = y2;
			}
		} else {
			x1 = barW0;
			y1 = minCoor;
			x2 = x1 + state.getBarWidth();
			y2 = maxCoor;
			if (place == 0) {
				x2 = x1;
			} else if (place == 1) {
				x2 = (x1 + x2) / 2.0;
				x1 = x2;
			} else {
				x1 = x2;
			}
		}
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		Stroke s = new BasicStroke(2);
		g2.setStroke(s);
		g2.setColor(Color.BLACK);
		g2.draw(line);
	}

	private void drawCustomBar(Graphics2D g2, CategoryItemRendererState state,
			Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
			ValueAxis rangeAxis, CustomCategoryDataset dataset, int row,
			int column, int pass) {

		for (int i = 0; i < 3; ++i) {
			if (s1[i]) {
				switch (i) {
				case 0: {
					Number dataValue = dataset.getValue(0, row, column);
					this.drawNormalBar(g2, state, dataArea, plot, domainAxis,
							rangeAxis, dataset, row, column, pass, dataValue);
					break;
				}
				case 1: {
					if (s1[0]) {
						Number dataValue = dataset.getValue(1, row, column);
						boolean border = dataValue.doubleValue() > dataset
						.getValue(0, row, column).doubleValue();
						this.DrawFilledRectangles(g2, state, dataArea, plot,
								domainAxis, rangeAxis, dataset, row, column,
								pass, dataValue, true, border);
					} else {
						Number dataValue = dataset.getValue(1, row, column);
						this.drawNormalBar(g2, state, dataArea, plot,
								domainAxis, rangeAxis, dataset, row, column,
								pass, dataValue);
					}
					break;
				}
				case 2: {
					if (s1[0] || s1[1]) {
						Number dataValue = dataset.getValue(9, row, column);
						this.DrawFilledRectangles(g2, state, dataArea, plot,
								domainAxis, rangeAxis, dataset, row, column,
								pass, dataValue, false, false);
					} else {
						Number dataValue = dataset.getValue(9, row, column);
						this.drawNormalBar(g2, state, dataArea, plot,
								domainAxis, rangeAxis, dataset, row, column,
								pass, dataValue);
					}
					break;
				}
				default:
					break;
				}
			}
		}
		for (int i = 0; i < 3; ++i) {
			if (s2[i]) {
				switch (i) {
				case 0: {
					Number min = dataset.getValue(4, row, column);
					Number max = dataset.getValue(5, row, column);
					this.drawLine(g2, state, dataArea, plot, domainAxis,
							rangeAxis, dataset, row, column, pass, min, max, i);
					break;
				}
				case 1: {
					Number min = dataset.getValue(2, row, column);
					Number max = dataset.getValue(3, row, column);
					this.drawLine(g2, state, dataArea, plot, domainAxis,
							rangeAxis, dataset, row, column, pass, min, max, i);
					break;
				}
				case 2: {
					Number min = dataset.getValue(6, row, column);
					Number max = dataset.getValue(7, row, column);
					this.drawLine(g2, state, dataArea, plot, domainAxis,
							rangeAxis, dataset, row, column, pass, min, max, i);
					break;
				}
				default:
					break;
				}
			}
		}
	}
}