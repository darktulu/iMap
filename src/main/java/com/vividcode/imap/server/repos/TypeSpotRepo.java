package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.TypeSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeSpotRepo extends JpaRepository<TypeSpot, Long>, JpaSpecificationExecutor<TypeSpot> {
}
