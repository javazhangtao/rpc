package com.plugins.server.suppor.pool;

/**
 * Created by zhangtao on 2015/12/9.
 * 代理方法池配置
 */
public class ProxyMethodPoolConfig {


    private Integer minIdle;
    private Integer maxIdle;
    private Long minEvictableIdleTimeMillis;


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
