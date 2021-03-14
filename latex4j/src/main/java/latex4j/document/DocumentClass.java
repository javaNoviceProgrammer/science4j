package latex4j.document;

import latex4j.base.Command;

public class DocumentClass extends Command {

	public DocumentClass(String name) {
		super("documentclass", name); // name: name of class file --> name.cls
	}

}
