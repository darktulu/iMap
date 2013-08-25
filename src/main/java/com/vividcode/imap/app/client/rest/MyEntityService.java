package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.vo.MyEntityVO;
import com.vividcode.imap.common.shared.dispatch.GetResult;
import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.dispatch.ValidatedResponse;
import com.vividcode.imap.common.shared.rest.PathParameter;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.rest.RestParameter;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path(ResourcesPath.ENTITY)
public interface MyEntityService extends RestService {
    @POST
    Action<ValidatedResponse> create(MyEntityVO entity);

    @DELETE
    @Path(PathParameter.ID)
    Action<GetResult<Void>> delete(@PathParam(RestParameter.ID) Long id);

    @GET
    Action<GetResults<MyEntityVO>> loadAll();
}
