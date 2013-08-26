package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpotRepo extends JpaRepository<Spot, Long>, JpaSpecificationExecutor<Spot> {
}
