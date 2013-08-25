package com.vividcode.imap.server.service.impl;

import com.google.common.collect.Lists;
import com.vividcode.imap.common.shared.type.LearnStatus;
import com.vividcode.imap.common.shared.vo.LearnVO;
import com.vividcode.imap.server.model.Learn;
import com.vividcode.imap.server.repos.LearnRepo;
import com.vividcode.imap.server.service.security.SecurityContextProvider;
import com.vividcode.imap.server.service.LearnService;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static com.vividcode.imap.server.repos.spec.LearnSpec.ownerIs;
import static com.vividcode.imap.server.repos.spec.LearnSpec.statusIn;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service
@Transactional
public class LearnServiceImpl implements LearnService {
    @Inject
    private LearnRepo learnRepo;
    @Inject
    private MyModelMapper mapper;
    @Inject
    private SecurityContextProvider securityProvider;

    @Override
    public Long create(LearnVO learn) {
        learn.setCreated(new Date());
        learn.setStatus(LearnStatus.ACTIVE);
        learn.setOwner(securityProvider.getCurrentUser());

        return learnRepo.save(mapper.map(learn, Learn.class)).getId();
    }

    @Override
    public void delete(Long id) {
        learnRepo.delete(id);
    }

    @Override
    public void show(LearnVO myEntity) {
        System.out.println("this is a valid myEntity" + myEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearnVO> loadAll() {
        List<LearnVO> result = Lists.newArrayList();
        List<Learn> learns = learnRepo.findAll(where(ownerIs(securityProvider.getConnectedUser()))
                .and(statusIn(LearnStatus.ACTIVE)));

        for (Learn entity : learns) {
            LearnVO learn = mapper.map(entity, LearnVO.class);

            result.add(learn);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public LearnVO loadOne(Long id) {
        LearnVO learn = mapper.map(learnRepo.findOne(id), LearnVO.class);

        return learn;
    }

    @Override
    public void archive(Long id) {
        Learn learn = learnRepo.findOne(id);
        learn.setStatus(LearnStatus.ARCHIVED);
        learnRepo.save(learn);
    }
}
