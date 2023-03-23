package com.company.project;

import com.company.project.resources.MockServerApi;
import com.company.project.resources.TestEndpoints;
import com.company.project.sql.jdbcMain;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(TestEndpoints.class).asEagerSingleton();
        bind(MockServerApi.class).asEagerSingleton();
        bind(jdbcMain.class).asEagerSingleton();
    }
}
