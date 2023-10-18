package ttp.guava.custom;

public class RateLimiter {
    private Integer rateLimit;

    public RateLimiter(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    public void acquire() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (rateLimit <= 0) {
                    this.wait();
                }
                rateLimit--;
                break;
            }

        }
    }

    public void release() {
        synchronized (this) {
            rateLimit++;
            this.notifyAll();
        }
    }
}
