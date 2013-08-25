package com.vividcode.imap.server.service;

import com.google.common.collect.Lists;
import com.vividcode.imap.common.shared.vo.TagVO;
import com.vividcode.imap.server.repos.UserRepo;
import com.vividcode.imap.server.service.util.MyModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/applicationContext.xml",
        "classpath:/META-INF/applicationContext-security.xml",
        "classpath:/META-INF/applicationContext-data.xml"
})
public class ServiceTest {
    @Inject
    private UserRepo userRepo;
    @Inject
    private MyModelMapper mapper;

    @Test
    public void nothing() {
        List<TagVO> tags = Lists.newArrayList();
        TagVO tag = new TagVO();
        tag.setTitle("spring");
        tags.add(tag);
        tag = new TagVO();
        tag.setTitle("activiti");
        tags.add(tag);
        tag = new TagVO();
        tag.setTitle("i'm ubuntu");
        tags.add(tag);

        for (TagVO tagVO : tags) {
            System.out.println(tagVO.getColor());
        }
    }
}
