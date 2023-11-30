package ar.gob.dgciud.nivel3.model;

import java.util.ArrayList;
import java.util.List;

public class CitizensL1Discriminated extends CitizenModel {

	private List<CitizenL1> citizenL1Found;

	private List<CitizenLicencia> citizenL1NotFound;

	public CitizensL1Discriminated() {
		this.citizenL1Found = new ArrayList<CitizenL1>();
		this.citizenL1NotFound = new ArrayList<CitizenLicencia>();
	}

	public void addCitizenL1Found(CitizenL1 citizenL1Found) {
		this.citizenL1Found.add(citizenL1Found);
	}

	public void addCitizenL1NotFound(CitizenLicencia citizenL1NotFound) {
		this.citizenL1NotFound.add(citizenL1NotFound);
	}

	public List<CitizenL1> getCitizenL1Found() {
		return citizenL1Found;
	}

	public List<CitizenLicencia> getCitizenL1NotFound() {
		return citizenL1NotFound;
	}

}
