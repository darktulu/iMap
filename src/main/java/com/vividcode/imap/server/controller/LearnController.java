package com.vividcode.imap.server.controller;

import com.vividcode.imap.common.shared.dispatch.GetResult;
import com.vividcode.imap.common.shared.dispatch.GetResults;
import com.vividcode.imap.common.shared.dispatch.NoResult;
import com.vividcode.imap.common.shared.rest.PathParameter;
import com.vividcode.imap.common.shared.rest.ResourcesPath;
import com.vividcode.imap.common.shared.rest.RestParameter;
import com.vividcode.imap.common.shared.vo.LearnVO;
import com.vividcode.imap.server.service.LearnService;
import com.vividcode.imap.server.service.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequestMapping(ResourcesPath.LEARN)
public class LearnController extends BaseController {
    @Autowired
    private LearnService learnService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GetResult<Long> create(@RequestBody @Valid LearnVO learn) {
        return new GetResult<Long>(learnService.create(learn));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = PathParameter.ID)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public NoResult delete(@PathVariable(RestParameter.ID) Long id) {
        learnService.delete(id);
        return new NoResult();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResults<LearnVO> loadAll() {
        return new GetResults<LearnVO>(learnService.loadAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = PathParameter.ID)
    @ResponseBody
    public GetResult<LearnVO> loadOne(@PathVariable(RestParameter.ID) Long id) {
        return new GetResult<LearnVO>(learnService.loadOne(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = PathParameter.ID)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public NoResult archive(@PathVariable(RestParameter.ID) Long id) {
        learnService.archive(id);
        return new NoResult();
    }
}
