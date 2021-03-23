package latex4j.fig;

import java.io.File;
import java.io.IOException;

import latex4j.base.Command;

public class Graphic extends Command {
	
	File image ;

	public Graphic(File image) throws IOException {
		super("includegraphics", image.getCanonicalPath());
		this.image = image ;
	}
	
	public void setImage(File image) {
		this.image = image ;
		args.set(0, this.image.getAbsolutePath()) ;
	}

}
