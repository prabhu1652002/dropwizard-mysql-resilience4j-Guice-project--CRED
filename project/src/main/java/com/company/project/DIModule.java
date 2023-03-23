package com.company.project;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DIModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ResourceModule());
    }
}
