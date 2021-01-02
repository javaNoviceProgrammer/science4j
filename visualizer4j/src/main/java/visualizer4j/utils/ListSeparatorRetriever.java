
package visualizer4j.utils;

//import org.snipecode.reg.RegUtil;


public class ListSeparatorRetriever {

	public static String retrieve() {
		/*
		try {
			// Open a Handle
			int[] ret = RegUtil.RegOpenKey(RegUtil.HKEY_CURRENT_USER, "Control Panel\\International", RegUtil.KEY_QUERY_VALUE);
			handle = ret[RegUtil.NATIVE_HANDLE];
			// get the Number of Values in the key
			int[] info = RegUtil.RegQueryInfoKey(handle);
			int count = info[RegUtil.VALUES_NUMBER];
			int maxlen = info[RegUtil.MAX_VALUE_NAME_LENGTH];
			for(int index =0 ;index< count;index++) {
				byte[] name = RegUtil.RegEnumValue(handle,index, maxlen+1);
				String nameS = new String(name).trim();
				if (nameS.equals("sList")) {
					byte[] values = RegUtil.RegQueryValueEx(handle, name);
					if(null!=values)
						 return new String(values).trim();
				}
			}
		}
		finally {
			RegUtil.RegCloseKey(handle);
		}
		 */
		return ",";
	}
}

