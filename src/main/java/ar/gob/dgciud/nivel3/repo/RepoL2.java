package ar.gob.dgciud.nivel3.repo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.gob.dgciud.nivel3.exceptions.CitizenNotFoundException;
import ar.gob.dgciud.nivel3.exceptions.MultipleCitizenFound;
import ar.gob.dgciud.nivel3.model.CitizenL2;
import ar.gob.dgciud.nivel3.model.OfficialGcba;

@Repository
public class RepoL2 {

	private static final int EXITO = 1;
	private static final String ONSITE_VALIDATION = "ONSITE_VALIDATION";
	private static final String MIBALOGIN2 = "MIBALOGIN2";

	@Autowired
	@Qualifier("jdbcTemplateLogin2")
	private JdbcTemplate repoL2;

	public List<CitizenL2> finCitizensByBAID(final String baid) {

		String sql = "SELECT * FROM CITIZEN WHERE BAID = '" + baid + "'";

		return repoL2.query(sql, BeanPropertyRowMapper.newInstance(CitizenL2.class));

	}

	public CitizenL2 finCitizenByBAID(final String baid) throws CitizenNotFoundException, MultipleCitizenFound {

		List<CitizenL2> citizens = this.finCitizensByBAID(baid);

		if (citizens == null || citizens.isEmpty())
			throw new CitizenNotFoundException(baid);

		if (citizens.size() > 1)
			throw new MultipleCitizenFound(baid);

		return citizens.get(0);

	}

	/** Si el script tiene permisos puede ejecutar la query directamente */
	public Boolean setToLevel3(CitizenL2 citizen) {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(this.repoL2).withSchemaName(MIBALOGIN2).withTableName("CITIZEN_LEVELS");
			// .usingGeneratedKeyColumns("LEVELS_ID");
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("LEVELS_ID", nextValLevelID());
			// parameters.put("LEVELS_ID", "SEQ_LEVELS_ID.nextval");
			parameters.put("CITIZEN_ID", citizen.getCitizenId());
			parameters.put("LEVEL_TYPE_ID", 3);
			// parameters.put("INSERTED_ON", "TIMESTAMP '" + timestamp + "'");
			parameters.put("INSERTED_ON", new Timestamp(System.currentTimeMillis()));
			parameters.put("DELETED_ON", null);
			insert.execute(parameters);

			return true;
		} catch (Exception e) {
			System.err.println("Error al subir de nivel " + citizen);

			e.printStackTrace();
			return false;
		}

	}

	/** Si el script tiene permisos puede ejecutar la query directamente */
	public void auditOnSiteValidation(CitizenL2 citizen, OfficialGcba official) {
		try {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(this.repoL2).withSchemaName(MIBALOGIN2).withTableName(ONSITE_VALIDATION);

			Map<String, Object> parameters = builAuditdParams(citizen, official);

			insert.execute(parameters);
		} catch (Exception e) {
			System.err.println("error al auditar " + citizen);
			e.printStackTrace();
		}
	}

	private Map<String, Object> builAuditdParams(CitizenL2 citizen, OfficialGcba official) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ONSITE_VALIDATION_ID", nextValOnsiteValidation());
		parameters.put("BAID", citizen.getBaid());
		parameters.put("CUIL_GCBA", official.getCuilGcba());
		parameters.put("TIMESTAMP", new Timestamp(System.currentTimeMillis()));
		parameters.put("ANSWER", EXITO);
		parameters.put("ANSWER_MESSAGE", " ");
		parameters.put("SITE_ID", official.getSiteId());
		parameters.put("REASON_ID", official.getReasonId());
		parameters.put("DETAIL", "");
		parameters.put("CITIZEN_ID", citizen.getCitizenId());
		parameters.put("USER_ID_GCBA", official.getId());
		parameters.put("FIRST_NAME", official.getFirstName());
		parameters.put("LAST_NAME", official.getLastName());
		parameters.put("EMAIL_GCBA", official.getEmailGcba());
		return parameters;
	}

	private Integer nextValLevelID() {
		String sql = "SELECT SEQ_LEVELS_ID.nextval as ID FROM dual";
		Integer val = 0;
		try {
			val = repoL2.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.err.println(e);
		}

		return val;
		// return repoL2.queryForRowSet(sql).findColumn("ID");
	}

	private Integer nextValOnsiteValidation() {
		String sql = "SELECT SEQ_ONSITEVALIDATION_ID.nextval as ID FROM dual";
		Integer val = 0;
		try {
			val = repoL2.queryForObject(sql, Integer.class);
		} catch (Exception e) {
			System.err.println(e);
		}

		return val;
		// return repoL2.queryForRowSet(sql).findColumn("ID");
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
