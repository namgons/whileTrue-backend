package com.whiletruebackend.global.error;

import com.whiletruebackend.global.error.exception.NotionConnectionErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

@Slf4j
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) {
        log.error("[Notion Error]");
        throw NotionConnectionErrorException.EXCEPTION;
    }
}