package com.whiletruebackend.global.error;

import com.whiletruebackend.global.error.exception.NotionConnectionErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("[Notion Error] " + response.getBody());
        throw NotionConnectionErrorException.EXCEPTION;
    }
}