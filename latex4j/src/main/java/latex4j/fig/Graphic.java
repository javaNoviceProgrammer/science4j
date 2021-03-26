package latex4j.fig;

import static java.lang.String.format ;

import java.io.File;
import java.io.IOException;

import latex4j.base.Command;

public class Graphic extends Command {
	
	File image ;
	boolean isBoxed ;

	public Graphic(File image) throws IOException {
		super("includegraphics", image.getCanonicalPath());
		this.image = image ;
	}
	
	public void setImage(File image) {
		this.image = image ;
		args.set(0, this.image.getAbsolutePath()) ;
	}
	
	public Graphic boxed(boolean flag) {
		isBoxed = flag ;
		return this ;
	}
	
	@Override
	public String toString() {
		// if boxed: \boxed{this}
		if(isBoxed) {
			this.addNewLine = false ;
			String s = super.toString() ;
			return new Command("boxed", s).addEmptyOptions(false).toString() ;
		}
		else
			return super.toString() ;
	}
	
	//************ options ***************//

	public Graphic width(String text) { // "1.2cm"
		addOption(format("width=%s", text)) ;
		return this ;
	}
	
	public Graphic height(String text) { // "1.2cm"
		addOption(format("height=%s", text)) ;
		return this ;
	}
	
	public Graphic totalHeight(String text) { // "1.2cm"
		addOption(format("totalheight=%s", text)) ;
		return this ;
	}
	
	public Graphic keepAspectRatio(boolean flag) {
		addOption(format("keepaspectratio=%s", flag)) ;
		return this ;
	}
	
	public Graphic scale(double factor) {
		addOption(format("scale=%.2f", factor)) ;
		return this ;
	}
	
	public Graphic angle(double degree) {
		addOption(format("angle=%.2f", degree)) ;
		return this ;
	}
	
	public Graphic origin(String origin) { // l for left, r for right, b for bottom, c for center, t for top, and B for baseline.
		addOption(format("origin=%s", origin)) ;
		return this ;
	}
	
	public enum Origin {
		l,
		r,
		b,
		c,
		t,
		B,
		lB ;
	}
	
	public Graphic origin(Origin origin) {
		addOption(format("origin=%s", origin)) ;
		return this ;
	}

}
