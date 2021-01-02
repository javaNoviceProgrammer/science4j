package visualizer4j.results;

import java.io.Serializable;
import java.util.Map.Entry;

public class Property implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String name;
	private final String value;
	private final Float floatValue;
	private final String unit;

	public Property(Entry<String, String> ent) {
		this.name = ent.getKey();
		this.value = ent.getValue();
		this.floatValue = parse(value);
		this.unit = "";
	}

	public Property(String name, String value) {
		this.name = name;
		this.value = value;
		this.floatValue = parse(value);
		this.unit = "";
	}

	public Property(String name, double value) {
		this.name = name;
		this.value = value+"";
		this.floatValue = (float)value;
		this.unit = "";
	}

	public Property(String name, String value, String unit) {
		this.name = name;
		this.value = value;
		this.floatValue = parse(value);
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
	
	public Float getFloatValue() {
		return floatValue;
	}

	public String getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return name + "=" + value + " " + unit;
	}

	public boolean isString() {
		return floatValue == null;
	}
	
	public Float parse(String s) {
		try {
			return Float.parseFloat(s);
		}
		catch (Exception e) {
			return null;
		}
	}	
}
