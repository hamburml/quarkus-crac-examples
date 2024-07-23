package de.hamburml.crac1.resource;

import de.hamburml.crac1.service.CatService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;

@ApplicationScoped
@Path("cat")
@Slf4j
public class CatResource {

    @Inject
    CatService catService;

    @GET
    public String getCat() {
        var before = Instant.now();
        log.info("getCat start {}", before);
        var catFact = this.catService.getRandomFact2();
        var after = Instant.now();
        log.info("getCat after {}", after);
        log.info("getCat duration {}", Duration.between(before, after).toMillis());
        return catFact;
    }
}
