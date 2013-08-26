package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocationRepo extends JpaRepository<Location, Long>, JpaSpecificationExecutor<Location> {
}
