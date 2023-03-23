package com.company.project;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class projectApplication extends Application<projectConfiguration> {
    public static void main(final String[] args) throws Exception {
        new projectApplication().run(args);
    }

    @Override
    public String getName() {
        return "project";
    }

    @Override
    public void initialize(final Bootstrap<projectConfiguration> bootstrap) {
    }

    @Override
    public void run(final projectConfiguration configuration ,final Environment environment)  {
        Injector injector = Guice.createInjector(new DIModule());
        new ApplicationModule(environment, injector).configure();
    }

}
