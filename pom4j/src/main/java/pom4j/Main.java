package pom4j;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() ->{
			new JarInstaller().setVisible(true);
		});
	}

}
