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

public class VerticalScratches extends Texture {
	private Color backGroudColor, scratchesColor;

	public VerticalScratches() {
		this.backGroudColor = Color.BLACK;
		this.scratchesColor = Color.WHITE;
	}

	@Override
	public PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
			Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
		return new VerticalStrachesContext(cm, deviceBounds,
				this.backGroudColor, this.scratchesColor);
	}

	@Override
	public int getTransparency() {
		if (this.backGroudColor.getAlpha() < 255
				|| this.scratchesColor.getAlpha() < 255) {
			return Transparency.TRANSLUCENT;
		}
		return Transparency.OPAQUE;
	}

	@Override
	public Color getColor() {
		return this.scratchesColor;
	}

	@Override
	public void setColor(Color c) {
		this.scratchesColor = c;
	}

	private class VerticalStrachesContext implements PaintContext {
		private ColorModel colorModel;
		private Rectangle containingRectangle;
		private int backGroundColor, scratchesColor;

		private VerticalStrachesContext(ColorModel colorModel,
				Rectangle containingRectangle, Color background,
				Color scratches) {
			this.colorModel = colorModel;
			this.containingRectangle = containingRectangle;
			this.backGroundColor = background.getRGB();
			this.scratchesColor = scratches.getRGB();
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
			WritableRaster raster = this.colorModel
			.createCompatibleWritableRaster(width, height);
			int xx = x - this.containingRectangle.x;
			for (int i = 0; i < width; i++) {
				int color = this.backGroundColor;
				if ((i + xx) % 2 == 1) {
					color = this.scratchesColor;
				}
				for (int j = 0; j < height; j++) {
					Color col = new Color(color);
					int[] rgb = new int[] {col.getRed(), col.getGreen(), col.getBlue()};
					raster.setPixel(i, j, rgb);
				}
			}
			return raster;
		}
	}
}