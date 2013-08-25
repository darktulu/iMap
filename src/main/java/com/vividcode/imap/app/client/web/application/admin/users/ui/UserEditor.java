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

package com.vividcode.imap.app.client.web.application.admin.users.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.vividcode.imap.common.client.util.EditorView;
import com.vividcode.imap.common.client.widget.renderer.EnumRenderer;
import com.vividcode.imap.common.shared.type.Authority;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.inject.Inject;
import java.util.Arrays;

public class UserEditor extends Composite implements EditorView<UserVO> {
    public interface Binder extends UiBinder<Widget, UserEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<UserVO, UserEditor> {
    }

    @UiField
    TextBox username;
    @UiField
    TextBox firstName;
    @UiField
    TextBox lastName;
    @UiField
    TextBox email;
    @UiField
    PasswordTextBox password;
    @UiField(provided = true)
    ValueListBox<Authority> authority;

    private final Driver driver;

    @Inject
    UserEditor(Binder uiBinder,
               Driver driver) {
        this.driver = driver;
        this.authority = new ValueListBox<Authority>(new EnumRenderer<Authority>());

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        authority.setAcceptableValues(Arrays.asList(Authority.values()));
    }

    @Override
    public void edit(UserVO object) {
        driver.edit(object);
    }

    @Override
    public UserVO get() {
        UserVO user = driver.flush();
        if (!driver.hasErrors()) {
            return user;
        }
        return null;
    }
}
