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

import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.vividcode.imap.app.client.resource.message.MessageBundle;
import com.vividcode.imap.app.client.rest.UserService;
import com.vividcode.imap.common.client.event.ReloadEvent;
import com.vividcode.imap.common.client.rest.AsyncCallbackImpl;
import com.vividcode.imap.common.client.widget.messages.Message;
import com.vividcode.imap.common.client.widget.messages.event.MessageEvent;
import com.vividcode.imap.common.shared.dispatch.ValidatedResponse;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.inject.Inject;

public class EditUserPresenter extends PresenterWidget<EditUserPresenter.MyView> implements EditUserUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<EditUserUiHandlers> {
        void editUser(UserVO user);
    }

    private final DispatchAsync dispatchAsync;
    private final UserService userService;
    private final MessageBundle messageBundle;
    private final PlaceManager placeManager;
    private final UserVO user;

    @Inject
    EditUserPresenter(EventBus eventBus,
                      MyView view,
                      DispatchAsync dispatchAsync,
                      UserService userService,
                      MessageBundle messageBundle,
                      PlaceManager placeManager,
                      @Assisted UserVO user) {
        super(eventBus, view);

        this.dispatchAsync = dispatchAsync;
        this.userService = userService;
        this.messageBundle = messageBundle;
        this.placeManager = placeManager;
        this.user = user;

        getView().setUiHandlers(this);
    }

    @Override
    public void saveUser(UserVO user) {
        if (user.getId() == null) {
            dispatchAsync.execute(userService.create(user), new AsyncCallbackImpl<ValidatedResponse>() {
                @Override
                public void onReceive(ValidatedResponse response) {
                    Message message = new Message.Builder("success")
                            .style(AlertType.SUCCESS).build();
                    MessageEvent.fire(this, message);
                    getView().hide();

                    ReloadEvent.fire(this, placeManager.getCurrentPlaceRequest());
                }
            });
        } else {
            dispatchAsync.execute(userService.update(user), new AsyncCallbackImpl<ValidatedResponse>() {
                @Override
                public void onReceive(ValidatedResponse response) {
                    Message message = new Message.Builder("error")
                            .style(AlertType.SUCCESS).build();
                    MessageEvent.fire(this, message);
                    getView().hide();

                    ReloadEvent.fire(this, placeManager.getCurrentPlaceRequest());
                }
            });
        }
    }

    @Override
    public void onReveal(){
        getView().editUser(user);
    }
}
