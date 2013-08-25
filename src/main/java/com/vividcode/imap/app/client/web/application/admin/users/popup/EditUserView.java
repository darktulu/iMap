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

package com.vividcode.imap.app.client.web.application.admin.users.popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.vividcode.imap.app.client.web.application.admin.users.ui.UserEditor;
import com.vividcode.imap.common.client.widget.ModalHeader;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.inject.Inject;

public class EditUserView extends PopupViewWithUiHandlers<EditUserUiHandlers> implements EditUserPresenter.MyView {
    public interface Binder extends UiBinder<PopupPanel, EditUserView> {
    }

    @UiField(provided = true)
    ModalHeader modalHeader;
    @UiField(provided = true)
    UserEditor userEditor;

    @Inject
    EditUserView(EventBus eventBus,
                 Binder uiBinder,
                 ModalHeader modalHeader,
                 UserEditor userEditor) {
        super(eventBus);

        this.modalHeader = modalHeader;
        this.userEditor = userEditor;

        initWidget(uiBinder.createAndBindUi(this));

        modalHeader.addCloseHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                hide();
            }
        });
    }

    @Override
    public void editUser(UserVO user) {
        userEditor.edit(user);
    }

    @UiHandler("save")
    void onSaveClicked(ClickEvent event) {
        getUiHandlers().saveUser(userEditor.get());
    }
}
