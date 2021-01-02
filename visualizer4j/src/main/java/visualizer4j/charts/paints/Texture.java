package visualizer4j.charts.paints;

import java.awt.Color;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

public abstract class Texture implements Paint {

	@Override
	public abstract int getTransparency();

	@Override
	public abstract PaintContext createContext(ColorModel cm, Rectangle deviceBounds,
			Rectangle2D userBounds, AffineTransform xform, RenderingHints hints);


	public abstract Color getColor();
	public abstract void setColor(Color c);
}
