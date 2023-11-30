package ar.gob.dgciud.nivel3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.gob.dgciud.nivel3.service.MainService;

@SpringBootApplication
public class Nivel3Application implements CommandLineRunner {

	@Autowired
	MainService s;

	@Value("${test}")
	private String test;

	public static void main(String[] args) {
		SpringApplication.run(Nivel3Application.class, args);

		System.out.println("FIN Nivel3Application");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("init Nivel3Application 2");

		System.out.println("Contenido propertie de test : " + test);

//		s.runScript();

	}

}
