package com.whiletruebackend.domain.Problem.service;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.dto.response.ProblemListResponseDto;
import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final NotionService notionService;

    @Override
    public ProblemListResponseDto getProblemList(Member member) {
        List<Problem> problemList = notionService.getProblemList(member.getNotionApiKey(), member.getNotionSpace().getDatabaseId());
        return ProblemListResponseDto.from(problemList);

    }
}
