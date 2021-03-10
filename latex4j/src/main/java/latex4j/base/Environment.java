package latex4j.base;

import java.util.ArrayList;
import java.util.List;

public class Environment {
	
	/*
	 * \begin{name}[options]
	 * 
	 * \end{name}
	 * 
	 * Examples: document, equation, eqnarray, figure, table, ...
	 * 
	 */
	
	String name ;
	List<String> options ;
	List<String> text ;
	
	public Environment(String name) {
		this.name = name ;
		options = new ArrayList<>() ;
		text = new ArrayList<>() ;

	}
	
	public Environment addOption(String option) {
		options.add(option) ;
		return this ;
	}
	
	// low-level API
	public Environment addText(String text) {
		this.text.add(text) ;
		return this ;
	}
	
	@Override
	public String toString() {
		// initialize environment
		StringBuilder environment = new StringBuilder() ;
		// step 1: begin
		environment.append("\\begin{"+name+"}") ;
		// step 2: add options
		if(!options.isEmpty()) {
			environment.append("[" + String.join(", ", options) + "]") ;
		}
		environment.append("\n") ;
		// step 3: add text
		if(!text.isEmpty()) {
			for(String s : text) {
				environment.append(s) ;
			}
		}
		environment.append("\n") ;
		// step 4: end
		environment.append("\\end{"+name+"}\n") ;
		return environment.toString() ;
	}
	
	
	
	

}
