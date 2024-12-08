package com.amtron.akn_inventory.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amtron.akn_inventory.enums.ErrorType;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Object handleResourceNotFoundException(HttpServletRequest request,
            ResourceNotFoundException ex, RedirectAttributes redirectAttributes) {

        log.error("ResourceNotFoundException stack trace: ");
        ex.printStackTrace();

        if (isAjaxRequest(request)) {
            log.debug("ResourceNotFoundException ajax request");
            // Handle AJAX error response
            ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
            detail.setProperty("errorType", ErrorType.GENERIC);
            return detail;
        } else {
            // Handle MVC error page
            log.debug("ResourceNotFoundException MVC request");
            redirectAttributes.addAttribute("actualErrorMsg", ex.getMessage());
            return "redirect:/error";
        }

    }

    @ExceptionHandler(DataValidationException.class)
    public Object handleDataValidationException(HttpServletRequest request,
            DataValidationException ex, RedirectAttributes redirectAttributes) {

        log.error("DataValidationException stack trace: ", ex);

        if (isAjaxRequest(request)) {
            log.debug("DataValidationException ajax request");
            // Handle AJAX error response
            ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            detail.setProperty("errorType", ErrorType.GENERIC);
            return detail;
        } else {
            // Handle MVC error page
            log.debug("DataValidationException MVC request");
            redirectAttributes.addAttribute("actualErrorMsg", ex.getMessage());
            return "redirect:/error";
        }

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleValidationExceptions(MethodArgumentNotValidException ex) {

        log.error("MethodArgumentNotValidException stack trace: ", ex);

        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMap);
        } catch (Exception e) {
            log.error("MethodArgumentNotValidException could not convert field error map to json.");
        }

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, json);
        detail.setProperty("errorType", ErrorType.VALIDATION);
        return detail;

    }

    @ExceptionHandler(RuntimeException.class)
    public Object onRuntimeException(HttpServletRequest request,
            RuntimeException ex, RedirectAttributes redirectAttributes) {

        log.error("RuntimeException stack trace: ");
        ex.printStackTrace();

        if (isAjaxRequest(request)) {
            log.debug("RuntimeException ajax request");
            // Handle AJAX error response
            ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            detail.setProperty("errorType", ErrorType.GENERIC);
            return detail;
        } else {
            // Handle MVC error page
            log.debug("RuntimeException MVC request");
            redirectAttributes.addAttribute("actualErrorMsg", ex.getMessage());
            return "redirect:/error";
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
