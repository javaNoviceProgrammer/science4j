package latex4j.base;

public class Label extends Command {

	public Label(String label) {
		super("label", label);
		addEmptyOptions = false ;
	}

}
