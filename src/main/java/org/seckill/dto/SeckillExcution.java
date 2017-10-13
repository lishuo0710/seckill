package org.seckill.dto;

import org.seckill.entity.SuccessKilled;

public
class SeckillExcution {

    //秒杀商品
    private long seckillId;
    //秒杀状态
    private String state;
    //秒杀状态表示
    private String stateInfo;
    //秒杀明细
    private SuccessKilled successKilled;

    public
    SeckillExcution(long seckillId, String state, String stateInfo, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public
    SeckillExcution(long seckillId, String state, String stateInfo) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }

}
