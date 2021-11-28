package com.shopby.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class EncodingFilter extends OncePerRequestFilter {

    @Override
    public void destroy() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //요청과 응답 한글 처리...
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
        //사전 작업(위에서 처리한 한글 처리 작업)의 내용이
        //서버상의 다음 번 컴포넌트에게 계속 적용되기 위한 작업...
        filterChain.doFilter(request, response);
    }


}