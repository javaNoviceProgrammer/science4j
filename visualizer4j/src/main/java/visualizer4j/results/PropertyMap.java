package visualizer4j.results;

import visualizer4j.utils.SimpleMap;

public class PropertyMap extends SimpleMap<String, String> {
	
	private static final long serialVersionUID = 1L;

	public void put(String s, double d) {
		super.put(s, d+"");
	}
	
	public void put(String s, float f) {
		super.put(s, f+"");
	}

}