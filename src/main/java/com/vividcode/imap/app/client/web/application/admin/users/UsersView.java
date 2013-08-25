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

package com.vividcode.imap.app.client.web.application.admin.users;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.vividcode.imap.common.client.resource.style.DataGridStyle;
import com.vividcode.imap.common.shared.constants.GlobalParameters;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.inject.Inject;
import java.util.List;

public class UsersView extends ViewWithUiHandlers<UsersUiHandlers>
        implements UsersPresenter.MyView {
    public interface Binder extends UiBinder<Widget, UsersView> {
    }

    @UiField(provided = true)
    DataGrid<UserVO> userDataGrid;
    @UiField
    Button update;
    @UiField
    Button remove;

    private final ListDataProvider<UserVO> dataProvider;
    private final SingleSelectionModel<UserVO> selectionModel;

    @Inject
    UsersView(Binder uiBinder,
              DataGridStyle dataGridStyle,
              ListDataProvider<UserVO> dataProvider) {
        this.dataProvider = dataProvider;
        this.userDataGrid = new DataGrid<UserVO>(GlobalParameters.PAGE_SIZE, dataGridStyle);
        this.selectionModel = new SingleSelectionModel<UserVO>();

        userDataGrid.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                update.setEnabled(true);
                remove.setEnabled(true);
            }
        });

        initWidget(uiBinder.createAndBindUi(this));
        dataProvider.addDataDisplay(userDataGrid);
        initColumns();
    }
    @Override
    public void setData(List<UserVO> data) {
        dataProvider.setList(data);
        userDataGrid.setRowCount(data.size());

        update.setEnabled(false);
        remove.setEnabled(false);
    }
    @UiHandler("newUser")
    void onNewUserClicked(ClickEvent event) {
        getUiHandlers().addNewUser();
    }

    @UiHandler("remove")
    void onRemoveClicked(ClickEvent event) {
        UserVO selectedUser = selectionModel.getSelectedObject();
        if (selectedUser != null) {
            getUiHandlers().removeUser(selectedUser);
        }
    }

    @UiHandler("update")
    void onUpdateClicked(ClickEvent event) {
        UserVO selectedUser = selectionModel.getSelectedObject();
        if (selectedUser != null) {
            getUiHandlers().editUser(selectedUser);
        }
    }

    private void initColumns() {
        TextColumn<UserVO> loginColumn = new TextColumn<UserVO>() {
            @Override
            public String getValue(UserVO object) {
                return object.getUsername();
            }
        };
        loginColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        userDataGrid.addColumn(loginColumn, "Login");

        TextColumn<UserVO> emailColumn = new TextColumn<UserVO>() {
            @Override
            public String getValue(UserVO object) {
                return object.getEmail();
            }
        };
        emailColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        userDataGrid.addColumn(emailColumn, "Email");

        TextColumn<UserVO> nomPrenomColumn = new TextColumn<UserVO>() {
            @Override
            public String getValue(UserVO object) {
                return object.getFirstName() + " " + object.getLastName();
            }
        };
        nomPrenomColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        userDataGrid.addColumn(nomPrenomColumn, "Nom & prénom");

        TextColumn<UserVO> roleColumn = new TextColumn<UserVO>() {
            @Override
            public String getValue(UserVO object) {
                return object.getAuthority().toString();
            }
        };
        roleColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        userDataGrid.addColumn(roleColumn, "Role");
    }
}
