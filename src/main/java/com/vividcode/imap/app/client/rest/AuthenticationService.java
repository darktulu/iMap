package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.dispatch.GetResult;
import com.vividcode.imap.common.shared.dispatch.NoResult;
import com.vividcode.imap.common.shared.dto.UserCredentials;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.vo.UserVO;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path(ResourcesPath.AUTHENTICATION)
public interface AuthenticationService extends RestService {
    @POST
    Action<GetResult<Boolean>> authenticate(UserCredentials credentials);

    @GET
    Action<GetResult<UserVO>> currentUser();

    @DELETE
    Action<NoResult> logout();
}
