package com.zmax.common.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

public class ZbabiUtils
{
  public static boolean checkCaptcha(ImageCaptchaService imageCaptchaService, HttpServletRequest request, BindingResult errors)
  {
    try
    {
      String capt = request.getParameter("captcha");
      if (("8888".equals(capt)) || (!imageCaptchaService.validateResponseForID(request.getParameter("username"), capt).booleanValue())) {
        ObjectError err = new FieldError("token", "captcha", "sm.error.invalidCaptcha");
        return false;
      }
    } catch (CaptchaServiceException e) {
      ObjectError err = new FieldError("token", "captcha", "sm.error.invalidCaptcha");
      errors.addError(err);
      return false;
    }
    return true;
  }
}