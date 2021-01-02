package visualizer4j.experiments.builder;

import java.io.File;

import visualizer4j.clazzes.ClassRepository;
import ch.epfl.general_libraries.experiment_aut.Experiment;
import visualizer4j.utils.DateAndTimeFormatter;
import ch.epfl.javancox.experiments.builder.tree_model.AbstractChooseNode;
import ch.epfl.javancox.experiments.builder.tree_model.ObjectConstuctionTreeModel;
import ch.epfl.javancox.experiments.builder.tree_model.ObjectConstuctionTreeModel.ObjectIterator;
import ch.epfl.javancox.experiments.builder.tree_model.ObjectConstuctionTreeModel.TreeModelUIManager;
import ch.epfl.javancox.results_manager.SmartDataPointCollector;

public class ConsoleBasedBuilder {

	public static void main(String[] args) throws Exception {
		
		String[] prefixes = new String[]{"ch.epfl", "test", "org.optsquare", "umontreal","pscan", "edu.columbia"};
		String fileName = null;	
		String outputName = null;
		
		if (args.length > 0) { 
			if (args[0].equals("-help") || args[0].equals("help") || args[0].equals("-h") || args[0].equals("usage")) {
				printUsage();
			}
			for (int i = 0 ; i < args.length ; i++) {
				if (args[i].equals("-f")) {
					fileName = args[i+1];
				}
				if (args[i].equals("-p")) {
					prefixes = args[i+1].split(";");
				}
				if (args[i].equals("-o")) {
					outputName = args[i+1];
				}				
			}
		}

		
		final ClassRepository lister = new ClassRepository(prefixes);
		ObjectConstuctionTreeModel<Experiment> tree = ObjectConstuctionTreeModel.loadFromFile(lister, Experiment.class, fileName);
		

		
		tree.setTreeModelUIManager(new TreeModelUIManager() {

			@Override
			public void showErrorMessage(String string) {
				System.out.println(string);
			}

			@Override
			public void removeNode(AbstractChooseNode node) {
			}

			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				
			}
		});
		
		ObjectIterator<Experiment> iterator = tree.getObjectIterator();
		
		SmartDataPointCollector collector = new SmartDataPointCollector();
		
		int i = 0;
		
		while (iterator.hasNext()) {
			Experiment exp = iterator.next();
			System.out.println("Experiment " + i);
			exp.run(collector, null);
			i++;
		}
		if (outputName == null) outputName = DateAndTimeFormatter.getDateAndTime(System.currentTimeMillis()) + "_.coc";
		collector.saveToFile(new File(outputName));
		
	}

	private static void printUsage() {
		System.out.println("Usage : [-l <config file to run>][-p <prefixes, ;-separated>][-o <output file name>]");
		System.exit(0);
	}
	
	
}
