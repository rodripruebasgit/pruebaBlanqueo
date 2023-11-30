package ar.gob.dgciud.nivel3.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ar.gob.dgciud.nivel3.exceptions.CitizenNotFoundException;
import ar.gob.dgciud.nivel3.exceptions.MultipleCitizenFound;
import ar.gob.dgciud.nivel3.model.CitizenL1;

@Repository
public class RepoL1 {

	@Autowired
	@Qualifier("jdbcTemplateLogin1")
	private JdbcTemplate repoL1;

	public CitizenL1 findCitizen(String documentNumber, String documentType) throws CitizenNotFoundException, MultipleCitizenFound {
		List<CitizenL1> citizens = this.findCitizens(documentNumber, documentType);

		if (citizens == null || citizens.isEmpty())
			throw new CitizenNotFoundException(documentNumber);

		if (citizens.size() > 1)
			throw new MultipleCitizenFound(documentNumber);

		return citizens.get(0);

	}

	public List<CitizenL1> findCitizens(String documentNumber, String documentType) {
		String sql = "SELECT * FROM public.baid_citizen WHERE document_number = '" + documentNumber + "'";

		List<CitizenL1> citizens = repoL1.query(sql, BeanPropertyRowMapper.newInstance(CitizenL1.class));

		return citizens;
	}
}
