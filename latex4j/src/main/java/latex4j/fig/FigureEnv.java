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
	 * \end{figure}
	 * 
	 * useful options:
	 * 		figure --> t b h !
	 * 		include --> width=2cm, height=1cm, scale=0.5, 2.0, ...
	 * 
	 */
	
	List<Command> includes ;
	
	public FigureEnv() {
		super("figure") ;
		includes = new ArrayList<>() ;
	}
	
	public Command includeGraphics(String filename) {
//		Command cmd = new Command("includegraphics", filename) ;
		// one solution
		Command cmd = new Command("includegraphics", new File(filename).getAbsolutePath()) ;
		includes.add(cmd) ;
		return cmd ;
	}

	@Override
	public String toString() {
		if(!includes.isEmpty()) {
			for(Command cmd : includes) {
				this.add("\t" + cmd) ;
			}
		}
		return super.toString();
	}
	
}
