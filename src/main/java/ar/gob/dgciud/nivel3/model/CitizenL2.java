package ar.gob.dgciud.nivel3.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CITIZEN database table.
 * 
 */
@Entity
@Table(name = "CITIZEN")
public class CitizenL2 extends CitizenModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String COMA = ",";
	@Id
	@Column(name = "CITIZEN_ID")
	private Long citizenId;

	private String baid;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private Long dni;

	@Column(name = "DOC_TYPE_ID")
	private Integer docTypeId;

	@Column(name = "DOCUMENT_NATIONALITY_ID")
	private Integer documentNationalityId;

	@Column(name = "DOCUMENT_NUMBER")
	private String documentNumber;

	@Column(name = "FACIAL_VALIDATION_DATE")
	private Timestamp facialValidationDate;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "GENDER_ID")
	private Integer genderId;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "NATIONALITY_ID")
	private Integer nationalityId;

	@Column(name = "RENAPER_VALIDATION_DATE")
	private Timestamp renaperValidationDate;

	@Column(name = "VALIDATED_FACIAL")
	private Integer validatedFacial;

	@Column(name = "VALIDATED_RENAPER")
	private Integer validatedRenaper;

	@Column(name = "LEVEL_AGIP")
	private Integer levelAgip;

	@Column(name = "DELETED_ON")
	private Timestamp deletedOn;
	@Column(name = "DELETE_REASON")
	private Long deleteReason;

	public CitizenL2() {
	}

	public String toCsvLine() {
		String line = "";
		line += this.getCitizenId() + COMA;
		line += this.getBaid() + COMA;
		line += this.getBirthdate() + COMA;
		line += this.getDni() + COMA;
		line += this.getDocTypeId() + COMA;
		line += this.getDocumentNationalityId() + COMA;
		line += this.getDocumentNumber() + COMA;
		line += this.getFirstName() + COMA;
		line += this.getLastName();

		return line;
	}

	public long getCitizenId() {
		return this.citizenId;
	}

	public void setCitizenId(long citizenId) {
		this.citizenId = citizenId;
	}

	public String getBaid() {
		return this.baid;
	}

	public void setBaid(String baid) {
		this.baid = baid;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Long getDni() {
		return this.dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Integer getDocTypeId() {
		return this.docTypeId;
	}

	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}

	public Integer getDocumentNationalityId() {
		return this.documentNationalityId;
	}

	public void setDocumentNationalityId(Integer documentNationalityId) {
		this.documentNationalityId = documentNationalityId;
	}

	public String getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Timestamp getFacialValidationDate() {
		return this.facialValidationDate;
	}

	public void setFacialValidationDate(Timestamp facialValidationDate) {
		this.facialValidationDate = facialValidationDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getGenderId() {
		return this.genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getNationalityId() {
		return this.nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public Timestamp getRenaperValidationDate() {
		return this.renaperValidationDate;
	}

	public void setRenaperValidationDate(Timestamp renaperValidationDate) {
		this.renaperValidationDate = renaperValidationDate;
	}

	public Integer getValidatedFacial() {
		return this.validatedFacial;
	}

	public void setValidatedFacial(Integer validatedFacial) {
		this.validatedFacial = validatedFacial;
	}

	public Integer getValidatedRenaper() {
		return this.validatedRenaper;
	}

	public void setValidatedRenaper(Integer validatedRenaper) {
		this.validatedRenaper = validatedRenaper;
	}

	public Timestamp getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Timestamp deletedOn) {
		this.deletedOn = deletedOn;
	}

	public Long getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(Long deleteReason) {
		this.deleteReason = deleteReason;
	}

}