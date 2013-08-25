package com.vividcode.imap.server.service.impl;

import com.google.common.collect.Lists;
import com.vividcode.imap.common.shared.vo.TagVO;
import com.vividcode.imap.server.model.Tag;
import com.vividcode.imap.server.repos.TagRepo;
import com.vividcode.imap.server.service.TagService;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Inject
    private TagRepo tagRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    public List<TagVO> loadAll() {
        List<TagVO> result = Lists.newArrayList();
        for (Tag tag : tagRepo.findAll()) {
            result.add(mapper.map(tag, TagVO.class));
        }
        return result;
    }
}
