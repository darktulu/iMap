package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.dispatch.*;
import com.vividcode.imap.common.shared.rest.PathParameter;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.rest.RestParameter;
import com.vividcode.imap.common.shared.vo.LearnVO;

import javax.ws.rs.*;

@Path(ResourcesPath.LEARN)
public interface LearnService extends RestService {
    @POST
    Action<GetResult<Long>> create(LearnVO entity);

    @DELETE
    @Path(PathParameter.ID)
    Action<NoResult> delete(@PathParam(RestParameter.ID) Long id);

    @GET
    Action<GetResults<LearnVO>> loadAll();

    @GET
    @Path(PathParameter.ID)
    Action<GetResult<LearnVO>> loadOne(@PathParam(RestParameter.ID) Long id);

    @PUT
    @Path(PathParameter.ID)
    Action<NoResult> archive(@PathParam(RestParameter.ID) Long id);
}
