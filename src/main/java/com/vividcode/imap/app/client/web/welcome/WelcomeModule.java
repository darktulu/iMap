package com.vividcode.imap.app.client.web.welcome;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.welcome.login.LoginPresenter;
import com.vividcode.imap.app.client.web.welcome.login.LoginUiHandlers;
import com.vividcode.imap.app.client.web.welcome.login.LoginView;
import com.vividcode.imap.app.client.web.welcome.register.RegisterPresenter;
import com.vividcode.imap.app.client.web.welcome.register.RegisterUiHandlers;
import com.vividcode.imap.app.client.web.welcome.register.RegisterView;

public class WelcomeModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(LoginUiHandlers.class).to(LoginPresenter.class);
        bind(RegisterUiHandlers.class).to(RegisterPresenter.class);

        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);
        bindPresenter(RegisterPresenter.class, RegisterPresenter.MyView.class, RegisterView.class,
                RegisterPresenter.MyProxy.class);
    }
}
