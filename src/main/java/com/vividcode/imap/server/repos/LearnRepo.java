package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.Learn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LearnRepo extends JpaRepository<Learn, Long>, JpaSpecificationExecutor<Learn> {
}
