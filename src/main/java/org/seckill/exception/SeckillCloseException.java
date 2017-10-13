package org.seckill.exception;

import org.seckill.enums.SeckillStatEnum;

/**
 * 秒杀关闭异常
 */
public
class SeckillCloseException extends SeckillException {

    public
    SeckillCloseException(String message) {
        super(message);
    }

    public
    SeckillCloseException(SeckillStatEnum seckillStatEnum) {
        super(seckillStatEnum.getStateInfo());
    }

    public
    SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
