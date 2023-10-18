package ttp.guava.custom;

import lombok.SneakyThrows;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5);
        for (int i = 0; i < 10; i++) {
            new MockThread(limiter).start();
        }
    }

    static class MockThread extends Thread {
        RateLimiter limiter;

        public MockThread(RateLimiter limiter) {
            this.limiter = limiter;
        }

        @SneakyThrows
        @Override
        public void run() {
            try {
                limiter.acquire();
                System.out.println(Thread.currentThread().getName());
                limiter.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
