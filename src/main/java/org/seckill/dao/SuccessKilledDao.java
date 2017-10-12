package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 如果影响行数>1
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据Id查询秒杀明细并携带产品对象
     * @param seckillId
     * @return 秒杀明细
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId);
}
