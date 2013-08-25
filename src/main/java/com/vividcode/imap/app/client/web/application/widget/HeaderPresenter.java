package com.vividcode.imap.app.client.web.application.widget;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.vividcode.imap.app.client.gin.BootstrapperImpl;
import com.vividcode.imap.app.client.rest.AuthenticationService;
import com.vividcode.imap.common.client.event.RequestEvent;
import com.vividcode.imap.common.client.rest.AsyncCallbackImpl;
import com.vividcode.imap.common.client.security.SecurityUtils;
import com.vividcode.imap.common.shared.dispatch.NoResult;

import java.util.ArrayList;
import java.util.List;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView>
        implements HeaderUiHandlers, RequestEvent.RequestEventHandler {
    public interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
        void setUsername(String username);

        void showAjaxLoader();

        void hideAjaxLoader();
    }

    private final PlaceManager placeManager;
    private final DispatchAsync dispatcher;
    private final AuthenticationService authenticationService;
    private final BootstrapperImpl bootstrapper;
    private final SecurityUtils securityUtils;

    private List<AsyncCallback<?>> pendingRequest;

    @Inject
    public HeaderPresenter(EventBus eventBus,
                           MyView view,
                           PlaceManager placeManager,
                           DispatchAsync dispatcher,
                           BootstrapperImpl bootstrapper,
                           SecurityUtils securityUtils,
                           AuthenticationService authenticationService) {
        super(eventBus, view);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;
        this.bootstrapper = bootstrapper;
        this.securityUtils = securityUtils;
        this.authenticationService = authenticationService;
        this.pendingRequest = new ArrayList<AsyncCallback<?>>();

        getView().setUiHandlers(this);
    }

    @Override
    public void onRequestEvent(RequestEvent requestEvent) {
        if (requestEvent.getState() == RequestEvent.State.SENT) {
            pendingRequest.add(requestEvent.getRequest());
            getView().showAjaxLoader();
        } else {
            pendingRequest.remove(requestEvent.getRequest());
            if (pendingRequest.isEmpty()) {
                getView().hideAjaxLoader();
            }
        }
    }

    @Override
    public void logout() {
        dispatcher.execute(authenticationService.logout(), new AsyncCallbackImpl<NoResult>() {
            @Override
            public void onReceive(NoResult response) {
                bootstrapper.onBootstrap();
            }
        });
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(RequestEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        getView().setUsername(securityUtils.getUsername());
    }
}
