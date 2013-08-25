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

package com.vividcode.imap.app.client.gin;

import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.vividcode.imap.common.client.security.SecurityUtils;
import com.vividcode.imap.common.client.util.CallbackImpl;
import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.security.CurrentUserProvider;

import javax.inject.Inject;
import java.util.logging.Logger;

public class BootstrapperImpl implements Bootstrapper {
    private final static Logger logger = Logger.getLogger(BootstrapperImpl.class.getName());

    private final PlaceManager placeManager;
    private final SecurityUtils securityUtils;
    private final CurrentUserProvider currentUserProvider;
    private final CallbackImpl<UserVO> getCurrentUserCallback;

    @Inject
    BootstrapperImpl(PlaceManager placeManager,
                     SecurityUtils securityUtils,
                     CurrentUserProvider currentUserProvider) {
        this.placeManager = placeManager;
        this.securityUtils = securityUtils;
        this.currentUserProvider = currentUserProvider;

        getCurrentUserCallback = new CallbackImpl<UserVO>() {
            @Override
            public void onSuccess(UserVO user) {
                onGetCurrentUser(user);
            }
        };
    }

    @Override
    public void onBootstrap() {
        if (securityUtils.isLoggedIn()) {
            currentUserProvider.load(getCurrentUserCallback);
        } else {
            placeManager.revealCurrentPlace();
        }
    }

    private void onGetCurrentUser(UserVO currentUser) {
        if (currentUser == null) {
            logger.info("User is not authentified -- access denied...");
            bounceToLogin();
        } else {
            bounceToHome();
        }
    }

    private void bounceToHome() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getHome()).build();
        placeManager.revealPlace(place);
    }

    private void bounceToLogin() {
        PlaceRequest place = new PlaceRequest.Builder().nameToken(NameTokens.getLogin()).build();
        placeManager.revealPlace(place);
    }
}
