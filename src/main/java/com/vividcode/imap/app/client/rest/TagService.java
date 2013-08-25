package com.vividcode.imap.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.vo.TagVO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(ResourcesPath.TAG)
public interface TagService extends RestService {
    @GET
    Action<GetResults<TagVO>> loadAll();
}
