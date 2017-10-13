package org.seckill.exception;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 秒杀业务相关异常
 */
public
class SeckillException extends RuntimeException {
    //秒杀商品
    private long seckillId;
    //秒杀状态
    private int state;
    //秒杀状态信息
    private String stateInfo;
    //秒杀成功的对象
    private SuccessKilled successKilled;

    //秒杀成功
    public
    SeckillException(long seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.successKilled = successKilled;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getStateInfo();
    }

    //秒杀失败
    public
    SeckillException(long seckillId, SeckillStatEnum seckillStatEnum) {
        this.seckillId = seckillId;
        this.stateInfo = seckillStatEnum.getStateInfo();
        this.state = seckillStatEnum.getState();
    }

    public
    SeckillException(String message) {
        super(message);
    }

    public
    SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
