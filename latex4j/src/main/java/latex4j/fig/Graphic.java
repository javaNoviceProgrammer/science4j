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
	
	public Graphic viewport(String xll, String yll, String xur, String yur) {
		addOption(format("viewport=%s %s %s %s", xll, yll, xur, yur)) ;
		return this ;
	}
	
	public Graphic clip(boolean flag) {
		addOption(format("clip=%s", flag)) ;
		return this ;
	}
	
	public Graphic trim(String left, String bottom, String right, String top) {
		addOption(format("trim=%s %s %s %s", left, bottom, right, top)) ;
		return this ;
	}
	
	public Graphic page(int pageNumber) {
		addOption(format("page=%d", pageNumber)) ;
		return this ;
	}
	
	public enum PDFBox {
		mediabox, cropbox, bleedbox, trimbox, artbox
	}
	
	public Graphic pagebox(PDFBox box) {
		addOption(format("pagebox=%s", box)) ;
		return this ;
	}
	
	public Graphic interpolate(boolean flag) {
		addOption(format("interpolate=%s", flag)) ;
		return this ;
	}
	
	public Graphic quiet(boolean flag) {
		addOption(format("quiet=%s", flag)) ;
		return this ;
	}
	
	public Graphic draft(boolean flag) {
		addOption(format("draft=%s", flag)) ;
		return this ;
	}
	
	public Graphic boundingBox(String xll, String yll, String xur, String yur) {
		addOption(format("bb=%s %s %s %s", xll, yll, xur, yur)) ;
		return this ;
	}
	
	public Graphic hiResBb(boolean flag) {
		addOption(format("hiresbb=%s", flag)) ;
		return this ;
	}

}
