package ar.gob.dgciud.nivel3.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UtilsFile {

	public static List<String> readCsv(String csvFile) throws IOException {
		String line = "";
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				lines.add(line.replaceAll("\"", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	public static void writeLine(String pathFile, String line) throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter(pathFile, true)); // clears file every time
		output.append(line);
		output.append('\n');
		output.close();
	}
}
