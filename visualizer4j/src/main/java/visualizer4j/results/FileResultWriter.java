package visualizer4j.results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class FileResultWriter extends ArrayList<Execution> implements AbstractResultsManager {

	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String separator;
	private int index;
	
	public FileResultWriter(String fileName, String separator) {
		this.fileName = fileName;
		this.separator = separator;
	}

	@Override
	public void addExecution(Execution e) {
		try {
			FileWriter fw = new FileWriter(fileName + "" + index + ".txt");
			HashSet<String> inProperties = new HashSet<String>();
			HashSet<String> outProperties = new HashSet<String>();
			
			for (DataPoint dp : e.getDataPoints()) {
				inProperties.addAll(dp.getInputPropertiesNames());
				outProperties.addAll(dp.getOutputPropertiesNames());
			}
			
			for (String in : inProperties) {
				fw.write(in + separator);
			}
			fw.write("######" + separator);
			for (String out : outProperties) {
				fw.write(out + separator);
			}
			fw.write("\r\n");
			
			for (DataPoint dp : e.getDataPoints()) {
				for (String in : inProperties) {
					Property p = dp.getProperty(in);
					if (p != null) {
						fw.write(p.getValue());
					}
					fw.write(separator);
				}
				fw.write("######" + separator);				
				for (String out : outProperties) {
					Property p = dp.getProperty(out);
					if (p != null) {
						fw.write(p.getValue());
					}
					fw.write(separator);
				}
				fw.write("\r\n");
			}
			fw.flush();
			fw.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public float confidenceInterval(CriteriumSet cs, String metric) {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public void terminate() {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public void addDataPoint(DataPoint dp) {
		Execution e = new Execution();
		e.addDataPoint(dp);
		this.addExecution(e);
	}
}
