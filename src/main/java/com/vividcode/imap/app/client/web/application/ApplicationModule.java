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

package com.vividcode.imap.app.client.web.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.app.client.web.application.admin.SettingsModule;
import com.vividcode.imap.app.client.web.application.home.HomeModule;
import com.vividcode.imap.app.client.web.application.learn.LearnModule;
import com.vividcode.imap.app.client.web.application.map.GeolocalisationModule;
import com.vividcode.imap.app.client.web.application.widget.HeaderPresenter;
import com.vividcode.imap.app.client.web.application.widget.HeaderUiHandlers;
import com.vividcode.imap.app.client.web.application.widget.HeaderView;
import com.vividcode.imap.app.client.web.application.widget.MenuPresenter;
import com.vividcode.imap.app.client.web.application.widget.MenuView;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new LearnModule());
        install(new GeolocalisationModule());
        install(new SettingsModule());

        bind(HeaderUiHandlers.class).to(HeaderPresenter.class);

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class,
                HeaderView.class);
        bindSingletonPresenterWidget(MenuPresenter.class, MenuPresenter.MyView.class,
                MenuView.class);
    }
}
