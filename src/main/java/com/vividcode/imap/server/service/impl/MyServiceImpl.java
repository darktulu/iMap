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
import com.vividcode.imap.common.shared.vo.MyEntityVO;
import com.vividcode.imap.server.model.MyEntity;
import com.vividcode.imap.server.repos.MyEntityRepo;
import com.vividcode.imap.server.service.MyService;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MyServiceImpl implements MyService {
    @Autowired
    private MyEntityRepo myEntityRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    public Long create(MyEntityVO entity) {
        entity.setCreated(new Date());
        return myEntityRepo.save(mapper.map(entity, MyEntity.class)).getId();
    }

    @Override
    public void delete(Long id) {
        myEntityRepo.delete(id);
    }

    @Override
    public void show(MyEntityVO myEntity) {
        System.out.println("this is a valid myEntity" + myEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyEntityVO> loadAll() {
        List<MyEntityVO> result = Lists.newArrayList();
        for (MyEntity entity : myEntityRepo.findAll()) {
            result.add(mapper.map(entity, MyEntityVO.class));
        }
        return result;
    }
}
