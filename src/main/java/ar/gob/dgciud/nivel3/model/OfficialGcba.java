package ar.gob.dgciud.nivel3.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OfficialGcba extends CitizenModel {

	@Value("${gcba.id}")
	private String id;

	@Value("${gcba.cuilGcba}")
	private String cuilGcba;

	@Value("${gcba.siteId}")
	private Integer siteId;

	@Value("${gcba.reasonId}")
	private Integer reasonId;

	@Value("${gcba.firstName}")
	private String firstName;

	@Value("${gcba.lastName}")
	private String lastName;

	@Value("${gcba.emailGcba}")
	private String emailGcba;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCuilGcba() {
		return cuilGcba;
	}

	public void setCuilGcba(String cuilGcba) {
		this.cuilGcba = cuilGcba;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailGcba() {
		return emailGcba;
	}

	public void setEmailGcba(String emailGcba) {
		this.emailGcba = emailGcba;
	}

}
