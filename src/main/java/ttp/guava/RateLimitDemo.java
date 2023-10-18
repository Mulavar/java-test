package ttp.guava;

import java.time.LocalTime;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitDemo {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1);
        // Guava 采用了预消费，所以第一次无需等待，而第二次
        for(int i = 1; i < 10; i = i + 2 ) {
            double waitTime = limiter.acquire(i);
            System.out.println("i=" + i + " cutTime=" + LocalTime.now() + " acq:" + i + " waitTime:" + waitTime);
        }
    }
}
