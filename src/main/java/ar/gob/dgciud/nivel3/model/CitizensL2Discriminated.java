package ar.gob.dgciud.nivel3.model;

import java.util.ArrayList;
import java.util.List;

public class CitizensL2Discriminated extends CitizenModel {

	private List<CitizenL2> citizenL2Found;

	private List<CitizenL1> citizenL2NotFound;

	public CitizensL2Discriminated() {
		this.citizenL2Found = new ArrayList<CitizenL2>();
		this.citizenL2NotFound = new ArrayList<CitizenL1>();
	}

	public void addCitizenL2Found(CitizenL2 citizenL2Found) {
		this.citizenL2Found.add(citizenL2Found);
	}

	public void addCitizenL2NotFound(CitizenL1 citizenL2NotFound) {
		this.citizenL2NotFound.add(citizenL2NotFound);
	}

	public List<CitizenL2> getCitizenL2Found() {
		return citizenL2Found;
	}

	public List<CitizenL1> getCitizenL2NotFound() {
		return citizenL2NotFound;
	}

}
