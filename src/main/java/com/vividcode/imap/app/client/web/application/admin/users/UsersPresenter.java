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

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.resource.message.MessageBundle;
import com.vividcode.imap.app.client.rest.UserService;
import com.vividcode.imap.app.client.web.application.admin.SettingsPresenter;
import com.vividcode.imap.app.client.web.application.admin.users.popup.EditUserPresenterFactory;
import com.vividcode.imap.common.client.event.ReloadEvent;
import com.vividcode.imap.common.client.rest.AsyncCallbackImpl;
import com.vividcode.imap.common.client.widget.messages.Message;
import com.vividcode.imap.common.client.widget.messages.event.MessageEvent;
import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.dispatch.NoResult;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.inject.Inject;
import java.util.List;

public class UsersPresenter extends Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> implements UsersUiHandlers,
        ReloadEvent.ReloadEventHandler {
    public interface MyView extends View, HasUiHandlers<UsersUiHandlers> {
        void setData(List<UserVO> data);
    }

    @ProxyStandard
    @NameToken(NameTokens.users)
    public interface MyProxy extends ProxyPlace<UsersPresenter> {
    }

    private final DispatchAsync dispatchAsync;
    private final UserService userService;
    private final MessageBundle messageBundle;
    private final EditUserPresenterFactory editUserPresenterFactory;

    @Inject
    UsersPresenter(EventBus eventBus,
                   MyView view,
                   MyProxy proxy,
                   DispatchAsync dispatchAsync,
                   UserService userService,
                   MessageBundle messageBundle,
                   EditUserPresenterFactory editUserPresenterFactory) {
        super(eventBus, view, proxy, SettingsPresenter.TYPE_SetSettingContent);

        this.dispatchAsync = dispatchAsync;
        this.userService = userService;
        this.messageBundle = messageBundle;
        this.editUserPresenterFactory = editUserPresenterFactory;

        getView().setUiHandlers(this);
    }

    @Override
    public void onRequestEvent(ReloadEvent requestEvent) {
        if (requestEvent.getPlaceRequest().matchesNameToken(NameTokens.getUsers())) {
            loadAllUsers();
        }
    }

    @Override
    public void addNewUser() {
        addToPopupSlot(editUserPresenterFactory.create(new UserVO()));
    }

    @Override
    public void editUser(UserVO user) {
        addToPopupSlot(editUserPresenterFactory.create(user));
    }

    @Override
    public void removeUser(UserVO user) {
        if (Window.confirm(messageBundle.userDeleteConfirmation(user.getFirstName() + " " + user.getLastName()))) {
            dispatchAsync.execute(userService.delete(user.getId()), new AsyncCallback<NoResult>() {
                @Override
                public void onFailure(Throwable caught) {
                }

                @Override
                public void onSuccess(NoResult result) {
                    Message message = new Message.Builder("success")
                            .style(AlertType.SUCCESS).build();
                    MessageEvent.fire(UsersPresenter.this, message);

                    loadAllUsers();
                }
            });
        }
    }

    @Override
    protected void onReveal() {
        loadAllUsers();
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ReloadEvent.getType(), this);
    }

    private void loadAllUsers() {
        dispatchAsync.execute(userService.loadAll(), new AsyncCallbackImpl<GetResults<UserVO>>() {
            @Override
            public void onReceive(GetResults<UserVO> response) {
                getView().setData(response.getPayload());
            }
        });
    }
}
