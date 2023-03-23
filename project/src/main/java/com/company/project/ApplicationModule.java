package com.company.project;

import com.company.project.resources.MockServerApi;
import com.company.project.resources.TestEndpoints;
import com.company.project.sql.jdbcMain;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import io.dropwizard.setup.Environment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationModule extends AbstractModule {

    private final Environment environment;

    private final Injector injector;

    @Override
    protected void configure() {
        registerResources();
    }

    private void registerResources() {
        environment.jersey().register(injector.getInstance(TestEndpoints.class));
        environment.jersey().register(injector.getInstance(jdbcMain.class));
        environment.jersey().register(injector.getInstance(MockServerApi.class));
    }
}
