package com.vividcode.imap.app.client.web;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.vividcode.imap.common.client.widget.messages.MessagePresenter;

import javax.inject.Inject;

public class RootPresenter extends Presenter<RootPresenter.MyView, RootPresenter.MyProxy> {
    public interface MyView extends View {
    }

    @ProxyStandard
    public interface MyProxy extends Proxy<RootPresenter> {
    }

    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_SetMainContent = new GwtEvent.Type<RevealContentHandler<?>>();
    public static final Object TYPE_SetMessageContent = new Object();

    private final MessagePresenter messagePresenter;

    @Inject
    RootPresenter(EventBus eventBus,
                  MyView view,
                  MyProxy proxy,
                  MessagePresenter messagePresenter) {
        super(eventBus, view, proxy, RevealType.RootLayout);

        this.messagePresenter = messagePresenter;
    }

    @Override
    protected void onReveal() {
        setInSlot(TYPE_SetMessageContent, messagePresenter);
    }
}
