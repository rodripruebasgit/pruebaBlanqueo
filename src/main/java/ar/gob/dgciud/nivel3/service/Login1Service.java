package ar.gob.dgciud.nivel3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.dgciud.nivel3.exceptions.CitizenNotFoundException;
import ar.gob.dgciud.nivel3.exceptions.MultipleCitizenFound;
import ar.gob.dgciud.nivel3.model.CitizenL1;
import ar.gob.dgciud.nivel3.model.CitizenLicencia;
import ar.gob.dgciud.nivel3.model.CitizensL1Discriminated;
import ar.gob.dgciud.nivel3.repo.RepoL1;

@Service
public class Login1Service {

	@Autowired
	private RepoL1 repoL1;

	/*** buscarlos en L1 por DNI y TIPO , Filtrar los que no existen */
	public CitizensL1Discriminated discriminateByCitizensFoundInL1(List<CitizenLicencia> licenseCitizens) {
		System.out.println("Buscando Citizens en Login1");
		CitizensL1Discriminated citizens = new CitizensL1Discriminated();

		for (CitizenLicencia citizenLicencia : licenseCitizens) {
			CitizenL1 citizen;
			try {
				citizen = repoL1.findCitizen(citizenLicencia.getDocumento(), citizenLicencia.getTipo_doc());
				citizens.addCitizenL1Found(citizen);
			} catch (CitizenNotFoundException | MultipleCitizenFound e) {
				System.err.println(e.getMessage());

				citizens.addCitizenL1NotFound(citizenLicencia);
			}
		}
		System.out.println("FIN Buscando Citizens en Login1");
		// System.out.println(citizens);
		System.out.println("   Encontrados en L1 " + citizens.getCitizenL1Found().size());
		System.out.println("NO Encontrados en L1 " + citizens.getCitizenL1NotFound().size());
		return citizens;
	}

}
