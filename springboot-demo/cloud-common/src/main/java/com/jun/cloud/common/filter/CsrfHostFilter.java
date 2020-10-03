package com.jun.cloud.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 跨站点请求伪造及host请求头攻击过滤器
 *
 * @author xumengjin
 */
public class CsrfHostFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(CsrfHostFilter.class);

    private static final String URL_PROTO_HTTP = "http://";
    private static final String URL_PROTO_HTTPS = "https://";

    //private ILegalIpService tempLegalIpService;

    private List<String> excludedPages;

    //private ILegalIpService getLegalIIpService() {
    //    if (tempLegalIpService == null) {
    //        tempLegalIpService = SpringUtil.getBean("legalIpService");
    //    }
    //    return tempLegalIpService;
    //    return null;
    //}

    public static final String IGNORE_PAGES = "ignorePages";

    //@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //String urls = filterConfig.getInitParameter(IGNORE_PAGES);
        //if (ObjectUtils.isNotEmpty(urls)) {
        //    String[] urlsArray = urls.split(",");
        //    excludedPages = new ArrayList<>();
        //    excludedPages.addAll(Arrays.asList(urlsArray));
        //}
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (!CollectionUtils.isEmpty(excludedPages)) {
            for (String page : excludedPages) {
                if (request.getRequestURI().equals(page)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }

        // 只校验web端的请求
        Set<String> legalIps = getAllIpSet();
        String referer = request.getHeader("Referer");
        if (!CollectionUtils.isEmpty(legalIps) && referer != null && !"".equals(referer)) {
            if (referer.startsWith(URL_PROTO_HTTP)) {
                referer = referer.replace(URL_PROTO_HTTP, "");
            } else if (referer.startsWith(URL_PROTO_HTTPS)) {
                referer = referer.replace(URL_PROTO_HTTPS, "");
            }
            int i = referer.indexOf("/");
            if (i > 0) {
                referer = referer.substring(0, i);
                if (referer.indexOf(":") > 0) {
                    // referer 10.20.147.80:7888 -> 10.20.147.80只验证ip
                    referer = referer.substring(0, referer.indexOf(":"));
                }
            }
            if (!legalIps.contains(referer)) {
                // 不同域请求 视为非法
                log.error("found csrf request ", "referer", referer);
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "illegal request");
                return;
            }
        }
        // 校验host
        String host = request.getHeader("Host");
        if (!CollectionUtils.isEmpty(legalIps) && host != null && !"".equals(host)) {
            if (host.startsWith(URL_PROTO_HTTP)) {
                host = host.replace(URL_PROTO_HTTP, "");
            } else if (host.startsWith(URL_PROTO_HTTPS)) {
                host = host.replace(URL_PROTO_HTTPS, "");
            }
            int i = host.indexOf("/");
            if (i > 0) {
                host = host.substring(0, i);
            }
            i = host.indexOf(":");
            if (i > 0) {
                // referer 10.20.147.80:7888 -> 10.20.147.80只验证ip
                host = host.substring(0, host.indexOf(":"));
            }
            if (!legalIps.contains(host)) {
                // 不同域请求 视为非法
                log.error("found host error request ", "host", host);
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "illegal request");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    private Set<String> getAllIpSet() {
        //ILegalIpService legalIpService = getLegalIIpService();
        //if (legalIpService != null) {
        //    return legalIpService.getLegalIpSet();
        //}
        return null;
    }

}