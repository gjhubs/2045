package nl.duo.team2045.datamodel;

import lombok.Data;

/**
 * A postal address using a pobox at the postoffice.
 * @author Hans Kruse
 *
 */
public final @Data class PostalAdres {
	
	/**
	 * A Dutch postal code of the form NNNNNAA, where NNNN are numbers and AA are upercase charactgers A-Z.
	 */
	private String PostalCode;
	
	/**
	 * the postal box number.
	 */
	private int poBox;

	/**
	 * City or village, e.g. Zutphen de Hoven wich is part of municipality Zutphen.
	 * This does not need to be equal to the municipality a person is living in.
	 */
	private String City;
	
	/**
	 *  the official code of the municipality the person is living in.
	 *  The city should be part of that municipality.
	 */
	private int municipalityCode;
	
}
