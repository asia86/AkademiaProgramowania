package com.example.onlineshoppostgres.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionController {


    private final Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = Exception.class )
    public String handleException(HttpServletRequest request, Exception ex){
        logger.error("Request "+  request.getRequestURI()+ "Threw an Exception", ex);
        return "error";
    }

}
