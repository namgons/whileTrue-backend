package com.whiletruebackend.domain.Problem.service;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.dto.request.ProblemRequestDto;
import com.whiletruebackend.domain.Problem.dto.response.ProblemExistenceResponseDto;
import com.whiletruebackend.domain.Problem.dto.response.ProblemListResponseDto;
import com.whiletruebackend.global.error.exception.MemberDatabaseIdNotFoundException;
import com.whiletruebackend.global.error.exception.MemberInvalidDatabaseFormatException;
import com.whiletruebackend.global.notion.dto.response.CheckProblemResponseDto;
import com.whiletruebackend.global.notion.dto.response.ProblemPageListResponseDto;
import com.whiletruebackend.global.notion.dto.response.SuccessResponseDto;
import com.whiletruebackend.global.notion.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final NotionService notionService;

    @Override
    public ProblemListResponseDto getProblemList(Member member) {
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        ProblemPageListResponseDto problemPageListResponseDto = notionService.getAllProblemList(member.getNotionApiKey(), databaseId);

        if (!problemPageListResponseDto.getValidCheck()) {
            throw MemberInvalidDatabaseFormatException.EXCEPTION;
        }
        return ProblemListResponseDto.from(problemPageListResponseDto);
    }

    @Override
    public ProblemExistenceResponseDto checkIfProblemExits(Member member, ProblemRequestDto problemRequestDto) {
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        CheckProblemResponseDto checkProblemResponseDto = notionService.isProblemExists(member.getNotionApiKey(), databaseId,
                                                                                        problemRequestDto.getProblemPage());

        if (!checkProblemResponseDto.getValidCheck()) {
            throw MemberInvalidDatabaseFormatException.EXCEPTION;
        }
        return ProblemExistenceResponseDto.from(checkProblemResponseDto);
    }

    @Override
    public void insertNewProblem(Member member, ProblemRequestDto problemRequestDto) {
        String databaseId = member.getNotionSpace().getDatabaseId();
        isDatabaseIdExists(databaseId);

        SuccessResponseDto successResponseDto = notionService.insertNewProblem(member.getNotionApiKey(), databaseId,
                                                                               problemRequestDto.getProblemPage());

        if (!successResponseDto.getIsSucceed()) {
            throw MemberInvalidDatabaseFormatException.EXCEPTION;
        }
    }

    private void isDatabaseIdExists(String databaseId) {
        if (databaseId == null) {
            throw MemberDatabaseIdNotFoundException.EXCEPTION;
        }
    }
}
