package com.plugins.server.suppor.pool;

/**
 * Created by zhangtao on 2015/12/9.
 * 代理方法池配置
 */
public class ProxyMethodPoolConfig {

    final Integer DEFAULT_MINIDLE=10;
    final Integer DEFAULT_MAXIDLE=50;
    final Long DEFAULT_TIMEMILLIS=60*60*1000l;

    private Integer minIdle=DEFAULT_MINIDLE;
    private Integer maxIdle=DEFAULT_MAXIDLE;
    private Long minEvictableIdleTimeMillis=DEFAULT_TIMEMILLIS;

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
}
