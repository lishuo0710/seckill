package org.seckill.dto;

public
class Exposer {

    //是否暴露
    private boolean exposed;
    //一种加密措施
    private String md5;
    //系统当前时间(毫秒)
    private long now;
    //秒杀开启时间
    private long start;
    //秒杀结束时间
    private long end;
    //秒杀主键
    private long seckillId;

    public
    Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public
    Exposer(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public
    Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public
    boolean getExposed() {
        return exposed;
    }

    public
    void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public
    String getMd5() {
        return md5;
    }

    public
    void setMd5(String md5) {
        this.md5 = md5;
    }

    public
    long getNow() {
        return now;
    }

    public
    void setNow(long now) {
        this.now = now;
    }

    public
    long getStart() {
        return start;
    }

    public
    void setStart(long start) {
        this.start = start;
    }

    public
    long getEnd() {
        return end;
    }

    public
    void setEnd(long end) {
        this.end = end;
    }

    public
    long getSeckillId() {
        return seckillId;
    }

    public
    void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
}