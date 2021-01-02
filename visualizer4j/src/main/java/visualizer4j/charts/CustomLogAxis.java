package visualizer4j.charts;


/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2011, by Object Refinery Limited and Contributors.
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
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * --------------------
 * LogarithmicAxis.java
 * --------------------
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Michael Duffy / Eric Thomas;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   David M. O'Donnell;
 *                   Scott Sams;
 *                   Sergei Ivanov;
 *
 * Changes
 * -------
 * 14-Mar-2002 : Version 1 contributed by Michael Duffy (DG);
 * 19-Apr-2002 : drawVerticalString() is now drawRotatedString() in
 *               RefineryUtilities (DG);
 * 23-Apr-2002 : Added a range property (DG);
 * 15-May-2002 : Modified to be able to deal with negative and zero values (via
 *               new 'adjustedLog10()' method);  occurrences of "Math.log(10)"
 *               changed to "LOG10_VALUE"; changed 'intValue()' to
 *               'longValue()' in 'refreshTicks()' to fix label-text value
 *               out-of-range problem; removed 'draw()' method; added
 *               'autoRangeMinimumSize' check; added 'log10TickLabelsFlag'
 *               parameter flag and implementation (ET);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 16-Jul-2002 : Implemented support for plotting positive values arbitrarily
 *               close to zero (added 'allowNegativesFlag' flag) (ET).
 * 05-Sep-2002 : Updated constructor reflecting changes in the Axis class (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 22-Nov-2002 : Bug fixes from David M. O'Donnell (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 08-May-2003 : Fixed plotting of datasets with lower==upper bounds when
 *               'minAutoRange' is very small; added 'strictValuesFlag'
 *               and default functionality of throwing a runtime exception
 *               if 'allowNegativesFlag' is false and any values are less
 *               than or equal to zero; added 'expTickLabelsFlag' and
 *               changed to use "1e#"-style tick labels by default
 *               ("10^n"-style tick labels still supported via 'set'
 *               method); improved generation of tick labels when range of
 *               values is small; changed to use 'NumberFormat.getInstance()'
 *               to create 'numberFormatterObj' (ET);
 * 14-May-2003 : Merged HorizontalLogarithmicAxis and
 *               VerticalLogarithmicAxis (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 07-Nov-2003 : Modified to use new NumberTick class (DG);
 * 08-Apr-2004 : Use numberFormatOverride if set - see patch 930139 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 21-Apr-2005 : Added support for upper and lower margins; added
 *               get/setAutoRangeNextLogFlag() methods and changed
 *               default to 'autoRangeNextLogFlag'==false (ET);
 * 22-Apr-2005 : Removed refreshTicks() and fixed names and parameters for
 *               refreshHorizontalTicks() & refreshVerticalTicks();
 *               changed javadoc on setExpTickLabelsFlag() to specify
 *               proper default (ET);
 * 22-Apr-2005 : Renamed refreshHorizontalTicks --> refreshTicksHorizontal
 *               (and likewise the vertical version) for consistency with
 *               other axis classes (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 * 02-Mar-2007 : Applied patch 1671069 to fix zooming (DG);
 * 22-Mar-2007 : Use new defaultAutoRange attribute (DG);
 *
 */

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.Tick;
import org.jfree.data.Range;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 * A numerical axis that uses a logarithmic scale.
 */
public class CustomLogAxis extends LogarithmicAxis {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomLogAxis(String label) {
        super(label);
    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
	@SuppressWarnings("unchecked")	
    protected List refreshTicksVertical(Graphics2D g2,
                                        Rectangle2D dataArea,
                                        RectangleEdge edge) {

        List ticks = new java.util.ArrayList();
        Range range = getRange();

        //get lower bound value:
        double lowerBoundVal = range.getLowerBound();
              //if small log values and lower bound value too small
              // then set to a small value (don't allow <= 0):
        if (this.smallLogFlag && lowerBoundVal < SMALL_LOG_VALUE) {
            lowerBoundVal = SMALL_LOG_VALUE;
        }

        //get upper bound value
        double upperBoundVal = range.getUpperBound();

        //get log10 version of lower bound and round to integer:
        int iBegCount = (int) Math.rint(switchedLog10(lowerBoundVal));
        //get log10 version of upper bound and round to integer:
        int iEndCount = (int) Math.rint(switchedLog10(upperBoundVal));

        if (iBegCount == iEndCount && iBegCount > 0
                && Math.pow(10, iBegCount) > lowerBoundVal) {
              //only 1 power of 10 value, it's > 0 and its resulting
              // tick value will be larger than lower bound of data
            --iBegCount;       //decrement to generate more ticks
        }

        double currentTickValue;
        String tickLabel;
        boolean zeroTickFlag = false;
        for (int i = iBegCount; i <= iEndCount; i++) {
            //for each power of 10 value; create ten ticks
            for (int j = 0; j < 10; ++j) {
                //for each tick to be displayed
                if (this.smallLogFlag) {
                    //small log values in use; create numeric value for tick
                    currentTickValue = Math.pow(10, i) + (Math.pow(10, i) * j);
                    if (this.expTickLabelsFlag
                        || (i < 0 && currentTickValue > 0.0
                        && currentTickValue < 1.0)) {
                        //showing "1e#"-style ticks or negative exponent
                        // generating tick value between 0 & 1; show fewer
                        if (j == 0 || (i > -4 && j < 2)
                                   || currentTickValue >= upperBoundVal) {
                          //first tick of series, or not too small a value and
                          // one of first 3 ticks, or last tick to be displayed
                            // set exact number of fractional digits to be shown
                            // (no effect if showing "1e#"-style ticks):
                            this.numberFormatterObj
                                .setMaximumFractionDigits(-i);
                               //create tick label (force use of fmt obj):
                            tickLabel = makeTickLabel(currentTickValue, true);
                        }
                        else {    //no tick label to be shown
                            tickLabel = "";
                        }
                    }
                    else {     //tick value not between 0 & 1
                               //show tick label if it's the first or last in
                               // the set, or if it's 1-5; beyond that show
                               // fewer as the values get larger:
                        tickLabel = (j < 1 || (i < 1 && j < 5) || (j < 4 - i)
                                         || currentTickValue >= upperBoundVal)
                                         ? makeTickLabel(currentTickValue) : "";
                    }
                }
                else { //not small log values in use; allow for values <= 0
                    if (zeroTickFlag) {   //if did zero tick last iter then
                        --j;              //decrement to do 1.0 tick now
                    }     //calculate power-of-ten value for tick:
                    currentTickValue = (i >= 0)
                        ? Math.pow(10, i) + (Math.pow(10, i) * j)
                        : -(Math.pow(10, -i) - (Math.pow(10, -i - 1) * j));
                    if (!zeroTickFlag) {  // did not do zero tick last iteration
                        if (Math.abs(currentTickValue - 1.0) < 0.0001
                            && lowerBoundVal <= 0.0 && upperBoundVal >= 0.0) {
                            //tick value is 1.0 and 0.0 is within data range
                            currentTickValue = 0.0;     //set tick value to zero
                            zeroTickFlag = true;        //indicate zero tick
                        }
                    }
                    else {     //did zero tick last iteration
                        zeroTickFlag = false;         //clear flag
                    }               //create tick label string:
                               //show tick label if "1e#"-style and it's one
                               // of the first two, if it's the first or last
                               // in the set, or if it's 1-5; beyond that
                               // show fewer as the values get larger:
                    tickLabel = ((this.expTickLabelsFlag && j < 2)
                                || j < 1
                                || (i < 1 && j < 5) || (j < 4 - i)
                                || currentTickValue >= upperBoundVal)
                                   ? makeTickLabel(currentTickValue) : "";
                }

                if (currentTickValue > upperBoundVal) {
                    return ticks;   // if past highest data value then exit
                                    // method
                }

                if (currentTickValue >= lowerBoundVal - SMALL_LOG_VALUE) {
                    //tick value not below lowest data value
                    TextAnchor anchor = null;
                    TextAnchor rotationAnchor = null;
                    double angle = 0.0;
                    if (isVerticalTickLabels()) {
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

                    Tick tick = new NumberTick(new Double(currentTickValue),
                            tickLabel, anchor, rotationAnchor, angle);
                    ticks.add(tick);
                }
            }
        }
        return ticks;

    }
}
