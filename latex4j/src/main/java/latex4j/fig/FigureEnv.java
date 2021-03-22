package latex4j.fig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import latex4j.base.Command;
import latex4j.base.Environment;

public class FigureEnv extends Environment {
	
	/*
	 * \begin{figure}[options]
	 * 		\includegraphics[options]{filename1}
	 * 		\includegraphics[options]{filename2}
	 * 	\caption{text}
	 * \end{figure}
	 * 
	 * useful options:
	 * 		figure --> t b h !
	 * 		include --> width=2cm, height=1cm, scale=0.5, 2.0, ...
	 * 
	 */
	
	List<Command> includes ;
	Command caption ;
	boolean centering ;
	
	public FigureEnv() {
		super("figure") ;
		includes = new ArrayList<>() ;
	}
	
	public Command includeGraphics(String filename) {
		Command cmd = new Command("includegraphics", new File(filename).getAbsolutePath()) ;
		includes.add(cmd) ;
		return cmd ;
	}
	
	public Command caption(String text) {
		caption = new Command("caption", text) ;
		return caption ;
	}
	
	public void centering(boolean flag) {
		centering = flag ;
	}

	@Override
	public String toString() {
		if(!includes.isEmpty()) {
			if(centering) {
				this.addText("\\centering\n") ;
			}
			for(Command cmd : includes) {
				this.add("\t" + cmd) ;
			}
		}
		if(caption!=null) {
			this.add(caption) ;
		}
		return super.toString();
	}
	
}
