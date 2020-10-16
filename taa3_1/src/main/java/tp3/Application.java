package tp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private IRun runner;
	
	public void run(String...strings) {
		runner.run();		
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
