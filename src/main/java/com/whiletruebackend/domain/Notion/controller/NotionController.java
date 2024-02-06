package com.whiletruebackend.domain.Notion.controller;

import com.whiletruebackend.domain.Notion.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notion")
public class NotionController {

    private final NotionService notionService;

    @PostMapping("/database/create")
    public ResponseEntity<?> createNotionDatabase() {

        return ResponseEntity.ok().build();
    }
}
