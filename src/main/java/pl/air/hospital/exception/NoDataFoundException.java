package pl.air.hospital.exception;

public class NoDataFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -9039578329044674186L;

	public NoDataFoundException(String message) {
		super(message);
	}
	
}
