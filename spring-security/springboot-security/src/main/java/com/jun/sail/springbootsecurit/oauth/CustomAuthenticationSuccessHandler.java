package com.jun.sail.springbootsecurit.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String DEFAULT_TARGET_URL = "/oauth/authorize";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();

        ObjectNode objectNode = objectMapper.createObjectNode();
        //String encryptedString = UtilSecret.aesCbcEncrypt(Base64Utils.encodeToString(SerializationUtils.serialize(authentication)),
        //    accountProperties.getOauthEncryptKey(),
        //    accountProperties.getOauthIvParameter());
        String token = URLEncoder.encode(SerializationUtils.serialize(authentication).toString(), "utf-8");
        objectNode.put("token",token);
        objectNode.put("authUrl", DEFAULT_TARGET_URL);
        writer.write(objectNode.toString());
    }

}