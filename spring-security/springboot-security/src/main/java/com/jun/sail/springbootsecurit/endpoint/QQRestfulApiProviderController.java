package com.jun.sail.springbootsecurit.endpoint;

import com.jun.sail.springbootsecurit.endpoint.dto.InMemoryQQDatabase;
import com.jun.sail.springbootsecurit.endpoint.dto.QQAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 */
@RestController
@RequestMapping()
@Slf4j
public class QQRestfulApiProviderController {

    @RequestMapping("/qq/info/{qq}")
    public QQAccount info(@PathVariable("qq") String qq){
        return InMemoryQQDatabase.database.get(qq);
    }

    @RequestMapping("/qq/fans/{qq}")
    public List<QQAccount> fans(@PathVariable("qq") String qq){
        return InMemoryQQDatabase.database.get(qq).getFans();
    }



    @RequestMapping("/login-success")
    public String loginSuccess(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String username = "匿名用户";
        if(Objects.nonNull(principal) && principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }
        return username + "  登陆成功";
    }

}