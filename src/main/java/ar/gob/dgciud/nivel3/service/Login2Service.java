package ar.gob.dgciud.nivel3.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.dgciud.nivel3.exceptions.CitizenNotFoundException;
import ar.gob.dgciud.nivel3.exceptions.MultipleCitizenFound;
import ar.gob.dgciud.nivel3.model.CitizenL1;
import ar.gob.dgciud.nivel3.model.CitizenL2;
import ar.gob.dgciud.nivel3.model.CitizensL2Discriminated;
import ar.gob.dgciud.nivel3.model.OfficialGcba;
import ar.gob.dgciud.nivel3.repo.RepoL2;

@Service
public class Login2Service {

	private static final int EXITO = 1;

	@Autowired
	private RepoL2 repoL2;

	@Autowired
	OfficialGcba official;

	@Autowired
	FileService fs;

	/*** buscarlos en L2 por BAID , Filtrar los que no existen */
	public CitizensL2Discriminated discriminateByCitizensFoundInL2(List<CitizenL1> citizenL1Found) {
		System.out.println("Buscando Citizens en Login2");
		CitizensL2Discriminated citizens = new CitizensL2Discriminated();

		for (CitizenL1 citizenL1 : citizenL1Found) {
			try {
				CitizenL2 citizenL2 = repoL2.finCitizenByBAID(citizenL1.getIdentifier());
				citizens.addCitizenL2Found(citizenL2);

			} catch (CitizenNotFoundException | MultipleCitizenFound e) {
				System.err.println(e.getMessage());

				citizens.addCitizenL2NotFound(citizenL1);
			}
		}
		System.out.println("FIN Buscando Citizens en Login2");
		// System.out.println(citizens);
		System.out.println("   Encontrados en L2 " + citizens.getCitizenL2Found().size());
		System.out.println("NO Encontrados en L2 " + citizens.getCitizenL2NotFound().size());
		return citizens;
	}

	public void writeInFileInsertLevel3(List<CitizenL2> citizenL2Found) {

		for (CitizenL2 citizen : citizenL2Found) {
			long citizenID = citizen.getCitizenId();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			String insertSql = "INSERT INTO MIBALOGIN2.CITIZEN_LEVELS (LEVELS_ID, CITIZEN_ID, LEVEL_TYPE_ID, INSERTED_ON, DELETED_ON)"
					+ " VALUES(SEQ_LEVELS_ID.NEXTVAL, " + citizenID + ", 3, TIMESTAMP '" + now + "', NULL);";

			fs.writeInInsertL2(insertSql);
		}

	}

	public void writeInFileInsertAuditLevel3(List<CitizenL2> citizenL2Found) {
		for (CitizenL2 citizen : citizenL2Found) {

			String insertSql = "INSERT INTO MIBALOGIN2.ONSITE_VALIDATION"
					+ " (ONSITE_VALIDATION_ID, BAID, CUIL_GCBA, \"TIMESTAMP\", ANSWER, ANSWER_MESSAGE, SITE_ID, REASON_ID, DETAIL, CITIZEN_ID, USER_ID_GCBA, FIRST_NAME, LAST_NAME, EMAIL_GCBA)"
					+ " VALUES(SEQ_ONSITEVALIDATION_ID.NEXTVAL, '" + citizen.getBaid() + "', " + official.getCuilGcba() + ", TIMESTAMP' "
					+ new Timestamp(System.currentTimeMillis()) + "', " + EXITO + ", NULL," + "" + official.getSiteId() + ", " + official.getReasonId()
					+ ", '', " + citizen.getCitizenId() + ", '" + official.getId() + "', '" + official.getFirstName() + "', '" + official.getLastName() + "', '"
					+ official.getEmailGcba() + "');";

			fs.writeInAudit(insertSql);

		}

	}

	/** Si el script tiene permisos puede ejecutar la query directamente */
	public Boolean setToLevel3(CitizenL2 citizen) {
		return repoL2.setToLevel3(citizen);
	}

	/** Si el script tiene permisos puede ejecutar la query directamente */
	public void auditOnSiteValidation(CitizenL2 citizen) {
		repoL2.auditOnSiteValidation(citizen, official);
	}

}
