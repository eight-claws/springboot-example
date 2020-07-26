package com.jun.sail.orderapi.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.util.HashMap;

public class CustomAuthResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthResponseExceptionTranslator.class);

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        logger.warn("oauth request resource server throw exception,", e);
        Throwable throwable = e.getCause();
        ApiCode apiCode = ApiCode.FAIL;
        if (throwable instanceof InvalidTokenException) {
            apiCode = ApiCode.TOKEN_ERROR;
        } else if (e instanceof InvalidGrantException) {
            apiCode = ApiCode.AUTH_CODE_INVALID_ERROR;
        }
        return new ResponseEntity(getJsonObjectByApiCode(apiCode), HttpStatus.OK);
    }

    private HashMap<String, Object> getJsonObjectByApiCode(ApiCode apiCode) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("code", apiCode.getCode());
        params.put("msg", apiCode.getMsg());
        return params;
    }
}