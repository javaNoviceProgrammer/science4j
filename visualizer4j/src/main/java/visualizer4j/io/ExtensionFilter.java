package visualizer4j.io;

import java.util.Arrays;

/**
 * An implementation of <code>FileFilter</code> which can be set on a <code>JFileChooser</code>
 * to display only the folders and the files with a given extension (e.g. "xml").<br>
 * An instance of <code>ExtensionFilter</code> can accept more than one extension, but it
 * is represented to the user as a single filter.
 * @author lchatela
 */

public class ExtensionFilter extends javax.swing.filechooser.FileFilter {


	private String[] extensions = null;
	private String description = "(No filter)";

	/**
	 * Creates a new <code>ExtensionFilter</code>
	 * @param extensions The extensions the filter accept (e.g. "xml", "jpg")
	 * @param descriptionName The filter description to display to the user
	 */
	public ExtensionFilter(String[] extensions, String descriptionName) {
		if (extensions != null && extensions.length != 0) {
			this.extensions = extensions;
			setDescription(descriptionName);
		}
	}

	@Override
	public boolean accept(java.io.File f){
		if ( (f.isDirectory()) || (extensions == null)) {
			return true;
		}
		String filename = f.getName().toLowerCase();
		String fileExtension = filename.substring(filename.lastIndexOf('.')+1);
		return Arrays.asList(extensions).contains(fileExtension);
	}

	@Override
	public String getDescription(){
		return description;
	}

	/**
	 * Creates the description of this filter which will be displayed to the user.<br/>
	 * The description is composed of two parts:
	 * <ul>
	 * <li> the name (the specified <code>descriptionName</code> if any, or a default name
	 * 		if only one extension is accepted (e.g. "XML file"))
	 * <li> the list of accepted extensions (e.g. "(*.xml, *.xsd)" )
	 * </ul>
	 * @param descriptionName the name of the description or <code>null</code>
	 */
	private void setDescription(String descriptionName) {
		// name of the filter (if any)
		StringBuffer descriptionBuf = new StringBuffer();
		if (descriptionName != null) {
			descriptionBuf.append(descriptionName + " ");
		} else if (extensions.length == 1 ) {
			descriptionBuf.append(extensions[0].toUpperCase() + " file ");
		}
		// list of extensions allowed
		descriptionBuf.append("(");
		for (int i=0 ; i<extensions.length-1 ; i++) {
			descriptionBuf.append("*." + extensions[i] + ", ");
		}
		descriptionBuf.append("*." + extensions[extensions.length-1]);
		descriptionBuf.append(")");
		this.description = descriptionBuf.toString();
	}
}
