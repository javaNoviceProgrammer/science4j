package visualizer4j.results;

import javax.swing.JFrame;

public abstract class AbstractResultsDisplayer {

	protected AdvancedDataRetriever retriever;
	protected AbstractInOutDataManager manager;
	public static boolean showGUI = true ;

	public AbstractResultsDisplayer(AdvancedDataRetriever retriever) {
		this.retriever = retriever;
		try {
			if (retriever instanceof AbstractInOutDataManager) {
				manager = (AbstractInOutDataManager)retriever;
			}
		}
		catch (Exception e) {}
	}

	public AbstractResultsDisplayer(AbstractInOutDataManager manager) {
		this.manager = manager;
		this.retriever = manager;
	}

	public abstract JFrame showFrame();
	public abstract void refresh();

	public AdvancedDataRetriever getDataRetriever() {
		return retriever;
	}

	public AbstractInOutDataManager getDataManager() {
		return manager;
	}
}
