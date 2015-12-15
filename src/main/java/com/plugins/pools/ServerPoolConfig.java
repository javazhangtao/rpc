package com.plugins.pools;

/**
 * Created by zhangtao on 2015/12/9.
 * 代理方法池配置
 */
public class ServerPoolConfig {

    final Integer DEFAULT_MAXTOTAL=500;
    final Long DEFAULT_TIMEMILLIS=60*60*1000l;

    private Integer maxTotal=DEFAULT_MAXTOTAL;
    private Long minEvictableIdleTimeMillis=DEFAULT_TIMEMILLIS;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxIdle) {
        this.maxTotal = maxIdle;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
}
