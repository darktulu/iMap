package com.vividcode.imap.server.service;

import com.vividcode.imap.common.shared.vo.TagVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TagService {
    List<TagVO> loadAll();
}
