package tech.nilvalue.portal.ErrorHandler;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;



@ControllerAdvice
public class ExceptionAdvice {


    //Для корректного отображения кода ошибки в отображении error.html реализовать ErrorHandler
    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView httpClientErrorException(ModelAndView mav, HttpClientErrorException e) {
        mav.setViewName("error");
        mav.addObject("status", e.getStatusCode());
        mav.addObject("message", "Вы оставили слишком много обращений.");
        return mav;
    }

    //Возвращает клиенту JSON с указанием полей ввода, в которых были ошибки.
    //Нужен рефакторинг для реализации подсветки полей с ошибками
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleConstraintViolationException (
            ConstraintViolationException e
    ) {
        final List<Violation> violationList = e.getConstraintViolations().stream()
                .map(
                violation -> new Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                )
        ).collect(Collectors.toList());
        return new ValidationErrorResponse(violationList);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }
}
