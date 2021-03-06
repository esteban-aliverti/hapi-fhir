
package ca.uhn.fhir.model.dev.valueset;

import ca.uhn.fhir.model.api.*;
import java.util.HashMap;
import java.util.Map;

public enum ProcedureRelationshipTypeEnum {

	/**
	 * Code Value: <b>caused-by</b>
	 *
	 * This procedure had to be performed because of the related one.
	 */
	CAUSED_BY("caused-by", "http://hl7.org/fhir/procedure-relationship-type"),
	
	/**
	 * Code Value: <b>because-of</b>
	 *
	 * This procedure caused the related one to be performed.
	 */
	BECAUSE_OF("because-of", "http://hl7.org/fhir/procedure-relationship-type"),
	
	;
	
	/**
	 * Identifier for this Value Set:
	 * http://hl7.org/fhir/vs/procedure-relationship-type
	 */
	public static final String VALUESET_IDENTIFIER = "http://hl7.org/fhir/vs/procedure-relationship-type";

	/**
	 * Name for this Value Set:
	 * ProcedureRelationshipType
	 */
	public static final String VALUESET_NAME = "ProcedureRelationshipType";

	private static Map<String, ProcedureRelationshipTypeEnum> CODE_TO_ENUM = new HashMap<String, ProcedureRelationshipTypeEnum>();
	private static Map<String, Map<String, ProcedureRelationshipTypeEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, ProcedureRelationshipTypeEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (ProcedureRelationshipTypeEnum next : ProcedureRelationshipTypeEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, ProcedureRelationshipTypeEnum>());
			}
			SYSTEM_TO_CODE_TO_ENUM.get(next.getSystem()).put(next.getCode(), next);			
		}
	}
	
	/**
	 * Returns the code associated with this enumerated value
	 */
	public String getCode() {
		return myCode;
	}
	
	/**
	 * Returns the code system associated with this enumerated value
	 */
	public String getSystem() {
		return mySystem;
	}
	
	/**
	 * Returns the enumerated value associated with this code
	 */
	public ProcedureRelationshipTypeEnum forCode(String theCode) {
		ProcedureRelationshipTypeEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<ProcedureRelationshipTypeEnum> VALUESET_BINDER = new IValueSetEnumBinder<ProcedureRelationshipTypeEnum>() {
		@Override
		public String toCodeString(ProcedureRelationshipTypeEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(ProcedureRelationshipTypeEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public ProcedureRelationshipTypeEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public ProcedureRelationshipTypeEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, ProcedureRelationshipTypeEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	ProcedureRelationshipTypeEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
