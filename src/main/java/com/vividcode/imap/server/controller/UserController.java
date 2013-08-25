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

package com.vividcode.imap.server.controller;

import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.dispatch.ValidatedResponse;
import com.vividcode.imap.common.shared.rest.PathParameter;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.rest.RestParameter;
import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.server.model.User;
import com.vividcode.imap.server.service.UserManagementService;
import com.vividcode.imap.server.service.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(ResourcesPath.USER)
public class UserController extends BaseController {
    @Autowired
    UserManagementService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResults<UserVO> loadAll() {
        return new GetResults<UserVO>(userService.loadAllUsers());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ValidatedResponse create(@RequestBody @Valid UserVO user) {
        userService.createUser(user);
        return new ValidatedResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ValidatedResponse update(@RequestBody @Valid UserVO user) {
        userService.updateUser(user);
        return new ValidatedResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = PathParameter.ID)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(RestParameter.ID) Long id) {
        userService.deleteUser(id);
    }
}
