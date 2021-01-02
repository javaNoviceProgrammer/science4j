package test_javanco;

import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.general_libraries.results.DataPoint;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;


public class Test1 implements Experiment {
	
	double x ;
	double y ;
	
	public Test1(double x) {
		this.x = x ;
		this.y = Math.sin(x) ;
	}

	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis) throws WrongExperimentException {
		DataPoint dp = new DataPoint() ;
		dp.addProperty("X value", x); 
		dp.addResultProperty("Y value", y);
		man.addDataPoint(dp);
	}
	
	public static void main(String[] args) {
		String className = Test1.class.getName() ;
		String pkgName = Test1.class.getPackage().getName() ;
		System.out.println(pkgName);
		System.out.println(className);
		ExperimentConfigurationCockpit.execute(new String[] {"-c", className, "-p", pkgName}, true);
	}

}
