package com.cms.admin.Exceptiom;

import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.net.BindException;

/**
 * @author ：GreatLi
 * @date ：Created 2022/8/14 16:09
 * @description：参数校验自定义异常
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ValidateExceptionHandler {

  private static final String PARAMS_ERROR = "params error";

  @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
  public ResponseEntity<String> handleMethodArgumentNotValidException(
      HttpServletResponse response, MethodArgumentNotValidException ex) {
    if (log.isErrorEnabled()) {
      log.error(ex.getMessage(), ex);
    }
    BindingResult result = ex.getBindingResult();
    FieldError error = result.getFieldError();
    String message = error.getDefaultMessage();
    response.setStatus(400);
    return ResponseUtils.badRequest(message);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity<String> handleBindException(
      HttpServletResponse response, ConstraintViolationException ex) {
    if (log.isErrorEnabled()) {
      log.error(ex.getMessage(), ex);
    }
    response.setStatus(400);
    return ResponseUtils.badRequest(PARAMS_ERROR);
  }
}
