package com.whiletruebackend.domain.Problem.service;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.dto.request.ProblemRequestDto;
import com.whiletruebackend.domain.Problem.dto.response.ProblemExistenceResponseDto;
import com.whiletruebackend.domain.Problem.dto.response.ProblemListResponseDto;
import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.error.exception.MemberDatabaseIdNotFoundException;
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
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        List<Problem> problemList = notionService.getAllProblemList(member.getNotionApiKey(), databaseId);
        return ProblemListResponseDto.from(problemList);
    }

    @Override
    public ProblemExistenceResponseDto checkIfProblemExits(Member member, ProblemRequestDto problemRequestDto) {
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        boolean isExists = notionService.isProblemExists(member.getNotionApiKey(), databaseId, problemRequestDto.getProblem());

        return ProblemExistenceResponseDto.from(isExists);
    }

    @Override
    public void insertNewProblem(Member member, ProblemRequestDto problemRequestDto) {
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        notionService.insertNewProblem(member.getNotionApiKey(), databaseId, problemRequestDto.getProblem());
    }

    private void isDatabaseIdExists(String databaseId) {
        if (databaseId == null) {
            throw MemberDatabaseIdNotFoundException.EXCEPTION;
        }
    }
}
