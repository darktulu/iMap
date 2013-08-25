package com.vividcode.imap.app.client.web.welcome.login;

import com.gwtplatform.mvp.client.UiHandlers;
import com.vividcode.imap.common.shared.dto.UserCredentials;

public interface LoginUiHandlers extends UiHandlers {
    void login(UserCredentials credentials);

    void bounceToRegister();
}
