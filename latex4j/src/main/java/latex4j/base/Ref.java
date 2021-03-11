package latex4j.base;

public class Ref extends Command {

	public Ref(String arg) {
		super("ref", arg);
		addEmptyOptions = false ;
	}

}
