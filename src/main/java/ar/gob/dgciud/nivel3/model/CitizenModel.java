package ar.gob.dgciud.nivel3.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CitizenModel {
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
