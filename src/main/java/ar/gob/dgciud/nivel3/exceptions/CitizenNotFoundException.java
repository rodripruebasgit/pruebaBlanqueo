package ar.gob.dgciud.nivel3.exceptions;

public class CitizenNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CitizenNotFoundException(String dni) {
		super("No se encontro Citizen con DNI " + dni);
	}
}
