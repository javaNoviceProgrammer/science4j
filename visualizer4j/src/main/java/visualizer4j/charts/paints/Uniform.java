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

public class Uniform extends Texture {

	private Color color;

	public Uniform() {
		this.color = Color.WHITE;
	}

	@Override
	public PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
			Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
		return new UniformContext(cm, this.color);
	}

	@Override
	public int getTransparency() {
		if (this.color.getAlpha() < 255) {
			return Transparency.TRANSLUCENT;
		}
		return Transparency.OPAQUE;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	private class UniformContext implements PaintContext {
		private ColorModel colorModel;
		private int color;

		private UniformContext(ColorModel colorModel, Color color) {
			this.colorModel = colorModel;
			this.color = color.getRGB();
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
			Color col = new Color(color);
			int[]  rgb = new int[raster.getNumBands()];
			rgb[0] = col.getRed();
			rgb[1] = col.getGreen();
			rgb[2] = col.getBlue();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					try {
						raster.setPixel(i, j, rgb);
					}
					catch (ArrayIndexOutOfBoundsException e) {
						System.out.print(".");
					}
				}
			}
			return raster;
		}
	}

}
