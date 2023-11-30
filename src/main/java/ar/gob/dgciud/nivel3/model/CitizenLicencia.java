package ar.gob.dgciud.nivel3.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.datetime.DateFormatter;

/**
 * Reprenta la informacion enviada por licencias en el CSV
 * "tipo_doc","documento","pais","genero","fecha"
 */
public class CitizenLicencia extends CitizenModel {

	private static final String COMA = ",";
	private String tipo_doc;
	private String documento;
	private String pais;
	private String genero;
	private Date fecha;
	DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public CitizenLicencia() {
		// TODO Auto-generated constructor stub
	}

	/** EJ : DNI,32166799,ARGENTINA ,m,2020-11-04 13:44:50.531739 */
	public CitizenLicencia(String csvLine) {
		try {
			String[] split = csvLine.split(COMA);
			this.tipo_doc = split[0].trim();
			this.documento = split[1].trim();
			this.pais = split[2].trim();
			this.genero = split[3];

			Timestamp ts = Timestamp.valueOf(split[4]);
			this.fecha = new Date(ts.getTime());

		} catch (Exception e) {
			System.err.println("error al transformar desde CSV el citizen licencia " + csvLine);
			// e.printStackTrace();
		}
	}

	public String toCsvLine() {
		String line = "";
		line += this.getTipo_doc() + COMA;
		line += this.getDocumento() + COMA;
		line += this.getPais() + COMA;
		line += this.getGenero() + COMA;
		line += sdfDestination.format(this.getFecha());

		return line;
	}

	public String getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
