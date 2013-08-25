package com.vividcode.imap.app.client.web.welcome.login;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.vividcode.imap.common.client.rest.AsyncCallbackImpl;
import com.vividcode.imap.common.client.security.SecurityUtils;
import com.vividcode.imap.common.shared.dispatch.GetResult;
import com.vividcode.imap.app.client.gin.BootstrapperImpl;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.rest.AuthenticationService;
import com.vividcode.imap.app.client.web.RootPresenter;
import com.vividcode.imap.common.shared.dto.UserCredentials;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
        implements LoginUiHandlers {
    public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        void edit(UserCredentials credentials);

        void displayLoginError(Boolean visible);
    }

    @ProxyStandard
    @NameToken(NameTokens.login)
    public interface MyProxy extends ProxyPlace<LoginPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final AuthenticationService authenticationService;
    private final SecurityUtils securityUtils;
    private final BootstrapperImpl bootstrapper;
    private final PlaceManager placeManager;

    @Inject
    public LoginPresenter(EventBus eventBus,
                          MyView view,
                          MyProxy proxy,
                          DispatchAsync dispatcher,
                          AuthenticationService authenticationService,
                          SecurityUtils securityUtils,
                          PlaceManager placeManager,
                          BootstrapperImpl bootstrapper) {
        super(eventBus, view, proxy, RootPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;
        this.placeManager = placeManager;
        this.authenticationService = authenticationService;
        this.securityUtils = securityUtils;
        this.bootstrapper = bootstrapper;

        getView().setUiHandlers(this);
    }

    @Override
    public void login(final UserCredentials credentials) {
        dispatcher.execute(authenticationService.authenticate(credentials), new AsyncCallbackImpl<GetResult<Boolean>>() {
            @Override
            public void onReceive(GetResult<Boolean> response) {
                if (response.getPayload()) {
                    securityUtils.setCredentials(credentials.getUsername(), credentials.getPassword());
                    bootstrapper.onBootstrap();
                } else {
                    getView().displayLoginError(true);
                }
            }
        });
    }

    @Override
    protected void onReveal() {
        getView().edit(new UserCredentials());
    }

    @Override
    public void bounceToRegister() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getRegister()).build();
        placeManager.revealPlace(place);
    }
}
