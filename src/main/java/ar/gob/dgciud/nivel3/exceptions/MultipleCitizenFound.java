package ar.gob.dgciud.nivel3.exceptions;

public class MultipleCitizenFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleCitizenFound(String dni) {
		super("Se encontro mas de un Citizen con DNI" + dni);
	}
}
