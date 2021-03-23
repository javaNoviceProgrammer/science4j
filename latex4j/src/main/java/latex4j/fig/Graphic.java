package latex4j.fig;

import java.io.File;

import latex4j.base.Command;

public class Graphic extends Command {
	
	File image ;

	public Graphic(String name, File image) {
		super("includegraphics", image.getAbsolutePath());
		this.image = image ;
	}
	
	public void setImage(File image) {
		this.image = image ;
		args.set(0, this.image.getAbsolutePath()) ;
	}

}
