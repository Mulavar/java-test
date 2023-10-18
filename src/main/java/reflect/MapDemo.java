package reflect;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class MapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, WorkerInfo> map = new ConcurrentHashMap<>();
        WorkerInfo wi = new WorkerInfo(new HashSet<Integer>());
        for (int i = 0; i < 10000; i++) {
            wi.set.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            map.put(i, wi);
        }

        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 1000; i++) {
            final int t = i;
            Thread thread1 = new Thread(() -> map.computeIfPresent(t, (k, v) -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.set.remove(t);
                return v;
            }));
            thread1.start();
//            Thread thread2 = new Thread(() -> map.computeIfPresent("b", (k, v) -> {
//                v.WI.remove("3");
//                return v;
//            }));
//            thread2.start();
        }

        latch.countDown();
        System.out.println(map);
    }
}

class WorkerInfo {
    public Set<Integer> set;

    public WorkerInfo(Set<Integer> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "WorkerInfo{" +
                "set=" + set +
                '}';
    }
}
