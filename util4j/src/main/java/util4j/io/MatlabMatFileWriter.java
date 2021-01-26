package util4j.io;

import java.io.IOException;
import java.util.ArrayList;

import com.jmatio.io.MatFileWriter;
import com.jmatio.types.MLArray;


/**
 * An interface to Matlab mat file format using JMatIO package.
 * <p>
 * With this class, matrices and vectors in FuturEye can be stored as mat file format of Matlab.
 * Use command <tt>load(fileName)</tt> in matlab to load these objects into matlab workspace for further processes.
 *
 *
 */
public class MatlabMatFileWriter {
	
	protected ArrayList<MLArray> list = new ArrayList<MLArray>();

	public MatlabMatFileWriter() {
	}


	/**
	 * Write all the matrices and vectors to the *.mat file named by <tt>fineName</tt>
	 *
	 * @param fileName Matlab *.mat File name. If <tt>fileName</tt> is not ended with '.mat' a postfix '.mat' will be added.
	 */
	public void writeFile(String fileName) {
		try {
			if(!fileName.endsWith(".mat"))
				fileName = fileName + ".mat";
			new MatFileWriter(fileName, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
