package latex4j.base;

import java.util.ArrayList;
import java.util.List;

public class Command {
	
	// structure of a Latex command: \name[options]{arg1}{arg2}...{argN}
	
	String name ;
	List<String> options ;
	List<String> args ;
	StringBuilder command ;
	
	protected boolean addEmptyOptions = true ;
	
	public Command(String name, String... args) {
		this.name = name ;
		this.options = new ArrayList<>() ;
		this.args = new ArrayList<>() ;
		for(String arg : args) {
			this.args.add(arg) ;
		}
	}
	
	public Command addOption(String option) {
		options.add(option) ;
		return this ;
	}
	
	public void options(String... options) {
		for(String option : options) {
			this.options.add(option) ;
		}
	}

	@Override
	public String toString() {
		// construct the latex command
		// escape sequence: \t
		if(command==null) {
			command = new StringBuilder() ;
			// step 1: add command name
			command.append("\\"+name) ;
			// add options
			if(name.equals("begin") || name.equals("end"))
				command.append("") ;
			else if(options.isEmpty()) {
				if(addEmptyOptions)
					command.append("[]") ;
			}
			else {
				// [a, b, c] --> "[a, b, c]"
				command.append("[" + String.join(", ", options) + "]") ;
			}
			// add arguments
			for(String arg : args)
				command.append("{" + arg + "}") ; 
			// add a new line
			command.append("\n") ;
		}
		return command.toString() ;
	}
	
}
