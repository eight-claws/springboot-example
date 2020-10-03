package com.jun.cloud.common.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * 将相关信息保存在RequestContextUtil里面
 * 1.保存当前request和response，以便在Controller和service层代码直接拿到请求和返回信息
 * 使用方式如：HttpServletRequest request = RequestContextUtil.getRequest()
 * <p>
 * 2.保存登录用户的session信息及多语言标识的处理
 * 使用方式如：UserSession userSession = RequestContextUtil.getUserSession()
 * Local userLocal = RequestContextUtil.getLocale()
 */
public class RequestContextFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //try{
        //    HttpServletRequest httpRequest = (HttpServletRequest) request;
        //    RequestContextUtil.setRequest(httpRequest);
        //    RequestContextUtil.setResponse((HttpServletResponse) response);
        //
        //    HttpSession session = httpRequest.getSession();
        //    if(session != null){
        //        Assertion assertion = (Assertion) httpRequest.getSession().getAttribute("_const_cas_assertion_");
        //        if(assertion != null && assertion.getPrincipal() != null){
        //            String name = assertion.getPrincipal().getName();
        //            String[] infos = name.split("&&");
        //            if(infos.length >= 6){
        //                UserSession userSession = new UserSession();
        //                userSession.setUserId(infos[0]);
        //                userSession.setPersonId(infos[1]);
        //                userSession.setClientIp(infos[2]);
        //                userSession.setClientMac(infos[3]);
        //                userSession.setTgc(infos[4]);
        //                //处理多语言标识，多语言可能是中划线或下划线
        //                String language = infos[5];
        //                String[] arrays = language.split("-");
        //                if(arrays.length==1){
        //                    String[] tempArray = language.split("_");
        //                    if(tempArray.length>1){
        //                        arrays = tempArray;
        //                    }
        //                }
        //                if(arrays.length == 2){
        //                    userSession.setLanguage(new Locale(arrays[0],arrays[1]));
        //                }else if(arrays.length == 1){
        //                    userSession.setLanguage(new Locale(arrays[0],""));
        //                }
        //                RequestContextUtil.setUserSession(userSession);
        //            }
        //        }
        //    }
        //    chain.doFilter(request,response);
        //}finally {
        //    RequestContextUtil.clear();
        //}
    }

    @Override
    public void destroy() {

    }

}