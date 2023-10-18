package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntToLongDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = IntToLongDemo.class.getDeclaredMethod("printLong", Long.class);
        int arg = 10;
        IntToLongDemo demo = new IntToLongDemo();
//        demo.printLong(arg);
        // Integer -> long
        System.out.println(Integer[].class.getName());
        method.invoke(demo, arg);
    }

    public void printLong(Long num) {
        System.out.println(num.getClass());
        System.out.println(num);
    }
}
