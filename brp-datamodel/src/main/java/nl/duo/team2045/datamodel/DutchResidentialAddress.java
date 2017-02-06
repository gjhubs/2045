package nl.duo.team2045.datamodel;

import java.util.Optional;

import lombok.Data;

public final  @Data class DutchResidentialAddress  implements Address {
	
	/**
	 * A possible null street field.
	 * Note Some cities have neighbourhoods without names for the street, e.g. the suburb of Heilige Stoel in Wijchen./
	 */
	private String Street;
	
	/**
	 * A Dutch postal code of the form NNNNNAA, where NNNN are numbers and AA are uppercase characters A-Z.
	 */
	private String postalCode;
	
	/**
	 *  The number of the house a person is living at.
	 */
	private int houseNumber;
	
	/** Addition to the house number, e.g.: a,b,c and bis. 
	 */
	private String houseLetter;
	
	/**
	 * the denotation. Used for objects that do not have a house number of their own. 
	 * Not used that much nowadays. In the past this was used for houseboats.
	 */
	private Optional<Denotation> denotation;
	
	/**
	 * City or village, e.g. Zutphen de Hoven wich is part of municipality Zutphen.
	 * This does not need to be equal to the municipality a person is living in.
	 */
	private String city;
	
	/**
	 *  the official code of the municipality the person is living in.
	 *  The city should be part of that municipality.
	 */
	private int municipalityCode;
}
