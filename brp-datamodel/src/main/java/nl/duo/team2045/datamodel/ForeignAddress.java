package nl.duo.team2045.datamodel;

import lombok.Data;

public final @Data class ForeignAddress implements Address {
	/** 
	 * Country.
	 */
	String country;
	
	/**
	 * Address line 1.
	 */
	String address1;
	/**
	 * Address line 1.
	 */
	String address2;
	/**
	 * Address line 1.
	 */
	String address3;
}
