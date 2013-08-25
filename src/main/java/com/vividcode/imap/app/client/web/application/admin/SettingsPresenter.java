/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vividcode.imap.app.client.web.application.admin;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.web.application.ApplicationPresenter;
import com.vividcode.imap.common.client.security.LoggedInGatekeeper;

import javax.inject.Inject;

public class SettingsPresenter extends Presenter<SettingsPresenter.MyView, SettingsPresenter.MyProxy> implements SettingsUiHandlers {
    public interface MyView extends View, HasUiHandlers<SettingsUiHandlers> {
        void setSelectedMenu(String currentToken);
    }

    @ProxyStandard
    @NameToken(NameTokens.admin)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<SettingsPresenter> {
    }

    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetSettingContent = new Type<RevealContentHandler<?>>();

    private final PlaceManager placeManager;

    @Inject
    SettingsPresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy,
                      PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void showSettingView(String place) {
        PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(place).build();
        placeManager.revealRelativePlace(placeRequest, 1);
    }

    @Override
    protected void onReveal() {
        PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.getUsers()).build();
        placeManager.revealRelativePlace(placeRequest, 1);
        getView().setSelectedMenu(placeManager.getCurrentPlaceRequest().getNameToken());
    }
}
