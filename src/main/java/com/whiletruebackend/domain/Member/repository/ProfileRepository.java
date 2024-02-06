package com.whiletruebackend.domain.Member.repository;

import com.whiletruebackend.domain.Member.entity.NotionSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<NotionSpace, Long> {
}
