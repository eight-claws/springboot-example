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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security的安全配置，和OAuth无关
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // http.requestMatchers()
        //         .antMatchers("/oauth/**")
        //         .and()
        //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //         .and()
        //         .authorizeRequests() // 开启登录配置
        //         .anyRequest().authenticated() // 其他接口登录之后就可以访问
        //         .and();
                // .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // .addFilterAt(dmAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

        //把用户信息放到内存中
//        auth.inMemoryAuthentication()
//                .withUser("user_1").password("123456").authorities("USER")
//                .and()
//                .withUser("user_2").password("123456").authorities("USER");
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
        daoAuthenticationProvider.setPasswordEncoder(oauthLoginPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userAccountService;
    }

    @Bean
    public PasswordEncoder oauthLoginPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return !s.equals("userNotFoundPassword") && s.equals(charSequence.toString());
            }
        };
    }
}