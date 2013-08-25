package com.vividcode.imap.app.client.web.application.admin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.application.admin.locations.LocationsModule;
import com.vividcode.imap.app.client.web.application.admin.users.UsersModule;

public class SettingsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new UsersModule());
        install(new LocationsModule());

        bind(SettingsUiHandlers.class).to(SettingsPresenter.class);

        bindPresenter(SettingsPresenter.class, SettingsPresenter.MyView.class, SettingsView.class,
                SettingsPresenter.MyProxy.class);
    }
}
