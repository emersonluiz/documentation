package br.com.emersonluiz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.emersonluiz.exception.BadRequestException;
import br.com.emersonluiz.exception.FailureReason;
import br.com.emersonluiz.exception.NotFoundException;

public abstract class ExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    FailureReason handleBadRequestNFE(HttpServletRequest req, Exception e) {
        return new FailureReason(req.getRequestURL().toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    FailureReason handleBadRequestBRE(HttpServletRequest req, Exception e) {
        return new FailureReason(req.getRequestURL().toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    FailureReason handleBadRequestE(HttpServletRequest req, Exception e) {
        return new FailureReason(req.getRequestURL().toString(), e.getMessage());
    }

}
