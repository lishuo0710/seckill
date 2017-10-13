package org.seckill.exception;


import org.seckill.enums.SeckillStatEnum;

/**
 * 重复秒杀异常（运行时异常）
 */
public
class RepeatKillException extends SeckillException {

    public
    RepeatKillException(String message) {
        super(message);
    }

    public
    RepeatKillException(SeckillStatEnum seckillStatEnum) {
        super(seckillStatEnum.getStateInfo());
    }

    public
    RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
