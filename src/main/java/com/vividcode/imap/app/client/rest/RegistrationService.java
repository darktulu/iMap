package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.vo.UserVO;
import com.vividcode.imap.common.shared.dispatch.GetResult;
import com.vividcode.imap.common.shared.rest.ResourcesPath;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path(ResourcesPath.REGISTRATION)
public interface RegistrationService extends RestService {
    @POST
    Action<GetResult<Long>> register(UserVO user);
}
