package com.vividcode.imap.server.service.security;

import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.server.model.User;
import org.springframework.security.core.Authentication;

public interface SecurityContextProvider {
    UserVO getCurrentUser();

    Boolean logout();

    void setAuthentication(Authentication authenticate);

    User getConnectedUser();
}
