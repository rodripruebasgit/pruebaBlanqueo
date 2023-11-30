package ar.gob.dgciud.nivel3.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.gob.dgciud.nivel3.model.CitizenL2;
import ar.gob.dgciud.nivel3.model.CitizenLicencia;
import ar.gob.dgciud.nivel3.model.CitizensL1Discriminated;
import ar.gob.dgciud.nivel3.model.CitizensL2Discriminated;

@org.springframework.stereotype.Service
public class MainService {

	@Autowired
	private Login1Service l1s;

	@Autowired
	private Login2Service l2s;

	@Autowired
	FileService fs;

	public void runScript() {
		List<CitizenLicencia> licenseCitizens = fs.loadLicenseCitizens();

		if (wouldConitue()) {
			process(licenseCitizens);
		}
	}

	private void process(List<CitizenLicencia> licenseCitizens) {
		CitizensL1Discriminated discriminateByCitizensFoundInL1 = l1s.discriminateByCitizensFoundInL1(licenseCitizens);

		fs.writeInFileNotFoundL1(discriminateByCitizensFoundInL1.getCitizenL1NotFound());

		CitizensL2Discriminated discriminateByCitizensFoundInL2 = l2s.discriminateByCitizensFoundInL2(discriminateByCitizensFoundInL1.getCitizenL1Found());

		fs.writeInFileNotFoundL2(discriminateByCitizensFoundInL2.getCitizenL2NotFound());

		// toLevel3AndAudit(discriminateByCitizensFoundInL2.getCitizenL2Found());
		l2s.writeInFileInsertLevel3(discriminateByCitizensFoundInL2.getCitizenL2Found());
		l2s.writeInFileInsertAuditLevel3(discriminateByCitizensFoundInL2.getCitizenL2Found());
	}

	private void toLevel3AndAudit(List<CitizenL2> citizenL2Found) {
		for (CitizenL2 citizen : citizenL2Found) {
			if (l2s.setToLevel3(citizen)) {
				auditOnSiteValidation(citizen);
				fs.writeInFileSetLevelOK(citizen);
			}

		}
	}

	private void auditOnSiteValidation(CitizenL2 citizen) {
		l2s.auditOnSiteValidation(citizen);
	}

	private Boolean wouldConitue() {
		System.out.println("continua ? si/no");
		BufferedReader response = new BufferedReader(new InputStreamReader(System.in));
		try {
			return response.readLine().equalsIgnoreCase("si");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
