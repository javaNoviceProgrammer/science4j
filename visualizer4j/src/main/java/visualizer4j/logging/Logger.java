package visualizer4j.logging;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Logger {


	private static boolean loggerInit = false;
	private static boolean loggerOpe = false;

	public static final String TRACE = "trace";
	public static final String INFO  = "info";
	public static final String DEBUG = "debug";
	public static final String ERROR = "error";
	public static final String FATAL = "fatal";
	public static final String WARN  = "warn";
	public static final String NONE  = "none";

	IntLogger log;

	private static Logger logger;

	public static void initLogger(File f) {
		if (loggerInit) {
			return;
		}
		try {
			@SuppressWarnings("unused")
			Class<?> c = Class.forName("org.apache.log4j.Logger");
			if (f != null) {
				if (f.exists()) {
					System.setProperty("log4j.configuration",f.toURI().toURL().toString());
					System.out.println("Log4j configuration file explicitely given as parameter");
				} else {
					return;
				}
			} else {
				if (System.getProperty("log4j.configuration") != null) {
					System.out.println("Log4j configuration read from properties file");
				} else {
					f = new File("div/log4j.properties");
					System.out.println("Log4j configuration file set to " + f.toURI().toURL().toString());
					System.setProperty("log4j.configuration",f.toURI().toURL().toString());					
				}
			}
			System.out.println("Log4j conf is : \r\n-->   " + System.getProperty("log4j.configuration"));
			logger = new Logger(Logger.class);
			loggerOpe = true;
		}
		catch (Exception e) {
			System.out.println("Error while init logging system");
			throw new IllegalStateException(e);
		}
		finally {
			loggerInit = true;
			if (Logger.loggerOpe) {
				logger.info("LOGGING SYSTEM OPERATIONNAL");
			} else {
				System.out.println("LOGGING SYSTEM ERROR");				
			}			
		}
	}


	public Logger(Class<?> source) {
		if (loggerOpe) {
			log = new Log4jWrapper(source);
		} else {
			log = new TextWrapper(source);
		}
	}

	public String getEffectiveLevel() {
		return log.getEffectiveLevel();
	}

	public void trace(String s) {
		log.trace(s);
	}

	public void trace(String s, Throwable t) {
		log.trace(s,t);
	}

	public void trace(Element el) {
		log.trace(el);
	}
	// end trace

	// info

	public void info(String s) {
		log.info(s);
	}

	public void info(String s, Throwable t) {
		log.info(s,t);
	}
	public void info(Element el) {
		log.info(el);
	}
	// end info

	// debug

	public void debug(String s) {
		log.debug(s);
	}

	public void debug(String s, Throwable t) {
		log.debug(s,t);
	}
	public void debug(Element el) {
		log.debug(el);
	}
	// end debug

	// error

	public void error(String s, Throwable t) {
		log.error(s,t);
	}

	public void error(String s) {
		log.error(s);
	}
	public void error(Element el) {
		log.error(el);
	}
	// end error

	// fatal

	public void fatal(String s, Throwable t) {
		log.fatal(s,t);
	}

	public void fatal(String s) {
		log.fatal(s);
	}
	public void fatal(Element el) {
		log.fatal(el);
	}
	// end fatal

	// warn

	public void warn(String s, Throwable t) {
		log.warn(s,t);
	}

	public void warn(String s) {
		log.warn(s);
	}
	public void warn(Element el) {
		log.warn(el);
	}

	public boolean isDebug() {
		String s = getEffectiveLevel();
		return (s.equals(DEBUG) || s.equals(TRACE));
	}
}

interface IntLogger {

	public void trace(String s);
	public void trace(String s, Throwable t);
	public void trace(Element e);
	public void info(String s);
	public void info(Element e);
	public void info(String s, Throwable t);
	public void debug(String s);
	public void debug(Element e);
	public void debug(String s, Throwable t);
	public void error(String s, Throwable t);
	public void error(String s);
	public void error(Element e);
	public void fatal(String s, Throwable t);
	public void fatal(String s);
	public void fatal(Element e);
	public void warn(String s, Throwable t);
	public void warn(String s);
	public void warn(Element e);
	public String getEffectiveLevel();

}
class TextWrapper implements IntLogger {

	Class<?> source = null;

	public TextWrapper(Class<?> source) {
		this.source = source;
	}

