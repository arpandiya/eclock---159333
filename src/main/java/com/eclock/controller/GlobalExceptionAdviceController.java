package com.eclock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdviceController {

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception ex) {
        String errorMessage = null;

        ModelAndView errorView = new ModelAndView();
        errorView.setViewName("error");

        if(ex.getMessage() != null) {
            errorMessage = ex.getMessage();
            } else if(ex.getCause() != null) {
                errorMessage = ex.getCause().toString();
            } else if(ex != null) {
                    errorMessage = ex.toString();
            }

        errorView.addObject("errorMessage", errorMessage);
        return errorView;
        }
}
