package com.whiletruebackend.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.whiletruebackend.global.error.dto.ErrorReason;
import com.whiletruebackend.global.error.dto.ErrorResponse;
import com.whiletruebackend.global.error.errorCode.BaseErrorCode;
import com.whiletruebackend.global.error.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            setErrorResponse(e, request.getRequestURL().toString(), response);
        }
    }

    private void setErrorResponse(CustomException customException, String requestUrl, HttpServletResponse response) throws IOException {
        BaseErrorCode code = customException.getErrorCode();
        ErrorReason errorReason = code.getErrorReason();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        response.setStatus(errorReason.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        ErrorResponse errorResponse = ErrorResponse.of(errorReason, requestUrl);

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
