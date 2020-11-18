package com.xi.xlm.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author liuyujun
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }

}
