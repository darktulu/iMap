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

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.application.admin.users.popup.EditUserPresenter;
import com.vividcode.imap.app.client.web.application.admin.users.popup.EditUserPresenterFactory;
import com.vividcode.imap.app.client.web.application.admin.users.popup.EditUserView;

public class UsersModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(UsersUiHandlers.class).to(UsersPresenter.class);

        bindPresenter(UsersPresenter.class, UsersPresenter.MyView.class, UsersView.class,
                UsersPresenter.MyProxy.class);

        bindSharedView(EditUserPresenter.MyView.class, EditUserView.class);

        install(new GinFactoryModuleBuilder().build(EditUserPresenterFactory.class));
    }
}
