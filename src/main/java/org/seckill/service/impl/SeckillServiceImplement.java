package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


@Service
public
class SeckillServiceImplement implements SeckillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //private final static loggerFactory
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    //md5盐值字符串用于混淆MD5
    private final String salt = "safkudsgfd786754324j-=90-6*%&#%$";

    public
    List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public
    Seckill getOneById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public
    Exposer exposeSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date starttime = seckill.getStartTime();
        Date endtime = seckill.getEndTime();
        //获取系统当前时间
        Date nowtime = new Date();
        if (nowtime.getTime() < starttime.getTime()
                || nowtime.getTime() > endtime.getTime()) {
            return new Exposer(false, seckillId, nowtime.getTime(), starttime.getTime(), endtime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private
    String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public
    SeckillExcution ExcuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("data has been rewrite");
        }
        //执行秒杀逻辑 减库存 --- 记录购买记录
        Date nowTime = new Date();
        try {


            int updatecount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updatecount < 1) {
                throw new SeckillCloseException(SeckillStatEnum.END);
            } else {
                //记录购行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount > 0) {
                    //重复秒杀
                    throw new RepeatKillException(SeckillStatEnum.REPEAT_KILL);
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId);
                    throw new SeckillException(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (Exception e) {
            logger.info("系统异常");
            //讲所有编译时异常转化成运行时异常
            throw new SeckillException("seckill inner error :" + e.getLocalizedMessage());
        }

    }
}
