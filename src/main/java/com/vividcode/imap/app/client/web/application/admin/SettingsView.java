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

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.vividcode.imap.app.client.place.NameTokens;

import javax.inject.Inject;

public class SettingsView extends ViewWithUiHandlers<SettingsUiHandlers> implements SettingsPresenter.MyView {
    public interface Binder extends UiBinder<Widget, SettingsView> {
    }

    @UiField
    NavLink users;
    @UiField
    NavLink locations;
    @UiField
    SimpleLayoutPanel settingsDisplay;

    @Inject
    SettingsView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (content != null) {
            if (slot == SettingsPresenter.TYPE_SetSettingContent) {
                settingsDisplay.setWidget(content);
            }
        }
    }

    @Override
    public void setSelectedMenu(String currentToken) {
        clearActive();

        if (currentToken.equals(NameTokens.getLocations())) {
            locations.setActive(true);
        } else if (currentToken.equals(NameTokens.getUsers())) {
            users.setActive(true);
        }
    }

    @UiHandler("locations")
    void onLocationsClicked(ClickEvent event) {
        clearActive();
        locations.setActive(true);
        getUiHandlers().showSettingView(NameTokens.getLocations());
    }

    @UiHandler("users")
    void onUsersClicked(ClickEvent event) {
        clearActive();
        users.setActive(true);
        getUiHandlers().showSettingView(NameTokens.getUsers());
    }

    private void clearActive() {
        locations.setActive(false);
        users.setActive(false);
    }
}
