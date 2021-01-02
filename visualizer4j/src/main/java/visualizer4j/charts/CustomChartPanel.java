package visualizer4j.charts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.block.Block;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.CenterArrangement;
import org.jfree.chart.block.LabelBlock;
import org.jfree.chart.block.LineBorder;
import org.jfree.chart.title.LegendGraphic;
import org.jfree.chart.title.LegendItemBlockContainer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;

import visualizer4j.utils.Pair;
import visualizer4j.utils.PairList;


public class CustomChartPanel extends ChartPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomChartPanel(JFreeChart chart,
            int width,
            int height,
            int minimumDrawWidth,
            int minimumDrawHeight,
            int maximumDrawWidth,
            int maximumDrawHeight,
            boolean useBuffer,
            boolean properties,
            boolean save,
            boolean print,
            boolean zoom,
            boolean tooltips) {	
    	super(chart,width,height, minimumDrawWidth, minimumDrawHeight, maximumDrawWidth, maximumDrawHeight,
              useBuffer, properties, save, print, zoom, tooltips);
    }
    
    /**
     * Opens a file chooser and gives the user an opportunity to save the chart
     * in PNG format.
     *
     * @throws IOException if there is an I/O error.
     */
    public void doSaveAs() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
   //     ExtensionFileFilter filter = new ExtensionFileFilter(
   //           localizationResources.getString("EMF_Image_Files"), ".emf");
    //    fileChooser.addChoosableFileFilter(filter);

        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getPath();
           // String filenamEMF = filename;
            String filenamPNG = filename;
        /*    if (!filename.endsWith(".emf")) {
                filenamEMF = filename + ".emf";
            }*/
            if (!filename.endsWith(".png")) {
                filenamPNG = filename + ".png";
            }           
            int width = 600;
            int height = 500;
            int scale = 1;
         /*   Dimension dim = new Dimension(width, height);
            EMFGraphics2D emfG2 = new EMFGraphics2D(new File(filenamEMF), dim);  
            emfG2.writeHeader();
            emfG2.create();
            Rectangle2D rect2d = new Rectangle2D.Double(0, 0, width, height);
            getChart().draw(emfG2, rect2d);
            emfG2.closeStream();*/
            
            BufferedImage image = new BufferedImage(width*scale, height*scale, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            g2.scale(scale, scale);
            getChart().draw(g2, new Rectangle2D.Double(0, 0, width, height), null, null);
            g2.dispose();
            OutputStream out = new BufferedOutputStream(new FileOutputStream( new File(filenamPNG)));
            ImageIO.write(image, "png", out);
            
            
            LegendTitle legend = new LegendTitle(getChart().getPlot());
            legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
            legend.setFrame(new LineBorder());
            legend.setBackgroundPaint(Color.white);
            legend.setPosition(RectangleEdge.BOTTOM);
            
            LegendItemCollection legendItems = getChart().getPlot().getLegendItems();
            int maxWidth = 0;
            double yAccum = 0;
            PairList<LegendGraphic, LabelBlock> legItems = new PairList<LegendGraphic, LabelBlock>(legendItems.getItemCount());
            if (legendItems != null) {
                for (int i = 0; i < legendItems.getItemCount(); i++) {
                    LegendItem item = legendItems.get(i);
                    BlockContainer block = (BlockContainer)createLegendItemBlock(item, legend);
                    
                    BlockContainer legendItem = (BlockContainer)block.getBlocks().get(0);
                    
                    LegendGraphic shapeAndLine = (LegendGraphic)legendItem.getBlocks().get(0);
                    LabelBlock label = (LabelBlock)legendItem.getBlocks().get(1);
                    
                    legItems.add(shapeAndLine, label);
                    
                    Size2D sizeShape = shapeAndLine.arrange(null);
                    Size2D sizeLabel = label.arrange(g2);
                    
                    int x = (int)Math.ceil(sizeShape.width + sizeLabel.width);
                    double y = Math.max(sizeShape.height, sizeLabel.height);
                    yAccum += y;
                    if (x > maxWidth) maxWidth = x;
                }
            }
 
            BufferedImage legendIm = new BufferedImage(maxWidth, (int)yAccum, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = legendIm.createGraphics();
            g.setBackground(Color.WHITE);
            g.setPaint(Color.WHITE);
            g.fillRect(0, 0, maxWidth, (int)yAccum);
            
            yAccum = 0;
            
            for (Pair<LegendGraphic, LabelBlock> pair : legItems) {
                LegendGraphic shapeAndLine = pair.getFirst();
                LabelBlock label = pair.getSecond();
                
                Size2D sizeShape = shapeAndLine.arrange(null);
                Size2D sizeLabel = label.arrange(g2);
                
                double y = Math.max(sizeShape.height, sizeLabel.height);                
                shapeAndLine.draw(g, new Rectangle2D.Double(0, yAccum, sizeShape.width, y));
                label.draw(g, new Rectangle2D.Double(sizeShape.width, yAccum, sizeLabel.width, y));
                
                yAccum += y;
                
            }
            g.dispose();
            OutputStream out2 = new BufferedOutputStream(new FileOutputStream(new File(filenamPNG + "leg.png")));
            ImageIO.write(legendIm, "png", out2);  
            out2.close();
            
        }
    }  
    
    /**
     * Creates a legend item block.
     * 
     * @param item  the legend item.
     * 
     * @return The block.
     */
    protected Block createLegendItemBlock(LegendItem item, LegendTitle title) {
        BlockContainer result = null;
        LegendGraphic lg = new LegendGraphic(item.getShape(), 
                item.getFillPaint());
        lg.setFillPaintTransformer(item.getFillPaintTransformer());
        lg.setShapeFilled(item.isShapeFilled());
        lg.setLine(item.getLine());
        lg.setLineStroke(item.getLineStroke());
        lg.setLinePaint(item.getLinePaint());
        lg.setLineVisible(item.isLineVisible());
        lg.setShapeVisible(item.isShapeVisible());
        lg.setShapeOutlineVisible(item.isShapeOutlineVisible());
        lg.setOutlinePaint(item.getOutlinePaint());
        lg.setOutlineStroke(item.getOutlineStroke());
        lg.setPadding(title.getLegendItemGraphicPadding());

        LegendItemBlockContainer legendItem = new LegendItemBlockContainer(
                new BorderArrangement(), item.getDataset(), 
                item.getSeriesKey());
        lg.setShapeAnchor(title.getLegendItemGraphicAnchor());
        lg.setShapeLocation(title.getLegendItemGraphicLocation());
        legendItem.add(lg, title.getLegendItemGraphicEdge());
        LabelBlock labelBlock = new LabelBlock(item.getLabel(), title.getItemFont(), 
                title.getItemPaint());
        labelBlock.setPadding(title.getItemLabelPadding());
        legendItem.add(labelBlock);
        legendItem.setToolTipText(item.getToolTipText());
        legendItem.setURLText(item.getURLText());
        
        result = new BlockContainer(new CenterArrangement());
        result.add(legendItem);
        
        return result;
    }
	
}
