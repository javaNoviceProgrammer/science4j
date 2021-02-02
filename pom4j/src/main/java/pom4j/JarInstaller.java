/*
 * Created by JFormDesigner on Tue Feb 02 06:13:43 EST 2021
 */

package pom4j;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;

/**
 * @author Meisam Bahadori
 */
public class JarInstaller extends JFrame {

	private static final long serialVersionUID = -9080997086482141473L;
	
	public JarInstaller() {
		initComponents();
	}

	private void installButtonPressed(ActionEvent e) {
		// get jar file
		String jarfile = textField1.getText() ;
		// get maven info
		String groupID = textField2.getText() ;
		String artifactID = textField3.getText() ;
		String version = textField4.getText() ;
		// maven command
		String command = String.format("mvn install:install-file -Dfile=%s -DgroupId=%s "
				+ "-DartifactId=%s -Dversion=%s -Dpackaging=jar",
				jarfile, groupID, artifactID, version) ;
		// execuate maven
		try {
			Process p = Runtime.getRuntime().exec(command) ;
			Scanner out = new Scanner(p.getInputStream()) ;
			while(out.hasNextLine()) {
				textArea1.append(out.nextLine()+"\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			StackTraceElement[] trace = e1.getStackTrace() ;
			for(StackTraceElement s : trace) {
				textArea1.append(s.toString()+"\n");
			}
		}
		
	}

	private void quitMenuItemPressed(ActionEvent e) {
		System.exit(0);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner non-commercial license
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menu2 = new JMenu();
		menu3 = new JMenu();
		label1 = new JLabel();
		textField1 = new JTextField();
		button1 = new JButton();
		label2 = new JLabel();
		textField2 = new JTextField();
		label3 = new JLabel();
		textField3 = new JTextField();
		label4 = new JLabel();
		textField4 = new JTextField();
		button2 = new JButton();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();

		//======== this ========
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Maven Jar Installer");
		setMinimumSize(new Dimension(800, 700));
		setLocationByPlatform(true);
		var contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[fill]" +
			"[grow,fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[grow,fill]"));

		//======== menuBar1 ========
		{

			//======== menu1 ========
			{
				menu1.setText("File");

				//---- menuItem1 ----
				menuItem1.setText("Open Jar...");
				menu1.add(menuItem1);

				//---- menuItem2 ----
				menuItem2.setText("Quit");
				menuItem2.addActionListener(e -> quitMenuItemPressed(e));
				menu1.add(menuItem2);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText("Log");
			}
			menuBar1.add(menu2);

			//======== menu3 ========
			{
				menu3.setText("Settings");
			}
			menuBar1.add(menu3);
		}
		setJMenuBar(menuBar1);

		//---- label1 ----
		label1.setText("Jar File: ");
		contentPane.add(label1, "cell 0 0");
		contentPane.add(textField1, "cell 1 0");

		//---- button1 ----
		button1.setText("Browse...");
		contentPane.add(button1, "cell 2 0");

		//---- label2 ----
		label2.setText("Group ID: ");
		contentPane.add(label2, "cell 0 1");
		contentPane.add(textField2, "cell 1 1");

		//---- label3 ----
		label3.setText("Artifact ID: ");
		contentPane.add(label3, "cell 0 2");
		contentPane.add(textField3, "cell 1 2");

		//---- label4 ----
		label4.setText("Version: ");
		contentPane.add(label4, "cell 0 3");
		contentPane.add(textField4, "cell 1 3");

		//---- button2 ----
		button2.setText("Install");
		button2.addActionListener(e -> installButtonPressed(e));
		contentPane.add(button2, "cell 2 3");

		//======== scrollPane1 ========
		{
			scrollPane1.setBorder(new TitledBorder(null, "Log", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.blue));
			scrollPane1.setName("Log");

			//---- textArea1 ----
			textArea1.setEditable(false);
			scrollPane1.setViewportView(textArea1);
		}
		contentPane.add(scrollPane1, "cell 0 4 3 1");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner non-commercial license
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenu menu2;
	private JMenu menu3;
	private JLabel label1;
	private JTextField textField1;
	private JButton button1;
	private JLabel label2;
	private JTextField textField2;
	private JLabel label3;
	private JTextField textField3;
	private JLabel label4;
	private JTextField textField4;
	private JButton button2;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
