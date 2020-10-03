package com.jun.sail.orderauth.config;

import com.jun.sail.orderauth.oauth.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security的安全配置，注意OAuth和这里的配置没有强关联
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**", "/login/**") //将指定的这些URL请求开启
                .and()
                .authorizeRequests().anyRequest().authenticated() // 这里配置其他端点，任何验证过的用户都有权限访问
                .and()
                .formLogin().permitAll(); //新增login form支持用户登录及授权

        // 这里配置的意思是，/和/home不需要任何验证，其他任何请求都需要经过验证
        // http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

        // 还可以把用户信息放到内存中
        // auth.inMemoryAuthentication.withUser("user_1").password("123456").authorities("USER")
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userAccountService);
        daoAuthenticationProvider.setPasswordEncoder(loginPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userAccountService;
    }

    @Bean
    public PasswordEncoder loginPasswordEncoder() {
        // BCrypt是推荐的加密方式，每次salt值都不同所以生成的秘文也不同。比对时可以从生成的秘文中获取salt值.秘文一般以$2a$开头
        return new BCryptPasswordEncoder();

        // 使用委托PasswordEncoder可以支持多种密码加密方式，原理是在不同形式的密文前添加{idForEncode}，
        // 这样在解密时根据密文的前缀去匹配不同的passwordEncode然后解密，如果想迁移可以在原来没有前缀的密文前添加{idForEncode}
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    /**
     *
     * 可以根据需求，自定义密码加密方式
     */
    // @Bean
    // public PasswordEncoder oauthLoginPasswordEncoder() {
    //     return new PasswordEncoder() {
    //         @Override
    //         public String encode(CharSequence charSequence) {
    //             return charSequence.toString();
    //         }
    //
    //         @Override
    //         public boolean matches(CharSequence charSequence, String s) {
    //             return !s.equals("userNotFoundPassword") && s.equals(charSequence.toString());
    //         }
    //     };
    // }
}