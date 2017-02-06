package nl.duo.team2045.datamodel;

/**
 * The suname to use in correspondence used when someone is married or has a registered partner.
 * @author Hans Kruse
 *
 */
public enum CodeNameUse {
	/**
	 * Only use a person's own surname.
	 */
	OwnName,
	/**
	 * Use the surname of a person's spouse.
	 */
	SpouseName,
	/**
	 * Use a person's own name followed by a person's spouse's surname.
	 */
	OwnNameSpouseName,
	/**
	 * Use a person's spouse's surname followed by the person's own surname.
	 */
	SpouseNameOwnName
}
