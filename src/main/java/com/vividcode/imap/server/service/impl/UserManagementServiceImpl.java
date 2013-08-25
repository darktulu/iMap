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

package com.vividcode.imap.server.service.impl;

import com.google.common.collect.Lists;
import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.server.model.User;
import com.vividcode.imap.server.repos.UserRepo;
import com.vividcode.imap.server.service.UserManagementService;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class UserManagementServiceImpl implements UserManagementService {
    @Inject
    private UserRepo userRepos;
    @Inject
    private MyModelMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserVO> loadAllUsers() {
        List<UserVO> result = Lists.newArrayList();
        for (User user : userRepos.findAll()) {
            result.add(mapper.map(user, UserVO.class));
        }
        return result;
    }

    @Override
    public void createUser(UserVO user) {
        userRepos.save(mapper.map(user, User.class));
    }

    @Override
    public void updateUser(UserVO user) {
        User current = userRepos.findOne(user.getId());
        current.setAuthority(user.getAuthority());
        current.setEmail(user.getEmail());
        current.setFirstName(user.getFirstName());
        current.setLastName(user.getLastName());
        current.setPassword(user.getPassword());
        current.setUsername(user.getUsername());
        userRepos.save(current);
    }

    @Override
    public void deleteUser(Long id) {
        userRepos.delete(id);
    }
}
