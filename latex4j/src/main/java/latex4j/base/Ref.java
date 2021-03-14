package latex4j.base;


public class Ref extends Command {

	// Ref is an inline command: ... \ref{lable} ...
	
	public Ref(String arg) {
		super("ref", arg);
		addEmptyOptions = false ;
		addNewLine = false ;
	}

}
