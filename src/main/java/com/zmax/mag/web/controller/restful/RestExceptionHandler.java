package com.zmax.mag.web.controller.restful;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
/**
 * 出错过滤器
 * @author Administrator
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {  
	private static final MediaType utf8 = new MediaType("text", "plain", Charset.forName("UTF-8"));  
	@ExceptionHandler(value = { Exception.class })
	public final ResponseEntity<?> handleDefException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(utf8);
		ex.printStackTrace();
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.FORBIDDEN, request);
	}     
	@ExceptionHandler(value = { BoException.class })
	public final ResponseEntity<?> handleBoException(BoException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(utf8);
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.NOT_ACCEPTABLE, request);
	}    
	@ExceptionHandler(value = { NeedLoginException.class })
	public final ResponseEntity<?> handleNeedLoginException(NeedLoginException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(utf8);
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.UNAUTHORIZED, request);
	}  	
	@ExceptionHandler(value = { RightException.class })
	public final ResponseEntity<?> handleRightException(RightException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(utf8);
		return handleExceptionInternal(ex, ex.getMessage(), headers, HttpStatus.OK, request);
	}  	
}