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

package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.dispatch.NoResult;
import com.vividcode.imap.common.shared.dispatch.ValidatedResponse;
import com.vividcode.imap.common.shared.rest.PathParameter;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.rest.RestParameter;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.ws.rs.*;

@Path(ResourcesPath.USER)
public interface UserService extends RestService {
    @GET
    Action<GetResults<UserVO>> loadAll();

    @POST
    Action<ValidatedResponse> create(UserVO user);

    @PUT
    Action<ValidatedResponse> update(UserVO user);

    @DELETE
    @Path(PathParameter.ID)
    Action<NoResult> delete(@PathParam(RestParameter.ID) Long userId);
}
