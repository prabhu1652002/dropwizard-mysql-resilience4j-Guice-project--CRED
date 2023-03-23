package com.company.project.resources;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@RequiredArgsConstructor(onConstructor_ = { @Inject})

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MockServerApi {
    public String checkMockResponse() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://24a51464-eb57-4fdd-894b-ab58d5ed97ed.mock.pstmn.io/localhost:8080/")
                .path("mocked");

        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }
}