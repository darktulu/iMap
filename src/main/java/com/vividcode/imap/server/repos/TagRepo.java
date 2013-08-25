package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepo extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
}
