package com.changmu.common.exception;


/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/28
 * @description 租车异常
 */
public enum RentalError {

    CAR_NOT_EXIST(199800,"车辆不存在(已下架)"),
    RENT_ORDER_NOT_EXIST(199801,"出租单不存在"),
    CAR_RENT_ERROR(199802,"车辆已出租,无法再次预订出租"),
    CAR_RETURN_ERROR(199803,"车辆已归还，无需重复归还"),
    CAR_EXPIRED(199804,"车辆已过期，无需手动归还")
    ;
	
    private int code;

    private String msg;

    private Throwable cause;

    RentalError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    RentalError(int code, String msg, Throwable throwable) {
        this.code = code;
        this.msg = msg;
        this.cause = throwable;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public RentalException toException() {
        return new RentalException(getCode(), getMsg());
    }

    public RentalException toException(Exception e) {
        return new RentalException(getCode(), getMsg(), e);
    }

}
