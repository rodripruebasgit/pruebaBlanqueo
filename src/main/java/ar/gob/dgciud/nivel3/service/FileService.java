package ar.gob.dgciud.nivel3.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.dgciud.nivel3.config.FileParams;
import ar.gob.dgciud.nivel3.model.CitizenL1;
import ar.gob.dgciud.nivel3.model.CitizenL2;
import ar.gob.dgciud.nivel3.model.CitizenLicencia;
import ar.gob.dgciud.nivel3.utils.UtilsFile;

@Service
public class FileService {

	@Autowired
	FileParams fp;

	private String fileOutputNotFoundLogin1;
	private String fileOutputNotFoundLogin2;
	private String foundLogin2;

	private Integer countLevelFiles = 1;
	private Integer countLevelLines = 1;

	private Integer countAuditFiles = 1;
	private Integer countAuditLines = 1;

	@PostConstruct
	private void init() {
		this.fileOutputNotFoundLogin1 = fp.getFolderOutput() + "NotFoundLogin1.csv";
		this.fileOutputNotFoundLogin2 = fp.getFolderOutput() + "NotFoundLogin2.csv";
		this.foundLogin2 = fp.getFolderOutput() + "foundLogin2.csv";
	}

	/** Carga los Citizen del archivo CSV de licencias */
	public List<CitizenLicencia> loadLicenseCitizens() {
		System.out.println("Cargando Citizen de archivo " + fp.getFileInput());

		List<CitizenLicencia> cl = new ArrayList<CitizenLicencia>();
		try {
			List<String> csvLines = UtilsFile.readCsv(fp.getFileInput());

			for (String csvLine : csvLines) {
				cl.add(new CitizenLicencia(csvLine));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Citizens cargados del CSV :" + cl.size());
		return cl;
	}

	public void writeInFileSetLevelOK(CitizenL2 citizen) {
		try {

			UtilsFile.writeLine(foundLogin2, citizen.toCsvLine());

		} catch (IOException e) {
			System.err.println("writeInFileSetLevelOK");
			e.printStackTrace();
		}

	}

	/** Escribe en archivo CSV */
	public void writeInFileNotFoundL1(List<CitizenLicencia> citizenL1NotFound) {
		try {
			for (CitizenLicencia citizenLicencia : citizenL1NotFound) {
				UtilsFile.writeLine(fileOutputNotFoundLogin1, citizenLicencia.toCsvLine());
			}

		} catch (IOException e) {
			System.err.println("writeInFileNotFoundL1");
			e.printStackTrace();
		}
	}

	public void writeInFileNotFoundL2(List<CitizenL1> citizenL2NotFound) {
		try {
			for (CitizenL1 citizenL1 : citizenL2NotFound) {
				UtilsFile.writeLine(fileOutputNotFoundLogin2, citizenL1.toCsvLine());
			}
		} catch (IOException e) {
			System.err.println("writeInFileNotFoundL2");
			e.printStackTrace();
		}

	}

	public void writeInInsertL2(String line) {
		try {
			if ((countLevelLines % fp.getMaxPerFile()) == (0)) {
				++countLevelFiles;
			}

			UtilsFile.writeLine(fp.getFolderOutput() + "CITIZEN_LEVELS_" + countLevelFiles + ".sql", line);
			++countLevelLines;
		} catch (IOException e) {
			System.err.println("writeInInsertL2");
			e.printStackTrace();
		}
	}

	public void writeInAudit(String line) {
		try {
			if ((countAuditLines % fp.getMaxPerFile()) == (0)) {
				++countAuditFiles;
			}

			UtilsFile.writeLine(fp.getFolderOutput() + "ONSITE_VALIDATION_" + countAuditFiles + ".sql", line);
			++countAuditLines;
		} catch (IOException e) {
			System.err.println("writeInInsertL2");
			e.printStackTrace();
		}
	}

}
