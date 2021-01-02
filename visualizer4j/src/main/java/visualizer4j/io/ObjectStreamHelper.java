package visualizer4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamHelper {

	public static void writeObject(Object[] o, File f) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(o);
			oos.close();
		}
		catch (Exception e) {
			throw new IllegalStateException("Problem in write object", e);
		}
	}

	public static Object[] readObject(File f) {
		Object g = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			g = ois.readObject();
			ois.close();
		}
		catch (Exception e) {
			throw new IllegalStateException("Problem in wread object", e);
		}
		return (Object[])g;
	}

}
