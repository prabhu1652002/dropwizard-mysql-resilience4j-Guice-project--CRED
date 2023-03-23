package com.company.project.resources;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

@RequiredArgsConstructor(onConstructor_ = { @Inject})

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


@Path("testing")
public class TestEndpoints {
    static MockServerApi MyMock=new MockServerApi();

    CircuitBreaker circuitBreaker = CircuitBreaker.of("myCircuitBreaker",
            CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)
                    .waitDurationInOpenState(Duration.ofMillis(1000))
                    .build());

    static String url="https://24a51464-eb57-4fdd-894b-ab58d5ed97ed.mock.pstmn.io/localhost:8080/mocked";
    String response = circuitBreaker.executeSupplier(() -> {
        // Check if the user has exceeded the rate limit
        // If yes, throw an exception
        // Otherwise, make the HTTP request
        return makeHttpRequest(url);
    });
    private static Semaphore semaphore = new Semaphore(10);
    private static Map<String, Long> requestCountMap = new HashMap<>();
    static long requestCount=0, now ,prev;
    @GET
    @Path("/mocked")
    public static String makeHttpRequest(String url) {
        // Get the current timestamp
        now= System.currentTimeMillis();
        // If the user has exceeded the rate limit, throw an exception
        if (now-prev < 10000) {
            System.out.println("Rate exceeded");
            return "Try again after a few seconds";
        }

        String response = MyMock.checkMockResponse();
        prev=now;
        return response;
    }

    public String MockedRequest(){
        MockServerApi MyMock= new MockServerApi();
        return MyMock.checkMockResponse();
    }

    @GET
    @Path("/end1")
    public static String getEnd1(){
        return "Testing endpoint 1";
    }
    @GET
    @Path("/end2")
    public static String getEnd2(){
        return "Testing endpoint 2";
    }
    @GET
    @Path("/end3")
    public static String getEnd3(){
        return "Testing endpoint 3";
    }
    @GET
    @Path("/end4")
    public static String getEnd4(){
        return "Testing endpoint 4";
    }
}

