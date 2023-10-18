package concurrent;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<StringWrapper> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(LocalTime.now() + " thread1=" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new StringWrapper("hello world 11111");
        });

        future.whenComplete((res, error) -> {
            System.out.println(LocalTime.now() + " thread22=" + Thread.currentThread().getName());
            res.setMsg("hello world 22222");
        });
        System.out.println("future.get(): " + future.get());

        future.whenComplete((res, error) -> {
            System.out.println(LocalTime.now() + " thread33=" + Thread.currentThread().getName());
            try {
                res.setMsg("hello world 33333");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        System.out.println("future.get(): " + future.get());
//        TimeUnit.SECONDS.sleep(10);
    }

    static class StringWrapper {
        private String msg;

        public StringWrapper(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "StringWrapper{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }
}
