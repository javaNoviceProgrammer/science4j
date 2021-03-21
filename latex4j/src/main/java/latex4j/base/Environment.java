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
	protected List<String> text ;
	Label label ; // \label{label_name}
	Ref ref ; // \ref{label_name}
	
	protected boolean isFloating = false ;
	
	public Environment(String name) {
		this.name = name ;
		options = new ArrayList<>() ;
		text = new ArrayList<>() ;
	
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Environment> T floating(boolean flag) {
		this.isFloating = flag ;
		return (T) this ;
	}
	
	public boolean isFloating() {
		return isFloating ;
	}
 	
	public Environment addOption(String option) {
		options.add(option) ;
		return this ;
	}
	
	// low-level API
	@SuppressWarnings("unchecked")
	public <T extends Environment> T addText(String text) {
		this.text.add(text) ;
		return (T) this ;
	}
	
	// low-level API
	@SuppressWarnings("unchecked")
	public <T extends Environment> T addText(Object text) {
		this.text.add(text.toString()) ;
		return (T) this ;
	}
	
	public Environment add(Object text) {
		this.text.add(text.toString()) ;
		return this ;
	}
	
	public Ref setLabel(String label) {
		this.label = new Label(label) ;
		this.ref = new Ref(label) ;
		return this.ref ;
	}
	
	@Override
	public String toString() {
		// initialize environment
		StringBuilder environment = new StringBuilder() ;
		// step 1: begin
		if(isFloating)
			environment.append("\\begin{"+name+"*}") ;
		else
			environment.append("\\begin{"+name+"}") ;
		// step 2: add options
		if(!options.isEmpty()) {
			environment.append("[" + String.join(", ", options) + "]") ;
		}
		environment.append("\n") ;
//		// check for label
//		if(label!=null) {
//			environment.append(label) ;
//		}
		// step 3: add text
		if(!text.isEmpty()) {
			for(String s : text) {
				environment.append(s) ;
			}
		}
//		environment.append("\n") ;
		// check for label
		if(label!=null) {
			environment.append(label) ;
		}
		// step 4: end
		if(isFloating)
			environment.append("\\end{"+name+"*}\n") ;
		else
			environment.append("\\end{"+name+"}\n") ;
		return environment.toString() ;
	}
	
}
