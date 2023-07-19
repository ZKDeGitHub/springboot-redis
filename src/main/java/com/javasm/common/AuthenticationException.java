package com.javasm.common;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 11:39
 * @description: 自定义验证异常类，用于接收验证时的运行异常
 */

public class AuthenticationException extends RuntimeException{
   public AuthenticationException() {
   }

   public AuthenticationException(String message) {
      super(message);
   }
}
