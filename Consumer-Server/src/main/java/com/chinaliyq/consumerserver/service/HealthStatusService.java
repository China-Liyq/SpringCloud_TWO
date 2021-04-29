package com.chinaliyq.consumerserver.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @Author: liyq
 * @Description: SpringCloud_TWO
 * @Date: 2021/4/29 - 15:13
 * @Version: 1.0
 **/
@Service
public class HealthStatusService implements HealthIndicator {
    /*启动后初始健康值*/
    private Boolean status = true;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if (status)
            return new Health.Builder().up().build();
        return new Health.Builder().down().build();

    }
}
