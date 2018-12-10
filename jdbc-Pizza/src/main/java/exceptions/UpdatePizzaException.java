package exceptions;

public class UpdatePizzaException extends StockageException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdatePizzaException (String message) {
		super(message);
	}

}
