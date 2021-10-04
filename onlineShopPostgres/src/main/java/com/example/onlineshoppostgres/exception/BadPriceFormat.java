package com.example.onlineshoppostgres.exception;

import com.example.onlineshoppostgres.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BadPriceFormat {

    private final Log logger = LogFactory.getLog(BadPriceFormat.class);

    @ExceptionHandler(value = NumberFormatException.class )
    public String handleException(HttpServletRequest request, Exception ex){
        logger.error("Request "+  request.getRequestURI()+ "Threw an Exception", ex);
        return "errorNumberFormat";
    }
}
