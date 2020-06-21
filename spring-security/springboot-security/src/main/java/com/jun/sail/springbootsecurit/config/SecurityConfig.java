package com.jun.sail.springbootsecurit.config;

import com.jun.sail.springbootsecurit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用来配置用户信息、密码解码器passwordEncoder、安全拦截机制
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    /**
     * 自定义的用户信息。这里从内存中查，实际应从数据库查
     */
    @Override
    protected UserDetailsService userDetailsService() {
        return accountService;

        ////下面是从内存查
        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //// 创建两个 qq 用户
        //manager.createUser(User.withUsername("u1").password("123456").authorities("info").build());
        //manager.createUser(User.withUsername("u2").password("123456").authorities("fans").build());
        //return manager;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    /**
     * 密码加密器
     */
    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

        //NoOpPasswordEncoder直接比较字符串，但是数据库不能直接存密码的明文
        //return NoOpPasswordEncoder.getInstance();
    }


    /**
     * 自定义的PasswordEncoder，可以利用jasypt
     */
    //@Bean
    //public PasswordEncoder oauthLoginPasswordEncoder() {
    //    return new PasswordEncoder() {
    //        @Override
    //        public String encode(CharSequence charSequence) {
    //            return charSequence.toString();
    //        }
    //
    //        @Override
    //        public boolean matches(CharSequence charSequence, String s) {
    //            return !s.equals("userNotFoundPassword") && passwordUtils.checkPassword(s, charSequence.toString());
    //        }
    //    };
    //}



    /**
     * 配置安全拦截配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                //控制用户在访问/qq/info路径时必须有info授权，UserDetailsService里面配置的
                .antMatchers("/qq/info/**").hasAuthority("info")
                .antMatchers("/qq/fans/**").hasAuthority("fans")

                //所有/qq开头的请求都必须经过认证
                .antMatchers("/qq/**").authenticated()
                //其他请求放行
                .anyRequest().permitAll()
                //允许表单登陆，登陆成功后转到/login-success页面
                .and().formLogin().successForwardUrl("/login-success");


    }
}