package visualizer4j.charts.paints;

import java.awt.Color;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Grid extends Texture {

	private Color backGroundColor, gridColor;

	public Grid() {
		this.backGroundColor = Color.BLACK;
		this.gridColor = Color.WHITE;
	}

	@Override
	public PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
			Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
		return new GridContext(cm, deviceBounds,
				this.backGroundColor, this.gridColor);
	}

	@Override
	public int getTransparency() {
		if (this.backGroundColor.getAlpha() < 255
				|| this.gridColor.getAlpha() < 255) {
			return Transparency.TRANSLUCENT;
		}
		return Transparency.OPAQUE;
	}

	@Override
	public Color getColor() {
		return this.gridColor;
	}

	@Override
	public void setColor(Color c) {
		this.gridColor = c;
	}

	private class GridContext implements PaintContext {
		private ColorModel colorModel;
		private Rectangle containingRectangle;
		private int backGroundColor, gridColor;

		private GridContext(ColorModel colorModel,
				Rectangle containingRectangle, Color background,
				Color grid) {
			this.colorModel = colorModel;
			this.containingRectangle = containingRectangle;
			this.backGroundColor = background.getRGB();
			this.gridColor = grid.getRGB();
		}

		@Override
		public void dispose() {
		}

		@Override
		public ColorModel getColorModel() {
			return this.colorModel;
		}

		@Override
		public Raster getRaster(int x, int y, int width, int height) {
			WritableRaster raster = this.colorModel.createCompatibleWritableRaster(width, height);
			int xx = x - this.containingRectangle.x;
			int yy = y - this.containingRectangle.y;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int color = this.backGroundColor;
					if (((i + xx) % 2 == 1 &&  (j + yy) % 2 == 1) || ((i + xx) % 2 != 1 &&  (j + yy) % 2 !=1)) {
						color = this.gridColor;
					}
					Color col = new Color(color);
					int[] rgb = new int[] {col.getRed(), col.getGreen(), col.getBlue()};
					raster.setPixel(i, j, rgb);
				}
			}
			return raster;
		}
	}
}
