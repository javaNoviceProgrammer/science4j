package visualizer4j.charts;


public class Problem {
	public enum Severity {
		INFORMATION,
		WARNING,
		ERROR
	}
	final private Severity severity;
	final private String message;
	final private String method;

	public Problem(Severity severity, String message, String method) {
		this.severity = severity;
		this.message = message;
		this.method = method;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Problem) {
			Problem p = (Problem) o;
			return p.severity == this.severity && p.message.equals(this.message) && p.method.equals(this.method);
		} else {
			return false;
		}
	}
}
