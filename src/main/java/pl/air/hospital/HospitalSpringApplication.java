package pl.air.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.air.hospital.init.DataService;

@SpringBootApplication
public class HospitalSpringApplication implements ApplicationRunner {
    
	@Autowired private DataService dataSrv;
    
	public static void main(String[] args) {
		SpringApplication.run(HospitalSpringApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
     dataSrv.insertData();
	}
}
