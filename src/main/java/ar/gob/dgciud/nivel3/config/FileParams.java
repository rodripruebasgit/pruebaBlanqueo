package ar.gob.dgciud.nivel3.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileParams {

	@Value("${file.input}")
	private String fileInput;

	@Value("${folder.output}")
	private String folderOutput;

	@Value("${max.sql.sentences}")
	private Integer maxPerFile;

	@PostConstruct
	private void init() {

		System.out.println("File Params :");
		System.out.println("csv licencias " + fileInput);
		System.out.println("path folder output " + folderOutput);
		System.out.println("Maximo de lineas por archivo " + maxPerFile);
	}

	public String getFileInput() {
		return fileInput;
	}

	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}

	public String getFolderOutput() {
		return folderOutput;
	}

	public void setFolderOutput(String folderOutput) {
		this.folderOutput = folderOutput;
	}

	public Integer getMaxPerFile() {
		return maxPerFile;
	}

	public void setMaxPerFile(Integer maxPerFile) {
		this.maxPerFile = maxPerFile;
	}

}
