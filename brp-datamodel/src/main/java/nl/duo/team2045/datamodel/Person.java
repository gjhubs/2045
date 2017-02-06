package nl.duo.team2045.datamodel;

import java.util.List;

import lombok.Data;

/**
 * Models a person roughly based on Dutch BRP (former GBA-V).
 * @author Hans Kruse
 *
 */
public final @Data class Person {

	/** Prefix for surName out of the offical list of prefixes from BRP (former GBA-V).
	 * 
	 */
	private List<String> prefix;
	/**
	 * Surname without prefixes.
	 */
	private String surName;
	/**
	 * Given names.
	 */
	private List<String> givenNames;
	
	/** How to address a person who is married or has a legal partner contract
	 * 
	 */
	private CodeNameUse codeNameUse;
	
	/**
	 * Whether a person is alive.
	 * When True a person is alive, false otherwise.
	 */
	private boolean open;
	
	/**
	 * The Date of birth in the form yyyymmdd. 
	 * This form was choosen such that it can be used easily in both Java, JSON and Javascript and it makes sorting easy.
	 * String was choosen to prevent removing prefix zeros.
	 */
	private String dateOfBirth;
	
	/**
	 * The Date when a person died in the form yyyymmdd.
	 * This form was choosen such that it can be used easily in both Java, JSON and Javascript and it makes sorting easy.
	 * String was choosen to prevent removing prefix zeros.
	 */
	private String dateOfClosing;
	
	/**
	 * The place a person was born.
	 */
	private String placeOfBirth;
	
	/**
	 * The Person uniquely identifying number for governmental use (BSN). T
	 * This should conform to the 11 proof for BSN.
	 * This is a String by design. In most cases it should be threated as opaque and NOT as a number.
	 */
	private String BSN;
	
	/**
	 * A Person's gender.
	 */
	private Gender gender;
	/* TODO: should we allow multiple spouses and turn the below in a list */
	
	/**
	 * A person's spouse
	 */
	private Person spouse;
	
	/*TODO: Not sure about the lists below.
	 * Perhaps use one List of tuples with <RelationType,Person>
	 */
	
	/**
	 * A person's Childeren. Not sure about whether this should be biological or legal children.
	 */
	private List<Person> children;
	
	/**
	 * A Person's parents. 
	 * TODO: what kind of parents?
	 */
	private List<Person> parents;
	
	/**
	 * A Person's mentors a appointed by a judge. Usually there is only one.
	 */
	private List<Person> mentors;
	
	/**
	 * A Person's bewindvoerders a appointed by a judge. Usually there is only one. 
	 * TODO: find English term.
	 */
	private List<Person> bewindvoerders;
	
	/**
	 * A Person's curators a appointed by a judge. Usually there is only one. 
	 * TODO: find English term.
	 */
	private List<Person> curators;
	
	/**
	 * A Person's voogden a appointed by a judge. Usually there is only one. 
	 * TODO: find English term. Not sure whether this is a separate field.
	 */
	private List<Person> voogden;
	
	/**
	 * The residence address. The place a person is registered to live at in the Basis Registratie Personen(BRP). 
	 */
	private Address residenceAddress;
	
	/**
	 * A person's postaladress(brief adres). For example used for people who are in jail.
	 */
	private Address postalAdress;
}
