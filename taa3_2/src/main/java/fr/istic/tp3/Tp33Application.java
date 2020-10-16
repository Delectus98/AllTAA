package fr.istic.tp3;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.istic.rest.tp2.KabanRestService;
import fr.istic.taa.jaxrs.TestApplication;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.undertow.Undertow;

@SpringBootApplication
public class Tp33Application extends Application implements CommandLineRunner{
	
	public void run(String...strings) {
		UndertowJaxrsServer ut = new UndertowJaxrsServer();

		Tp33Application ta = new Tp33Application();

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );

	}
	
	@Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<Class<?>>();

        clazzes.add(KabanRestService.class);

        return clazzes;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Tp33Application.class, args);
	}

}
