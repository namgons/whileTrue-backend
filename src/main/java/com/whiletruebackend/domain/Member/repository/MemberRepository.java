package com.whiletruebackend.domain.Member.repository;

import com.whiletruebackend.domain.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
