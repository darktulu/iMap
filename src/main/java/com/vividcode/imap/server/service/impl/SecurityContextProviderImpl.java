package com.vividcode.imap.server.service.impl;

import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.server.model.User;
import com.vividcode.imap.server.repos.UserRepo;
import com.vividcode.imap.server.service.security.SecurityContextProvider;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.vividcode.imap.server.repos.spec.UserSpec.emailIs;
import static com.vividcode.imap.server.repos.spec.UserSpec.usernameIs;
import static org.springframework.data.jpa.domain.Specifications.where;

@Component
public class SecurityContextProviderImpl implements SecurityContextProvider {
    @Inject
    private UserRepo userRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserVO getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            String login = securityContext.getAuthentication().getName();
            User user = userRepo.findOne(where(emailIs(login)).or(usernameIs(login)));
            if (user != null) {
                return mapper.map(user, UserVO.class);
            }
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public User getConnectedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            String login = securityContext.getAuthentication().getName();
            return userRepo.findOne(where(emailIs(login)).or(usernameIs(login)));
        }

        return null;
    }

    @Override
    public Boolean logout() {
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setAuthentication(Authentication authenticate) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
