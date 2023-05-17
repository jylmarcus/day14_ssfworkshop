package sg.visa.ssf.day13_ssfworkshop;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13SsfworkshopApplication {

	public static void main(String[] args) {
		DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args);
		Path dataDir;
		if(!cliArgs.containsOption("dataDir")){
			System.out.println("Data directory not found");
			System.exit(1);
		} else {
			dataDir = Paths.get(cliArgs.getOptionValues("dataDir").get(0));
			if(!dataDir.toFile().exists()){
				dataDir.toFile().mkdir();
			}
		}


		SpringApplication.run(Day13SsfworkshopApplication.class, args);
	}

}
