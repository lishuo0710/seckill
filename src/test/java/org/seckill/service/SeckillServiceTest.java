package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public
class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public
    void getSeckillList() throws Exception {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info(String.format("list:{}", seckills));
    }

    @Test
    public
    void getOneById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getOneById(1000);
        logger.info(seckill.toString());
    }

    //测试代码完整逻辑，注意重复执行
    @Test
    public void exposeSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exposeSeckillUrl(id);
        if (exposer.getExposed()) {
            logger.info(exposer.toString());
            long phone = 13502171127L;
            String md5 = exposer.getMd5();
            try {
                SeckillExcution seckillException = seckillService.ExcuteSeckill(id, phone, md5);
            } catch (RepeatKillException e) {
                logger.info(e.getLocalizedMessage());
            } catch (SeckillCloseException e) {
                logger.info(e.getLocalizedMessage());
            }
        } else {
            //秒杀未开启
            logger.warn(exposer.toString());
        }
        logger.info(exposer.toString());

    }
    // output : Exposer{exposed=false, md5='null', now=1507955924876, start=1507651200000, end=1507737600000, seckillId=1000}

    @Test
    public
    void excuteSeckill() throws Exception {
     /*   long id = 1000;
        long phone = 13088888889L;
        String md5 = "";
        seckillService.ExcuteSeckill();*/
    }

}