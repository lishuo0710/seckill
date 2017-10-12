package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 如果影响行数>1
     */
    int insertSuccessKilled(long seckillId,long userPhone);

    /**
     * 根据Id查询秒杀明细并携带产品对象
     * @param seckillId
     * @return 秒杀明细
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
