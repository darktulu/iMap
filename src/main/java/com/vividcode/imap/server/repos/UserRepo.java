package com.vividcode.imap.server.repos;

import com.vividcode.imap.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
