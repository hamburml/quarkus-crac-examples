package de.hamburml.crac1.service;

import de.hamburml.crac1.client.MeowfactsAPI;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;

@ApplicationScoped
@Slf4j
public class CatService {

    @Inject
    @RestClient
    MeowfactsAPI catAPI;

    MeowfactsAPI catAPIBuilded;

    @SneakyThrows
    void onStart(@Observes StartupEvent ev) {
        //log.info("StartupEvent: " + catAPI.getRandomFact().readEntity(String.class));
        catAPIBuilded = RestClientBuilder.newBuilder()
                .baseUri(new URI("https://meowfacts.herokuapp.com/"))
                .build(MeowfactsAPI.class);
    }

    public String getRandomFact() {
        var before = Instant.now();
        log.info("getRandomFact start {}", before);
        var str = this.catAPI.getRandomFact().readEntity(String.class);
        var after = Instant.now();
        log.info("getCat after {}", after);
        log.info("getCat duration {}", Duration.between(before, after).toMillis());
        return str;
    }

    @SneakyThrows
    public String getRandomFact2() {
        var before = Instant.now();
        log.info("getRandomFact2 start {}", before);
        var str = catAPIBuilded
                .getRandomFact().readEntity(String.class);
        var after = Instant.now();
        log.info("getCat2 after {}", after);
        log.info("getCat2 duration {}", Duration.between(before, after).toMillis());
        return str;
    }

}