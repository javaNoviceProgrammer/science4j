package visualizer4j.experiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import visualizer4j.clazzes.ParamName;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import visualizer4j.results.AbstractResultsDisplayer;
import visualizer4j.results.AbstractResultsManager;
import visualizer4j.results.DataPoint;
import visualizer4j.results.Execution;


public class DataReader implements Experiment {
	
	private String folder;
	private String prefix;
	private String name;
	private String extension;
	private String sep;
	
	public DataReader(
			@ParamName(name="Folder")  String folder,
			@ParamName(name="Prefix")  String prefix,
			@ParamName(name="name")  String name,			
			@ParamName(name="suffix")  String extension,
			@ParamName(name="column separator") String sep
			) {
		this.folder = folder;
		this.prefix = prefix;
		this.name = name;
		this.extension = extension;
		this.sep = sep;
	}

	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis)
			throws WrongExperimentException {
		String path = buildPath();		
		Execution exec = new Execution();
		
		DataPoint dp = new DataPoint();
		dp.addProperty("Path", path);
		dp.addProperty("filename", name);
		
		BufferedReader br = null;
		
		try {

			FileReader fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String[] headers = parseHeaders(br.readLine());
			if (headers.length == 0) {
				throw new IllegalStateException("No headers it looks in " + path);
			}
			String line = "";
			while ((line = br.readLine()) != null) {
				parseLine(line, exec, headers, dp.getDerivedDataPoint());
			}
			
		}
		catch (IOException e) {
			throw new WrongExperimentException(e);
		}
		finally {
			try {
				if (br != null) 
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		man.addExecution(exec);
	}

	private String[] parseHeaders(String readLine) {
		return readLine.split(sep);
	}

	private void parseLine(String line, Execution exec, String[] headers, DataPoint dp) {
		String[] elementsStrings = line.split(sep);
		if (elementsStrings.length != headers.length) {
			System.out.println("mismatch");
 		} else {
			for (int i = 0 ; i < headers.length ; i++) {
				dp.addResultProperty(headers[i], elementsStrings[i]);
			}
			exec.addDataPoint(dp);
 		}
	}

	private String buildPath() {
		String path = "";
		if (!folder.endsWith(File.separator)) {
			path = folder + File.separator;
		} else {
			path = folder;
		}
		return path + prefix + name + extension;
	}

}
