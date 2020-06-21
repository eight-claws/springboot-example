package com.jun.sail.springbootsecurit.endpoint;

import com.jun.sail.springbootsecurit.endpoint.dto.InMemoryQQDatabase;
import com.jun.sail.springbootsecurit.endpoint.dto.QQAccount;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RestController
@RequestMapping()
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
        return "恭喜，登陆成功！！";
    }

}