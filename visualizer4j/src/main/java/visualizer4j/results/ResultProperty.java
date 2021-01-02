package visualizer4j.results;

import java.io.Serializable;




public class ResultProperty extends Property implements Serializable {
	private static final long serialVersionUID = 1L;

	public ResultProperty(String name, String value) {
		super(name, value);
	}

	public ResultProperty(String name, double value) {
		super(name, value+"");
	}

	public ResultProperty(String name, String value, String unit) {
		super(name, value, unit);
	}

	public ResultProperty(String name, double value, String unit) {
		super(name,value+"", unit);
	}

	public ResultProperty(String name, boolean b) {
		super(name, b+"");	
	}
}
