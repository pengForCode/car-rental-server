package com.changmu.common.exception;

import com.changmu.common.constants.ErrorCode;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28 12:00
 * @description
 */
public class RentalException extends RuntimeException {
    private static final long serialVersionUID = 3655050728585279326L;
    private int code;

    public RentalException() {
        this.code = ErrorCode.ERROR.getCode();
    }

    public RentalException(String msg) {
        super(msg);
        this.code = ErrorCode.ERROR.getCode();
    }

    public RentalException(int code, String msg) {
        super(msg);
        this.code = ErrorCode.ERROR.getCode();
        this.code = code;
    }

    public RentalException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = ErrorCode.ERROR.getCode();
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}