package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

public
interface SeckillService {
    /**
     * 获取所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 获取一个秒杀记录
     *
     * @return
     */
    Seckill getOneById(long seckillId);

    /**
     * 秒杀开启时暴露秒杀地址
     * 未开启时显示秒杀倒计时
     *
     * @param seckillId
     */
    Exposer exposeSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExcution ExcuteSeckill(long seckillId, long userPhone, String md5)
            throws RepeatKillException, SeckillCloseException, SeckillException;
}
