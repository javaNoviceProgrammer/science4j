package visualizer4j.charts;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.Tick;
import org.jfree.chart.axis.TickType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.data.Range;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

public class CustomNumberAxis extends NumberAxis {
	
	private static class LocalNumberTick extends NumberTick {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LocalNumberTick(Number number, String label,
                      TextAnchor textAnchor, 
                      TextAnchor rotationAnchor, double angle) {
        	super(number, label, textAnchor, rotationAnchor, angle);
        }		
	}

	private static final long serialVersionUID = 4240042954586057850L;

	private double maxRange;
	
	private boolean changeRange;
	
	Map<Double, String> map = null;
	
	public CustomNumberAxis(String label) {
		super(label);
		changeRange= false;
	}

	public CustomNumberAxis(String label, double maxRange) {
		super(label);
		this.maxRange = maxRange;
		this.changeRange = true;
	}

	@Override
	protected void autoAdjustRange() {
		if (changeRange) {
			setRange(new Range(0, this.maxRange), false, false);
		} else {
			super.autoAdjustRange();
		}
	}
	
	public void setTickMap(Map<Double, String> map) {
		this.map= map;
	}
	
    /**
     * Draws the axis line, tick marks and tick mark labels.
     *
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the plot area.
     * @param dataArea  the data area.
     * @param edge  the edge that the axis is aligned with.
     *
     * @return The width or height used to draw the axis.
     */
    protected AxisState drawTickMarksAndLabels(Graphics2D g2,
                                               double cursor,
                                               Rectangle2D plotArea,
                                               Rectangle2D dataArea,
                                               RectangleEdge edge) {

        AxisState state = new AxisState(cursor);

        if (isAxisLineVisible()) {
            drawAxisLine(g2, cursor, dataArea, edge);
        }

        double ol = getTickMarkOutsideLength();
        double il = getTickMarkInsideLength();

        List ticks = refreshTicks(g2, state, dataArea, edge);
        state.setTicks(ticks);
        g2.setFont(getTickLabelFont());
        Iterator iterator = ticks.iterator();
        while (iterator.hasNext()) {
            ValueTick tick = (ValueTick) iterator.next();
            // this part is added
            boolean vert = false;
            String tickText = tick.getText();
          /*  if (map != null) {
            	String rep = map.get(tick.getValue());
            	if (rep != null) {
            		tickText = rep;
            		vert = true;
            	} else {
            		tickText = "";
            	}
            }*/
            if (isTickLabelsVisible()) {
                g2.setPaint(getTickLabelPaint());
                float[] anchorPoint = calculateAnchorPoint(tick, cursor,
                        dataArea, edge);
                double angle = vert? -Math.PI/2 : tick.getAngle();
                TextUtilities.drawRotatedString(tickText, g2,
                        anchorPoint[0], anchorPoint[1], tick.getTextAnchor(),
                        angle, tick.getRotationAnchor());
            }

            if (isTickMarksVisible() && tick.getTickType().equals(
                    TickType.MAJOR)) {
                float xx = (float) valueToJava2D(tick.getValue(), dataArea,
                        edge);
                Line2D mark = null;
                g2.setStroke(getTickMarkStroke());
                g2.setPaint(getTickMarkPaint());
                if (edge == RectangleEdge.LEFT) {
                    mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
                }
                else if (edge == RectangleEdge.RIGHT) {
                    mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
                }
                else if (edge == RectangleEdge.TOP) {
                    mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
                }
                else if (edge == RectangleEdge.BOTTOM) {
                    mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
                }
                g2.draw(mark);
            }
        }

        // need to work out the space used by the tick labels...
        // so we can update the cursor...
        double used = 0.0;
        if (isTickLabelsVisible()) {
            if (edge == RectangleEdge.LEFT) {
                used += findMaximumTickLabelWidth(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorLeft(used);
            }
            else if (edge == RectangleEdge.RIGHT) {
                used = findMaximumTickLabelWidth(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorRight(used);
            }
            else if (edge == RectangleEdge.TOP) {
                used = findMaximumTickLabelHeight(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorUp(used);
            }
            else if (edge == RectangleEdge.BOTTOM) {
                used = findMaximumTickLabelHeight(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorDown(used);
            }
        }

        return state;
    }
    
    
    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
	@SuppressWarnings("unchecked")
    protected List refreshTicksHorizontal(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          RectangleEdge edge) {

        List result = new java.util.ArrayList();

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);

        if (isAutoTickUnitSelection()) {
            selectAutoTickUnit(g2, dataArea, edge);
        }

        double size = getTickUnit().getSize();
        int count = calculateVisibleTickCount();
        double lowestTickValue = calculateLowestVisibleTickValue();

        if (count <= ValueAxis.MAXIMUM_TICK_COUNT) {
            for (int i = 0; i < count; i++) {
                double currentTickValue = lowestTickValue + (i * size);
                String tickLabel;
                NumberFormat formatter = getNumberFormatOverride();
                boolean vert = false;
	            if (map != null) {
	            	String rep = map.get(currentTickValue);
	            	if (rep != null) {
	            		tickLabel = rep;
	            		vert = true;
	            	} else {
	            		tickLabel = "";
	            	}
	            } else if (formatter != null) {
                    tickLabel = formatter.format(currentTickValue);
                }
                else {
                    tickLabel = getTickUnit().valueToString(currentTickValue);
                }
                TextAnchor anchor = null;
                TextAnchor rotationAnchor = null;
                double angle = 0.0;
                if (isVerticalTickLabels() || vert) {
                    anchor = TextAnchor.CENTER_RIGHT;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
                    if (edge == RectangleEdge.TOP) {
                        angle = Math.PI / 2.0;
                    }
                    else {
                        angle = -Math.PI / 2.0;
                    }
                }
                else {
                    if (edge == RectangleEdge.TOP) {
                        anchor = TextAnchor.BOTTOM_CENTER;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
                    }
                    else {
                        anchor = TextAnchor.TOP_CENTER;
                        rotationAnchor = TextAnchor.TOP_CENTER;
                    }
                }
                Tick tick;
				if (vert) {
	                tick = new LocalNumberTick(new Double(currentTickValue),
	                		tickLabel, anchor, rotationAnchor, angle);
          	   } else {
	                tick = new NumberTick(new Double(currentTickValue),
	                		tickLabel, anchor, rotationAnchor, angle);          	   	
          	   }
                result.add(tick);
            }
        }
        return result;
    }    
  
	@SuppressWarnings("unchecked")    
    protected double findMaximumTickLabelHeight(List ticks,
                                                Graphics2D g2,
                                                Rectangle2D drawArea,
                                                boolean vertical) {
		boolean vert = false;
		for (Tick t : (List<Tick>)ticks) {
			if (t instanceof LocalNumberTick) {
				vert = true;
				break;
			}
		}
        RectangleInsets insets = getTickLabelInsets();
        Font font = getTickLabelFont();
        double maxHeight = 0.0;
        if (vertical || vert) {
            FontMetrics fm = g2.getFontMetrics(font);
            Iterator iterator = ticks.iterator();
            while (iterator.hasNext()) {
                Tick tick = (Tick) iterator.next();
                Rectangle2D labelBounds = TextUtilities.getTextBounds(
                        tick.getText(), g2, fm);
                if (labelBounds.getWidth() + insets.getTop()
                        + insets.getBottom() > maxHeight) {
                    maxHeight = labelBounds.getWidth()
                                + insets.getTop() + insets.getBottom();
                }
            }
        }
        else {
            LineMetrics metrics = font.getLineMetrics("ABCxyz",
                    g2.getFontRenderContext());
            maxHeight = metrics.getHeight()
                        + insets.getTop() + insets.getBottom();
        }
        return maxHeight;

    }    
    
    
    
    
    

}
