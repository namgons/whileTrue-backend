package com.whiletruebackend.domain.Member.repository;

import com.whiletruebackend.domain.Member.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
