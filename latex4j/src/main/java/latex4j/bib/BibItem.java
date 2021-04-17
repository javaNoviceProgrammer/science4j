package latex4j.bib;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BibItem { // later --> convert it to abstract class
	
	String type ; // book, article, ... => later --> change to enum
	String keyword ;
	Map<String, String> fields ;
	
	public BibItem(String type, String keyword) {
		this.type = type ;
		this.keyword = keyword ;
//		fields = new HashMap<>() ;
		fields = new LinkedHashMap<>() ; // preserves the order of items
	}
	
	// very low-level API
	public BibItem addField(String fieldName, String fieldValue) { // later --> change to protected method
		fields.put(fieldName, fieldValue); ;
		return this ;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		// create the bib content
		// step 1: @type{keyword, }
		sb.append("@%s{%s,\n".formatted(type, keyword)) ;
		// step 2: add fields --> field={value},
//		for(String item : fields.keySet()) {
//			sb.append("\t%s={%s},\n".formatted(item, fields.get(item))) ;
//			}
		List<String> allFields = new ArrayList<>() ;
		for(String s : fields.keySet()) {
			allFields.add("\t%s={%s}".formatted(s, fields.get(s))) ;
		}
		sb.append(String.join(",\n", allFields)) ;
		// end
		sb.append("\n}\n") ;
		return sb.toString() ;
	}
	
	

}
