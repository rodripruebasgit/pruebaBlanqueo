package ar.gob.dgciud.nivel3.model;

public class CitizenL1 extends CitizenModel {
	private static final String COMA = ",";

	private String identifier;
	private String cuil;

	private String first_name;
	private String last_name;
	private String gender;
	private String birthday;
	private String document_number;
	private String document_type_id;
	private String document_nationality_id;
	private String validated_with_PUC;
	private String duplicated_registrations;
	private String renaper_validation_timestamp;
	private String validated_with_renaper;

	public String toCsvLine() {
		String line = "";
		line += this.getIdentifier() + COMA;
		line += this.getCuil() + COMA;
		line += this.getFirst_name() + COMA;
		line += this.getLast_name() + COMA;
		line += this.getGender() + COMA;
		line += this.getBirthday() + COMA;
		line += this.getDocument_type_id() + COMA;
		line += this.getDocument_nationality_id();

		return line;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public String getDocument_type_id() {
		return document_type_id;
	}

	public void setDocument_type_id(String document_type_id) {
		this.document_type_id = document_type_id;
	}

	public String getDocument_nationality_id() {
		return document_nationality_id;
	}

	public void setDocument_nationality_id(String document_nationality_id) {
		this.document_nationality_id = document_nationality_id;
	}

	public String getValidated_with_PUC() {
		return validated_with_PUC;
	}

	public void setValidated_with_PUC(String validated_with_PUC) {
		this.validated_with_PUC = validated_with_PUC;
	}

	public String getDuplicated_registrations() {
		return duplicated_registrations;
	}

	public void setDuplicated_registrations(String duplicated_registrations) {
		this.duplicated_registrations = duplicated_registrations;
	}

	public String getRenaper_validation_timestamp() {
		return renaper_validation_timestamp;
	}

	public void setRenaper_validation_timestamp(String renaper_validation_timestamp) {
		this.renaper_validation_timestamp = renaper_validation_timestamp;
	}

	public String getValidated_with_renaper() {
		return validated_with_renaper;
	}

	public void setValidated_with_renaper(String validated_with_renaper) {
		this.validated_with_renaper = validated_with_renaper;
	}
}
