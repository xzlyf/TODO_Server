package com.xz.app.todolist.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   //拦截所有标注@controller的异常
public class ControllExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {

        log.error("Request URL : {},Exception : {}",request.getRequestURI(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){//判断该异常是否带有ResponseStatus，有则上抛异常
            throw e;
        }

        ModelAndView view = new ModelAndView();
        view.addObject("url",request.getRequestURI());
        view.addObject("exception",e);
        view.setViewName("error/error");
        return view;
    }

}