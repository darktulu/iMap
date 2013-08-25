package com.vividcode.imap.app.client.web.application.map;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.application.map.GeolocalisationPresenter.MyProxy;
import com.vividcode.imap.app.client.web.application.map.GeolocalisationPresenter.MyView;

public class GeolocalisationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(GeolocalisationUiHandlers.class).to(GeolocalisationPresenter.class);

        bindPresenter(GeolocalisationPresenter.class, MyView.class, GeolocalisationView.class, MyProxy.class);
    }
}
