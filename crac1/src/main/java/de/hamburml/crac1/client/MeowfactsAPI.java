package de.hamburml.crac1.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "meowfacts-api")
public interface MeowfactsAPI {

    @GET
    Response getRandomFact();
}
