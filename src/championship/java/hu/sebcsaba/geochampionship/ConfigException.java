package hu.sebcsaba.geochampionship;

public class ConfigException extends Exception {

	private static final long serialVersionUID = 1583612433732800876L;

	public ConfigException() {
		super();
	}

	public ConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigException(String message) {
		super(message);
	}

	public ConfigException(Throwable cause) {
		super(cause);
	}

}
