package com.vividcode.imap.app.client.web;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.application.ApplicationModule;
import com.vividcode.imap.app.client.web.welcome.WelcomeModule;

public class RootModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new ApplicationModule());
        install(new WelcomeModule());

        bindPresenter(RootPresenter.class, RootPresenter.MyView.class, RootView.class,
                RootPresenter.MyProxy.class);
    }
}
