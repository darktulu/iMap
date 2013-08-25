package com.vividcode.imap.app.client.web.application.admin.locations;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LocationsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(LocationsUiHandlers.class).to(LocationsPresenter.class);

        bindPresenter(LocationsPresenter.class, LocationsPresenter.MyView.class, LocationsView.class,
                LocationsPresenter.MyProxy.class);
    }
}
