package lang;

import java.util.ArrayList;
import java.util.List;

public class HashMapDemo {
    public int a = 10;
    public static void main(String[] args) {
        List<HashMapDemo> list = new ArrayList<>();
        HashMapDemo demo = new HashMapDemo();
        list.add(demo);
        list.get(0).a = 20;
        System.out.println(demo);
        testVarargs("aaa", 1, 2);
    }

    public static void testVarargs(String string, Integer... integers) {

    }

    public static void testVarargs(Integer... integers) {

    }

    @Override
    public String toString() {
        return "HashMapDemo{" +
                "a=" + a +
                '}';
    }
}
