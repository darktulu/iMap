package com.vividcode.imap.server.service;

import com.vividcode.imap.common.shared.vo.UserVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface RegistrationService {
    Long create(UserVO user);

    void delete(Long id);

    void show(@Valid UserVO user);
}
