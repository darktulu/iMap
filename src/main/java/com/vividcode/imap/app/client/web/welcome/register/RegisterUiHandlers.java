package com.vividcode.imap.app.client.web.welcome.register;

import com.gwtplatform.mvp.client.UiHandlers;
import com.vividcode.imap.common.shared.vo.UserVO;

public interface RegisterUiHandlers extends UiHandlers {
    void register(UserVO user);

    void bounceToLogin();
}
