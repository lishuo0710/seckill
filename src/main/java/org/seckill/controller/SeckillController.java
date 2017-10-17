package org.seckill.controller;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("list", seckills);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getOneById(seckillId);
        if (seckill == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("detail", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/jason;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId) {

        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getLocalizedMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/excution", method = RequestMethod.POST, produces = {"appication/jason;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killphone", required = false) Long phone) {
        // SeckillResult<Exposer> result;
        if (phone == null) {
            return new SeckillResult<SeckillExcution>(false, "未注册的手机号");
        }
        try {
            SeckillExcution excution = seckillService.ExcuteSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExcution>(true, excution);
        } catch (SeckillCloseException e) {
            SeckillExcution excution = seckillService.ExcuteSeckill(seckillId, phone, md5);
            logger.error(e.getMessage(), e);
            return new SeckillResult<SeckillExcution>(false, excution);
        } catch (RepeatKillException e) {
            SeckillExcution excution = seckillService.ExcuteSeckill(seckillId, phone, md5);
            logger.error(e.getMessage(), e);
            return new SeckillResult<SeckillExcution>(false, excution);
        } catch (Exception e) {
            SeckillExcution excution = seckillService.ExcuteSeckill(seckillId, phone, md5);
            logger.error(e.getMessage(), e);
            return new SeckillResult<SeckillExcution>(false, excution);
        }

    }

    @RequestMapping("/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }


}