	public void trace(String s) {
		//		System.out.println(s);
	}
	public void trace(String s, Throwable t) {
		//		System.out.println(s);
	}
	public void trace(Element e) {
		//		System.out.println(e.asXML());
	}
	public void info(String s) {
		System.out.println(s);
	}
	public void info(Element e) {
		System.out.println(e.asXML());
	}
	public void info(String s, Throwable t) {
		System.out.println(s);
	}
	public void debug(String s) {
		//		System.out.println(s);
	}
	public void debug(Element e) {
		//		System.out.println(e.asXML());
	}
	public void debug(String s, Throwable t) {
		//		System.out.println(s);
	}
	public void error(String s, Throwable t) {
		System.out.println(s);
	}
	public void error(String s) {
		System.out.println(s);
	}
	public void error(Element e) {
		System.out.println(e.asXML());
	}
	public void fatal(String s, Throwable t) {
		System.out.println(s);
	}
	public void fatal(String s) {
		System.out.println(s);
	}
	public void fatal(Element e) {
		System.out.println(e.asXML());
	}
	public void warn(String s, Throwable t) {
		System.out.println(s);
	}
	public void warn(String s) {
		System.out.println(s);
	}
	public void warn(Element e) {
		System.out.println(e.asXML());
	}
	public String getEffectiveLevel() {
		return "";
	}


}

class Log4jWrapper implements IntLogger {

	org.apache.log4j.Logger log4j = null;
	Class<?> source = null;


	public Log4jWrapper(Class<?> source) {
		this.source = source;
		try {
			log4j =  org.apache.log4j.Logger.getLogger(source);
		}
		catch(Throwable e) {
			System.out.println(".");
		}
	}


	private BufferedReader convertElement(Element el) {
		try {
			ByteArrayOutputStream bb = new ByteArrayOutputStream();
			XMLWriter wr = new XMLWriter(bb, new OutputFormat("  ",true));
			wr.write(el);
			BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bb.toByteArray())));
			return br;
		}
		catch(Exception e) {
			return null;
		}
	}

	public String getEffectiveLevel() {
		if (log4j != null) {
			org.apache.log4j.Level lev = log4j.getEffectiveLevel();
			if (lev.equals(org.apache.log4j.Level.TRACE)) {
				return Logger.TRACE;
			} else if (lev.equals(org.apache.log4j.Level.INFO)) {
				return Logger.INFO;
			} else if (lev.equals(org.apache.log4j.Level.DEBUG)) {
				return Logger.DEBUG;
			} else if (lev.equals(org.apache.log4j.Level.ERROR)) {
				return Logger.ERROR;
			} else if (lev.equals(org.apache.log4j.Level.FATAL)) {
				return Logger.FATAL;
			} else if (lev.equals(org.apache.log4j.Level.WARN)) {
				return Logger.WARN;
			}
		}
		return Logger.NONE;
	}

	// trace

	public void trace(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.TRACE, s, null);
	}

	public void trace(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.TRACE, r, null);
			}
		}
		catch(Exception ex) {
		}
	}

	public void trace(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.TRACE, s, t);
	}

	// end trace

	// info

	public void info(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.INFO, s, null);
	}

	public void info(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.INFO,s, t);
	}

	public void info(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.INFO, r, null);
			}
		}
		catch(Exception ex) {
		}
	}


	// end info

	// debug

	public void debug(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.DEBUG,s, null);
	}

	public void debug(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.DEBUG,s, t);
	}

	public void debug(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.DEBUG, r, null);
			}
		}
		catch(Exception ex) {
		}
	}

	// end debug

	// error

	public void error(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.ERROR,s, t);
	}

	public void error(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.ERROR,s, null);
	}
	public void error(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.ERROR, r, null);
			}
		}
		catch(Exception ex) {
		}
	}

	// end error

	// fatal

	public void fatal(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.FATAL,s, t);
	}

	public void fatal(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.FATAL,s, null);
	}
	public void fatal(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.FATAL, r, null);
			}
		}
		catch(Exception ex) {
		}
	}

	// end fatal

	// warn

	public void warn(String s, Throwable t) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.WARN, s, t);
	}

	public void warn(String s) {
		log4j.log(Logger.class.getName(), org.apache.log4j.Level.WARN,s, null);
	}
	public void warn(Element e) {
		try {
			BufferedReader br = convertElement(e);
			String r;
			while (	(r = br.readLine()) != null) {
				log4j.log(Logger.class.getName(), org.apache.log4j.Level.WARN, r, null);
			}
		}
		catch(Exception ex) {
		}
	}
	// end warn

}