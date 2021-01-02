package visualizer4j.results;

import java.io.File;

public interface SerialisableResultManager {

	public abstract void saveToFile(File f);
	public abstract void loadFromFile(File f);
}
