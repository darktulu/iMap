package com.vividcode.imap.server.controller;

import com.vividcode.imap.common.shared.dispatch.ValidatedResponse;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.server.service.RegistrationService;
import com.vividcode.imap.server.service.util.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(ResourcesPath.REGISTRATION)
public class RegistrationController extends BaseController {
    @Inject
    private RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ValidatedResponse register(@RequestBody @Valid UserVO user) {
        registrationService.create(user);
        return new ValidatedResponse();
    }
}
