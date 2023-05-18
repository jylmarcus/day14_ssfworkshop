package sg.visa.ssf.day13_ssfworkshop;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.visa.ssf.day13_ssfworkshop.utility.Utility;

@SpringBootApplication
public class Day13SsfworkshopApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Day13SsfworkshopApplication.class);
		DefaultApplicationArguments cliArgs = new DefaultApplicationArguments(args);
		if(!cliArgs.containsOption("dataDir")){
			System.out.println("Data directory not found");
			System.exit(1);
		} else {
			Utility.createDir(cliArgs.getOptionValues("dataDir").get(0));
			
		}


		app.run(args);
	}

}
