package latex4j.fig;

import latex4j.base.Command;

public class Caption extends Command {

	public Caption(String name, String caption) {
		super("caption", caption) ;
		addEmptyOptions = false ;
	}

}
