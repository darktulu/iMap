package com.vividcode.imap.app.client.web.application.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.vividcode.imap.app.client.resource.message.MessageBundle;

import javax.inject.Inject;

public class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements HeaderPresenter.MyView {
    public interface Binder extends UiBinder<Widget, HeaderView> {
    }

    @UiField
    Label username;
    @UiField
    Label reload;

    private final MessageBundle messageBundle;

    @Inject
    HeaderView(Binder uiBinder,
               MessageBundle messageBundle) {
        this.messageBundle = messageBundle;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setUsername(String login) {
        username.setText(messageBundle.welcomeMessage(login));
    }

    @Override
    public void showAjaxLoader() {
        reload.addStyleName("icon-spin");
    }

    @Override
    public void hideAjaxLoader() {
        reload.removeStyleName("icon-spin");
    }

    @UiHandler("logout")
    void onLogoutClicked(ClickEvent event) {
        getUiHandlers().logout();
    }
}
