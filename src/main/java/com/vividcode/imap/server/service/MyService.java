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

package com.vividcode.imap.server.service;

import java.util.List;

import com.vividcode.imap.common.shared.vo.MyEntityVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface MyService {
    Long create(MyEntityVO entity);

    void delete(Long id);

    List<MyEntityVO> loadAll();

    void show(@Valid MyEntityVO myEntity);
}
